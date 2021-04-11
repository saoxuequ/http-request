package io.github.saoxuequ.http.request.smooth.builder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import io.github.saoxuequ.http.request.core.HttpExecutor;
import io.github.saoxuequ.http.request.light.reader.FileResponse;
import io.github.saoxuequ.http.request.light.reader.FixedFileResponse;
import io.github.saoxuequ.http.request.light.reader.JsonObjectResponse;
import io.github.saoxuequ.http.request.light.reader.JsonResponse;
import io.github.saoxuequ.http.request.light.reader.StringResponse;
import io.github.saoxuequ.http.request.light.reader.TypedJsonResponse;
import io.github.saoxuequ.http.request.smooth.CookieProvider;
import io.github.saoxuequ.http.request.smooth.Invoker;
import io.github.saoxuequ.http.request.utils.UriTemplate;

import java.io.File;

public class InvokerFactory {

    private final HttpExecutor httpExecutor;
    private final CookieProvider cookieProvider;

    private final String method;
    private final UriTemplate urlTemplate;

    public InvokerFactory(HttpExecutor httpExecutor,
                          CookieProvider cookieProvider,
                          String method, UriTemplate urlTemplate) {
        this.httpExecutor = httpExecutor;
        this.cookieProvider = cookieProvider;
        this.method = method;
        this.urlTemplate = urlTemplate;
    }

    public Invoker<String> forString() {
        return new Invoker<>(httpExecutor, cookieProvider, method, urlTemplate, new StringResponse());
    }

    public <R> Invoker<R> forJson(Class<R> type) {
        return new Invoker<>(httpExecutor, cookieProvider, method, urlTemplate, new JsonResponse<>(type));
    }

    public <R> Invoker<R> forJson(TypeReference<R> type) {
        return new Invoker<>(httpExecutor, cookieProvider, method, urlTemplate, new TypedJsonResponse<>(type));
    }

    public Invoker<JSONObject> forJson() {
        return new Invoker<>(httpExecutor, cookieProvider, method, urlTemplate, new JsonObjectResponse());
    }

    public Invoker<JSONObject> forJsonArray() {
        return new Invoker<>(httpExecutor, cookieProvider, method, urlTemplate, new JsonObjectResponse());
    }

    public Invoker<File> forFile() {
        return new Invoker<>(httpExecutor, cookieProvider, method, urlTemplate, new FileResponse());
    }

    public Invoker<File> forFile(String path) {
        return new Invoker<>(httpExecutor, cookieProvider, method, urlTemplate, new FixedFileResponse(path));
    }
}
