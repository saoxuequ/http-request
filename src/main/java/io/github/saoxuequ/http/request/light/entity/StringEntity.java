package io.github.saoxuequ.http.request.light.entity;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import io.github.saoxuequ.http.request.core.Entity;

public class StringEntity extends Entity<String> {

    @Override
    public void write(OutputStream outputStream) {
        try (OutputStream os = outputStream) {
            IOUtils.write(getEntity(), os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
