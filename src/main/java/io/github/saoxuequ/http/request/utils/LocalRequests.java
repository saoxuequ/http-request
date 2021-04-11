package io.github.saoxuequ.http.request.utils;

import io.github.saoxuequ.http.request.core.HttpExecutor;
import io.github.saoxuequ.http.request.light.UrlConnectionHttpExecutor;
import io.github.saoxuequ.http.request.smooth.builder.EntityInvokerFactory;
import io.github.saoxuequ.http.request.smooth.builder.InvokerFactory;
import io.github.saoxuequ.http.request.smooth.builder.InvokerFactoryFactory;

public class LocalRequests {

    private static final HttpExecutor httpExecutor = new UrlConnectionHttpExecutor();

    public static InvokerFactory get(String url) {
        return new InvokerFactoryFactory(httpExecutor, CookieProviders.getCookieProvider()).get(url);
    }

    public static InvokerFactory delete(String url) {
        return new InvokerFactoryFactory(httpExecutor, CookieProviders.getCookieProvider()).delete(url);
    }

    public static EntityInvokerFactory post(String url) {
        return new InvokerFactoryFactory(httpExecutor, CookieProviders.getCookieProvider()).post(url);
    }

    public static EntityInvokerFactory put(String url) {
        return new InvokerFactoryFactory(httpExecutor, CookieProviders.getCookieProvider()).put(url);
    }

    public static EntityInvokerFactory patch(String url) {
        return new InvokerFactoryFactory(httpExecutor, CookieProviders.getCookieProvider()).patch(url);
    }

    public static EntityInvokerFactory newEntityConfigurator(String httpMethod, String url) {
        return new EntityInvokerFactory(httpExecutor, CookieProviders.getCookieProvider(),
                httpMethod, UriTemplate.of(url));
    }

    public static EntityInvokerFactory newConfigurator(String httpMethod, String url) {
        return new EntityInvokerFactory(httpExecutor, CookieProviders.getCookieProvider(),
                httpMethod, UriTemplate.of(url));
    }

}
