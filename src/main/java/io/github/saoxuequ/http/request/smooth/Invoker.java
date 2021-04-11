package io.github.saoxuequ.http.request.smooth;

import io.github.saoxuequ.http.request.common.Readable;
import io.github.saoxuequ.http.request.core.HttpExecutor;
import io.github.saoxuequ.http.request.core.Request;
import io.github.saoxuequ.http.request.utils.UriTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class Invoker<R> extends AbstractInvoker<R, Invoker<R>> {

    public Invoker(HttpExecutor httpExecutor, CookieProvider cookieProvider,
                   String method, UriTemplate urlTemplate,
                   Readable<R> responseReader) {
        super(httpExecutor, cookieProvider, method, urlTemplate, responseReader);
    }

    @Override
    protected Invoker<R> self() {
        return this;
    }

    public R invoke(List<String> urlParams) {
        URI uri = getUrlTemplate().generateUri(urlParams);
        if (getCookieProvider() != null) {
            setCookies(getCookieProvider().provide(uri));
        }
        Request<R> request = new Request<>(getMethod(),
                uri,
                getHeaders(),
                getResponseReader());
        return getHttpExecutor().execute(request, getConnTimeout(), getReadTimeout());
    }

    public R invoke() {
        URI uri = getUrlTemplate().generateUri(Collections.emptyList());
        if (getCookieProvider() != null) {
            setCookies(getCookieProvider().provide(uri));
        }
        Request<R> request = new Request<>(getMethod(),
                uri,
                getHeaders(),
                getResponseReader());
        return getHttpExecutor().execute(request, getConnTimeout(), getReadTimeout());
    }
}
