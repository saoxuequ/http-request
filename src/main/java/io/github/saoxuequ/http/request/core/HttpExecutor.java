package io.github.saoxuequ.http.request.core;

import java.util.concurrent.Future;

public interface HttpExecutor {

    <R> R execute(Request<R> request, int connectTimeout, int readTimeout);

    <R> Future<R> executeAsync(Request<R> request, int connectTimeout, int readTimeout);

    <E, R> R execute(EntityRequest<E, R> request, int connectTimeout, int readTimeout);

    <E, R> Future<R> executeAsync(EntityRequest<E, R> request, int connectTimeout, int readTimeout);
}
