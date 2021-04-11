package io.github.saoxuequ.http.request.light.reader;

import io.github.saoxuequ.http.request.common.Readable;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FixedFileResponse implements Readable<File> {
    private final String path;

    public FixedFileResponse(String path) {
        this.path = path;
    }

    @Override
    public File read(InputStream inputStream) {
        File f = new File(path);
        try (OutputStream os = FileUtils.openOutputStream(f);
             InputStream is = inputStream;) {
            IOUtils.copy(is, os);
            return f;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
