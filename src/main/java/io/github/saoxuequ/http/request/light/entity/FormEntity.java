package io.github.saoxuequ.http.request.light.entity;

import io.github.saoxuequ.http.request.utils.Joiner;
import io.github.saoxuequ.http.request.core.Entity;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

public class FormEntity extends Entity<Map<String, String>> {
    private final Joiner BODY_STR_JOINER = Joiner.on("&");

    @Override
    public void write(OutputStream outputStream) {
        String bodyStr = BODY_STR_JOINER.join(
                getEntity().entrySet().stream().map((entry) -> {
                    try {
                        return entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList()));

        try (OutputStream os = outputStream) {
            IOUtils.write(bodyStr, os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
