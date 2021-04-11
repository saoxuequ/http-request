package io.github.saoxuequ.http.request.light.reader;

import java.io.IOException;
import java.io.InputStream;

import io.github.saoxuequ.http.request.common.Readable;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JsonArrayResponse implements Readable<JSONArray> {
    @Override
    public JSONArray read(InputStream inputStream) {
        try (InputStream is = inputStream) {
            return JSON.parseArray(IOUtils.toString(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
