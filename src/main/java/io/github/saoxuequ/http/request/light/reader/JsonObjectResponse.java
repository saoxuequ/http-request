package io.github.saoxuequ.http.request.light.reader;

import java.io.IOException;
import java.io.InputStream;

import io.github.saoxuequ.http.request.common.Readable;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSONObject;

public class JsonObjectResponse implements Readable<JSONObject> {
    @Override
    public JSONObject read(InputStream inputStream) {
        try (InputStream is = inputStream) {
            return JSONObject.parseObject(IOUtils.toString(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
