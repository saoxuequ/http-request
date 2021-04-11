package io.github.saoxuequ.http.request.smooth;

import io.github.saoxuequ.http.request.common.Readable;
import io.github.saoxuequ.http.request.core.HttpExecutor;
import io.github.saoxuequ.http.request.utils.Lists;
import io.github.saoxuequ.http.request.utils.UriTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractInvoker<R, S> {
    private final HttpExecutor httpExecutor;
    private final CookieProvider cookieProvider;
    private final String method;
    private final UriTemplate urlTemplate;
    private final Readable<R> responseReader;
    private final Map<String, List<String>> headers = new HashMap<>();
    private int readTimeout = 5000;
    private int connTimeout = 5000;

    public AbstractInvoker(HttpExecutor httpExecutor, CookieProvider cookieProvider,
                           String method, UriTemplate urlTemplate, Readable<R> responseReader) {
        this.httpExecutor = httpExecutor;
        this.cookieProvider = cookieProvider;
        this.method = method;
        this.urlTemplate = urlTemplate;
        this.responseReader = responseReader;
    }

    protected abstract S self();

    public S addHeader(String name, String val) {
        this.headers.computeIfAbsent(name, (n) -> {
            return Lists.newArrayList();
        }).add(val);
        return self();
    }

    public S addHeader(String name, List<String> vals) {
        this.headers.computeIfAbsent(name, (n) -> {
            return Lists.newArrayList();
        }).addAll(vals);
        return self();
    }

    public S setHeader(String name, String val) {
        this.headers.put(name, Lists.newArrayList(val));
        return self();
    }

    public S setHeader(String name, List<String> vals) {
        this.headers.put(name, vals);
        return self();
    }

    public S setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return self();
    }

    public S setConnTimeout(int connTimeout) {
        this.connTimeout = connTimeout;
        return self();
    }

    public S setCookies(List<String> cookies) {
        this.headers.put(CookieProvider.COOKIE_KEY, cookies);
        return self();
    }

    protected HttpExecutor getHttpExecutor() {
        return httpExecutor;
    }

    protected CookieProvider getCookieProvider() {
        return cookieProvider;
    }

    protected Map<String, List<String>> getHeaders() {
        return headers;
    }

    protected int getReadTimeout() {
        return readTimeout;
    }

    protected int getConnTimeout() {
        return connTimeout;
    }

    protected String getMethod() {
        return method;
    }

    protected UriTemplate getUrlTemplate() {
        return urlTemplate;
    }

    protected Readable<R> getResponseReader() {
        return responseReader;
    }
}
