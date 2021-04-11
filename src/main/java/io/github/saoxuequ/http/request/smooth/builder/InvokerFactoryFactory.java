package io.github.saoxuequ.http.request.smooth.builder;

import io.github.saoxuequ.http.request.smooth.CookieProvider;
import io.github.saoxuequ.http.request.common.HttpMethods;
import io.github.saoxuequ.http.request.core.HttpExecutor;
import io.github.saoxuequ.http.request.utils.UriTemplate;

public class InvokerFactoryFactory {

    private final HttpExecutor httpExecutor;
    private final CookieProvider cookieProvider;

    public InvokerFactoryFactory(HttpExecutor httpExecutor,
                                 CookieProvider cookieProvider) {
        this.httpExecutor = httpExecutor;
        this.cookieProvider = cookieProvider;
    }

    public InvokerFactory get(String url) {
        return new InvokerFactory(httpExecutor, cookieProvider,
                HttpMethods.GET, UriTemplate.of(url));
    }

    public EntityInvokerFactory post(String url) {
        return new EntityInvokerFactory(httpExecutor, cookieProvider,
                HttpMethods.POST, UriTemplate.of(url));
    }

    public EntityInvokerFactory put(String url) {
        return new EntityInvokerFactory(httpExecutor, cookieProvider,
                HttpMethods.PUT, UriTemplate.of(url));
    }

    public InvokerFactory delete(String url) {
        return new InvokerFactory(httpExecutor, cookieProvider,
                HttpMethods.DELETE, UriTemplate.of(url));
    }

    public EntityInvokerFactory patch(String url) {
        return new EntityInvokerFactory(httpExecutor, cookieProvider,
                HttpMethods.PATCH, UriTemplate.of(url));
    }
}
