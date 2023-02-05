package org.openstack4j.openstack.heat.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.openstack4j.core.transport.ClientConstants.URI_SEP;

public class TemplateUtils {

    public static String readToString(String filename) throws IOException {
        return readToString(new URL(filename));
    }

    public static String readToString(URL url) throws IOException {
        try (Scanner scanner = new Scanner(url.openStream(), StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    public static URL baseUrl(String url) throws MalformedURLException {
        String baseUrl = url.substring(0, url.lastIndexOf(URI_SEP) + 1);
        if (!baseUrl.endsWith(URI_SEP)) {
            baseUrl += URI_SEP;
        }
        return new URL(baseUrl);
    }

    public static URL normaliseFilePathToUrl(String path)
            throws MalformedURLException, URISyntaxException {
        if (path.startsWith("file:")
                || path.startsWith("http:")
                || path.startsWith("https:")) {
            return new URI(path).toURL();
        } else {
            return new File(path).toURI().toURL();
        }

    }

    public static URL normaliseFilePathToUrl(String baseUrl, String templateName)
            throws MalformedURLException, URISyntaxException {
        if (templateName.startsWith("file:")
                || templateName.startsWith("http:")
                || templateName.startsWith("https:")) {
            return normaliseFilePathToUrl(templateName);
        } else {
            return normaliseFilePathToUrl(baseUrl + templateName);
        }
    }
}
