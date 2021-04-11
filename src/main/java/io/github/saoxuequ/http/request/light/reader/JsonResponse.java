package io.github.saoxuequ.http.request.light.reader;

import com.alibaba.fastjson.JSONObject;
import io.github.saoxuequ.http.request.common.Readable;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class JsonResponse<T> implements Readable<T> {

    private final Class<T> type;

    public JsonResponse(Class<T> type) {
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
