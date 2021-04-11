package io.github.saoxuequ.http.request.core;

import io.github.saoxuequ.http.request.common.Readable;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class Request<R> {

    private final String method;
    private final URI uri;

    private final Map<String, List<String>> headers;

    private final Readable<R> responseReader;

    public Request(String method, URI uri, Map<String, List<String>> headers, Readable<R> responseReader) {
        this.method = method;
        this.uri = uri;
        this.headers = headers;
        this.responseReader = responseReader;
    }

    public String getMethod() {
        return method;
    }

    public URI getUri() {
        return uri;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public Readable<R> getResponseReader() {
        return responseReader;
    }
}
