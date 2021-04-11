package io.github.saoxuequ.http.request.smooth.builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.saoxuequ.http.request.smooth.CookieProvider;
import io.github.saoxuequ.http.request.smooth.EntityInvoker;
import io.github.saoxuequ.http.request.common.Readable;
import io.github.saoxuequ.http.request.core.Entity;
import io.github.saoxuequ.http.request.core.HttpExecutor;
import io.github.saoxuequ.http.request.light.entity.FormEntity;
import io.github.saoxuequ.http.request.light.entity.JsonEntity;
import io.github.saoxuequ.http.request.light.entity.StringEntity;
import io.github.saoxuequ.http.request.light.reader.FileResponse;
import io.github.saoxuequ.http.request.light.reader.FixedFileResponse;
import io.github.saoxuequ.http.request.light.reader.JsonArrayResponse;
import io.github.saoxuequ.http.request.light.reader.JsonObjectResponse;
import io.github.saoxuequ.http.request.light.reader.JsonResponse;
import io.github.saoxuequ.http.request.light.reader.StringResponse;
import io.github.saoxuequ.http.request.utils.UriTemplate;

import java.io.File;
import java.util.Map;

public class EntityInvokerFactory {

    private final HttpExecutor httpExecutor;
    private final CookieProvider cookieProvider;

    private final String method;
    private final UriTemplate urlTemplate;

    public EntityInvokerFactory(HttpExecutor httpExecutor,
                                CookieProvider cookieProvider,
                                String method, UriTemplate urlTemplate) {
        this.httpExecutor = httpExecutor;
        this.cookieProvider = cookieProvider;
        this.method = method;
        this.urlTemplate = urlTemplate;
    }

    public <R> EntityInvoker<Map<String, String>, R> form4Json(Class<R> type) {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new FormEntity(), new JsonResponse<>(type)
        );
    }

    public EntityInvoker<Map<String, String>, JSONObject> form4Json() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new FormEntity(), new JsonObjectResponse()
        );
    }

    public EntityInvoker<Map<String, String>, JSONArray> form4JsonArray() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new FormEntity(), new JsonArrayResponse()
        );
    }

    public EntityInvoker<String, JSONObject> formString4Json() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new StringEntity(), new JsonObjectResponse()
        );
    }

    public EntityInvoker<String, JSONArray> formString4JsonArray() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new StringEntity(), new JsonArrayResponse()
        );
    }

    public EntityInvoker<String, String> formString4String() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new StringEntity(), new StringResponse()
        );
    }

    public EntityInvoker<String, String> string4String() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new StringEntity(), new StringResponse()
        );
    }

    public EntityInvoker<Map<String, String>, File> form4File() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new FormEntity(), new FileResponse()
        );
    }

    public EntityInvoker<Map<String, String>, File> form4File(String path) {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new FormEntity(), new FixedFileResponse(path)
        );
    }

    public EntityInvoker<Map<String, String>, String> form4String() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new FormEntity(), new StringResponse()
        );
    }

    public EntityInvoker<Object, String> json4String() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new JsonEntity(), new StringResponse()
        );
    }

    public EntityInvoker<Object, File> json4File() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new JsonEntity(), new FileResponse()
        );
    }

    public EntityInvoker<Object, File> json4File(String path) {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new JsonEntity(), new FixedFileResponse(path)
        );
    }

    public <R> EntityInvoker<Object, R> json4Json(Class<R> type) {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new JsonEntity(), new JsonResponse<>(type)
        );
    }

    public EntityInvoker<Object, JSONObject> json4Json() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new JsonEntity(), new JsonObjectResponse()
        );
    }

    public EntityInvoker<Object, JSONArray> json4JsonArray() {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                new JsonEntity(), new JsonArrayResponse()
        );
    }

    public <E, R> EntityInvoker<E, R> newInvoker(Entity<E> entity, Readable<R> reader) {
        return new EntityInvoker<>(
                httpExecutor, cookieProvider, method, urlTemplate,
                entity, reader
        );
    }
}
