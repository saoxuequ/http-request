package io.github.saoxuequ.http.request.light.reader;

import io.github.saoxuequ.http.request.common.Readable;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class StringResponse implements Readable<String> {
    @Override
    public String read(InputStream inputStream) {
        try (InputStream is = inputStream) {
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
