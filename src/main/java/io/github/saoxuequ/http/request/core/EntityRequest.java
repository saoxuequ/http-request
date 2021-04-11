package io.github.saoxuequ.http.request.core;

import io.github.saoxuequ.http.request.common.Readable;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class EntityRequest<E, R> extends Request<R> {
    private final Entity<E> entity;

    public EntityRequest(String method, URI url,
                         Map<String, List<String>> headers,
                         Entity<E> entity,
                         Readable<R> responseReader) {
        super(method, url, headers, responseReader);
        this.entity = entity;
    }

    public Entity<E> getEntity() {
        return entity;
    }
}
