package io.github.saoxuequ.http.request.light.entity;

import com.alibaba.fastjson.JSONObject;
import io.github.saoxuequ.http.request.core.Entity;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;

public class JsonEntity extends Entity<Object> {
    @Override
    public void write(OutputStream outputStream) {
        try (OutputStream os = outputStream) {
            IOUtils.write(JSONObject.toJSONString(getEntity()), os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

