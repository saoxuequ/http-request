package io.github.saoxuequ.http.request.smooth;

import io.github.saoxuequ.http.request.common.Readable;
import io.github.saoxuequ.http.request.core.Entity;
import io.github.saoxuequ.http.request.core.EntityRequest;
import io.github.saoxuequ.http.request.core.HttpExecutor;
import io.github.saoxuequ.http.request.utils.UriTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class EntityInvoker<W, R> extends AbstractInvoker<R, EntityInvoker<W, R>> {

    private final Entity<W> requestEntity;

    public EntityInvoker(HttpExecutor httpExecutor, CookieProvider cookieProvider,
                         String method, UriTemplate urlTemplate,
                         Entity<W> requestEntity, Readable<R> responseReader) {
        super(httpExecutor, cookieProvider, method, urlTemplate, responseReader);
        this.requestEntity = requestEntity;
    }

    @Override
    protected EntityInvoker<W, R> self() {
        return this;
    }

    public R invoke(List<String> urlParams, W body) {
        requestEntity.setEntity(body);
        URI uri = getUrlTemplate().generateUri(urlParams);
        if (getCookieProvider() != null) {
            setCookies(getCookieProvider().provide(uri));
        }
        EntityRequest<W, R> request = new EntityRequest<>(getMethod(),
                uri,
                getHeaders(),
                requestEntity,
                getResponseReader());
        return getHttpExecutor().execute(request, getConnTimeout(), getReadTimeout());
    }

    public R invoke(W body) {
        requestEntity.setEntity(body);
        URI uri = getUrlTemplate().generateUri(Collections.emptyList());
        if (getCookieProvider() != null) {
            setCookies(getCookieProvider().provide(uri));
        }
        EntityRequest<W, R> request = new EntityRequest<>(getMethod(),
                uri,
                getHeaders(),
                requestEntity,
                getResponseReader());
        return getHttpExecutor().execute(request, getConnTimeout(), getReadTimeout());
    }
}
