package io.github.saoxuequ.http.request.utils;

import io.github.saoxuequ.http.request.core.HttpExecutor;
import io.github.saoxuequ.http.request.light.UrlConnectionHttpExecutor;
import io.github.saoxuequ.http.request.smooth.CookieProvider;
import io.github.saoxuequ.http.request.smooth.builder.EntityInvokerFactory;
import io.github.saoxuequ.http.request.smooth.builder.InvokerFactory;
import io.github.saoxuequ.http.request.smooth.builder.InvokerFactoryFactory;

import java.util.LinkedList;

public class Requests {

    private static final CookieProvider cookieProvider = uri -> new LinkedList<>();
    private static final HttpExecutor httpExecutor = new UrlConnectionHttpExecutor();

    public static InvokerFactory get(String url) {
        return new InvokerFactoryFactory(httpExecutor, cookieProvider).get(url);
    }

    public static InvokerFactory delete(String url) {
        return new InvokerFactoryFactory(httpExecutor, cookieProvider).delete(url);
    }

    public static EntityInvokerFactory post(String url) {
        return new InvokerFactoryFactory(httpExecutor, cookieProvider).post(url);
    }

    public static EntityInvokerFactory put(String url) {
        return new InvokerFactoryFactory(httpExecutor, cookieProvider).put(url);
    }

    public static EntityInvokerFactory patch(String url) {
        return new InvokerFactoryFactory(httpExecutor, cookieProvider).patch(url);
    }

    public static EntityInvokerFactory newEntityConfigurator(String httpMethod, String url) {
        return new EntityInvokerFactory(httpExecutor, cookieProvider, httpMethod, UriTemplate.of(url));
    }

    public static EntityInvokerFactory newConfigurator(String httpMethod, String url) {
        return new EntityInvokerFactory(httpExecutor, cookieProvider, httpMethod, UriTemplate.of(url));
    }
}
