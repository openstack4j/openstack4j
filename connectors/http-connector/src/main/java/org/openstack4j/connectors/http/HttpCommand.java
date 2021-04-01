package org.openstack4j.connectors.http;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.*;
import java.net.Proxy.Type;
import java.util.Map;

import com.google.common.io.ByteStreams;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpCommand is responsible for executing the actual request driven by the
 * HttpExecutor.
 */
public final class HttpCommand<R> {

    private static final Logger LOG = LoggerFactory.getLogger(HttpCommand.class);

    private final HttpRequest<R> request;
    private URL connectionUrl;
    private HttpURLConnection connection;
    private int retries;

    private HttpCommand(HttpRequest<R> request) {

        this.request = request;
    }

    /**
     * Creates a new HttpCommand from the given request
     *
     * @param request the request
     * @return the command
     */
    public static <R> HttpCommand<R> create(HttpRequest<R> request) {
        HttpCommand<R> command = new HttpCommand<>(request);
        command.initialize();
        return command;
    }

    /**
     * @param httpURLConnection the HttpURLConnection
     * @param method the methods name (GET, PUT, POST,... exception is thrown when trying to do a PATCH)
     * @see <a href= "https://java.net/jira/browse/JERSEY-639">https://java.net/jira/browse/JERSEY-639</a>
     */
    private static void setRequestMethodUsingWorkaroundForJREBug(final HttpURLConnection httpURLConnection, final String method) {
        try {
            httpURLConnection.setRequestMethod(method);
            // Check whether we are running on a buggy JRE
        } catch (final ProtocolException pe) {
            try {
                final Class<?> httpURLConnectionClass = httpURLConnection
                        .getClass();
                final Class<?> parentClass = httpURLConnectionClass
                        .getSuperclass();
                final Field methodField;
                // If the implementation class is an HTTPS URL Connection, we
                // need to go up one level higher in the heirarchy to modify the
                // 'method' field.
                if (parentClass == HttpsURLConnection.class) {
                    methodField = parentClass.getSuperclass().getDeclaredField(
                            "method");
                } else {
                    methodField = parentClass.getDeclaredField("method");
                }
                methodField.setAccessible(true);
                methodField.set(httpURLConnection, method);
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initialize() {
        try {

            populateQueryParams();
            populateHeaders();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    /**
     * Executes the command and returns the Response
     *
     * @return the response
     */
    public HttpResponse execute() throws Exception {
        byte[] requestBody = null;

        if (request.getEntity() != null) {
            if (InputStream.class.isAssignableFrom(request.getEntity().getClass())) {
                requestBody = ByteStreams.toByteArray((InputStream) request.getEntity());
            } else {
                String content = ObjectMapperSingleton.getContext(request.getEntity().getClass()).writer().writeValueAsString(request.getEntity());
                requestBody = content.getBytes();
            }
        } else if (request.hasJson()) {
            requestBody = request.getJson().getBytes();
        }

        try {
            //connection.setRequestMethod(request.getMethod().name());
            setRequestMethodUsingWorkaroundForJREBug(connection, request.getMethod().name());

            if (requestBody != null) {
                connection.setDoOutput(true);
                BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
                out.write(requestBody);
                out.flush();
            }

            int status = connection.getResponseCode();
            byte[] data = getResponseBytes();
            return HttpResponseImpl.wrap(connection.getHeaderFields(),
                    status, connection.getResponseMessage(),
                    data);

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw e;
        } finally {
            connection.disconnect();
        }
    }

    // https://stackoverflow.com/a/613484/2091470
    private byte[] getResponseBytes() throws IOException {
        InputStream is;
        try {
            is = connection.getInputStream();
        } catch (IOException ex) {
            is = connection.getErrorStream();
        }

        if (is != null) {
            return ByteStreams.toByteArray(is);
        }
        return null;
    }

    /**
     * @return true if a request entity has been set
     */
    public boolean hasEntity() {
        return request.getEntity() != null;
    }

    /**
     * @return current retry execution count for this command
     */
    public int getRetries() {
        return retries;
    }

    /**
     * @return incremement's the retry count and returns self
     */
    public HttpCommand<R> incrementRetriesAndReturn() {
        initialize();
        retries++;
        return this;
    }

    public HttpRequest<R> getRequest() {
        return request;
    }

    private void populateQueryParams() throws MalformedURLException {
        connectionUrl = new URL(request.getUrl());
    }

    private void populateHeaders() throws IOException {

        if (request.getConfig() != null && request.getConfig().getProxy() != null) {
            Config config = request.getConfig();
            Proxy proxy = new Proxy(Type.HTTP,
                    new InetSocketAddress(config.getProxy().getRawHost(), config.getProxy().getPort()));
            connection = (HttpURLConnection) connectionUrl.openConnection(proxy);
        } else {
            connection = (HttpURLConnection) connectionUrl.openConnection();
        }
        connection.setRequestProperty("Content-Type", request.getContentType());
        connection.setRequestProperty("Accept", "application/json; charset=utf-8");

        if (!request.hasHeaders()) {
            return;
        }
        for (Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
            connection.setRequestProperty(h.getKey(), String.valueOf(h.getValue()));
        }
    }
}
