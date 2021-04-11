package io.github.saoxuequ.http.request.common;

import java.io.OutputStream;

public interface Writeable {

    void write(OutputStream outputStream);
}
