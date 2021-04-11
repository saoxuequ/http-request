package io.github.saoxuequ.http.request.light.reader;

import java.io.IOException;
import java.io.InputStream;

import io.github.saoxuequ.http.request.common.Readable;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class TypedJsonResponse<T> implements Readable<T> {

    private final TypeReference<T> type;

    public TypedJsonResponse(TypeReference<T> type) {
        this.type = type;
    }

    @Override
    public T read(InputStream inputStream) {
        try (InputStream is = inputStream) {
            return JSONObject.parseObject(IOUtils.toString(is), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}