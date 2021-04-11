package io.github.saoxuequ.http.request.common;

import java.io.InputStream;

public interface Readable<T> {

    T read(InputStream inputStream);
}
