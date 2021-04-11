package io.github.saoxuequ.http.request.smooth;

import java.net.URI;
import java.util.List;

public interface CookieProvider {

    String COOKIE_KEY = "Cookie";

    List<String> provide(URI uri);
}
