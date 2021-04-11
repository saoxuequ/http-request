package io.github.saoxuequ.http.request.core;

import io.github.saoxuequ.http.request.common.Writeable;

public abstract class Entity<T> implements Writeable {

    private T entity;

    protected T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
