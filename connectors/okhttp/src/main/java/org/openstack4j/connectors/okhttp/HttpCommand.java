package org.openstack4j.connectors.okhttp;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.io.ByteStreams;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;
import org.openstack4j.core.transport.*;
import org.openstack4j.core.transport.internal.HttpLoggingFilter;

/**
 * HttpCommand is responsible for executing the actual request driven by the HttpExecutor.
 *
 * @param <R>
 */
public final class HttpCommand<R> {

    private final HttpRequest<R> request;
    private OkHttpClient client;
    private Request.Builder clientReq;
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

    private void initialize() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        Config config = request.getConfig();

        if (config.getProxy() != null) {
            okHttpClientBuilder.proxy(new Proxy(Type.HTTP,
                    new InetSocketAddress(config.getProxy().getRawHost(), config.getProxy().getPort())));
        }

        if (config.getConnectTimeout() > 0)
            okHttpClientBuilder.connectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS);

        if (config.getReadTimeout() > 0)
            okHttpClientBuilder.readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS);

        if (config.isIgnoreSSLVerification()) {
            okHttpClientBuilder.hostnameVerifier(UntrustedSSL.getHostnameVerifier());
            okHttpClientBuilder.sslSocketFactory(UntrustedSSL.getSSLContext().getSocketFactory(), UntrustedSSL.getTrustManager());
        }

        if (config.getSslContext() != null)
            okHttpClientBuilder.sslSocketFactory(config.getSslContext().getSocketFactory());

        if (config.getHostNameVerifier() != null)
            okHttpClientBuilder.hostnameVerifier(config.getHostNameVerifier());
        if (HttpLoggingFilter.isLoggingEnabled()) {
            okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        okHttpClientBuilder.connectionPool(getConnectionPool());
        client = okHttpClientBuilder.build();
        clientReq = new Request.Builder();
        populateHeaders(request);
        populateQueryParams(request);
    }

    /**
     * Create ConnectionPool optimized for short lived client with little chance to reuse connections.
     */
    private ConnectionPool getConnectionPool() {
        int maxIdleConnections = 0;
        // OkHttp creates "OkHttp ConnectionPool" thread per every ConnectionPool created to mange its connections. It
        // lives as long as the last connection made through it + its keep alive timeout. By default that it 5 min which
        // makes no sense for openstack4j since the connections or pools are not reused (after HttpCommand completion,
        // at least). Setting strict keepAlive duration here so the connections and threads does not hang around longer
        // than necessary.
        int keepAliveDuration = 500;
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.MILLISECONDS);
    }

    /**
     * Executes the command and returns the Response.
     */
    public Response execute() throws Exception {
        RequestBody body = null;
        if (request.getEntity() != null) {
            if (InputStream.class.isAssignableFrom(request.getEntity().getClass())) {
                byte[] content = ByteStreams.toByteArray((InputStream) request.getEntity());
                body = RequestBody.create(MediaType.parse(request.getContentType()), content);
            } else {
                String content = ObjectMapperSingleton.getContext(request.getEntity().getClass()).writer().writeValueAsString(request.getEntity());
                body = RequestBody.create(MediaType.parse(request.getContentType()), content);
            }
        } else if (request.hasJson()) {
            body = RequestBody.create(MediaType.parse(ClientConstants.CONTENT_TYPE_JSON), request.getJson());
        }
        //Added to address https://github.com/square/okhttp/issues/751
        //Set body as empty byte array if request is POST or PUT and body is sent as null
        if ((request.getMethod() == HttpMethod.POST || request.getMethod() == HttpMethod.PUT) && body == null) {
            body = RequestBody.create(null, Util.EMPTY_BYTE_ARRAY);
        }
        clientReq.method(request.getMethod().name(), body);
        Call call = client.newCall(clientReq.build());
        return call.execute();
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
     * @return increment the retry count and returns self
     */
    public HttpCommand<R> incrementRetriesAndReturn() {
        initialize();
        retries++;
        return this;
    }

    public HttpRequest<R> getRequest() {
        return request;
    }

    private void populateQueryParams(HttpRequest<R> request) {
        clientReq.url(request.getUrl());
    }

    private void populateHeaders(HttpRequest<R> request) {

        if (!request.hasHeaders()) return;

        for (Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
            clientReq.addHeader(h.getKey(), String.valueOf(h.getValue()));
        }
    }
}
