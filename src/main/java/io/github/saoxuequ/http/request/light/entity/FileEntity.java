package io.github.saoxuequ.http.request.light.entity;

import io.github.saoxuequ.http.request.core.Entity;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileEntity extends Entity<File> {
    @Override
    public void write(OutputStream outputStream) {
        try (OutputStream os = outputStream;
             InputStream is = new FileInputStream(getEntity())) {
            IOUtils.copy(is, os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}