package io.github.saoxuequ.http.request.utils;


import io.github.saoxuequ.cookie.provider.Cookie;
import io.github.saoxuequ.cookie.provider.CookieCriterion;
import io.github.saoxuequ.cookie.provider.CookieProvider;
import io.github.saoxuequ.cookie.provider.utils.OsxChromeCookieProviders;

import java.util.stream.Collectors;

public class CookieProviders {

    private static class Single {
        private static final CookieProvider cookieProvider = newCookieProvider();

        private static CookieProvider newCookieProvider() {
            try {
                return OsxChromeCookieProviders.newOsxChromeCookieProviderBySystemEnv();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static io.github.saoxuequ.http.request.smooth.CookieProvider getCookieProvider() {
        return uri -> {
            return Single.cookieProvider.provide(new CookieCriterion(uri.getHost(), uri.getPath()))
                    .stream().map(Cookie::toString).collect(Collectors.toList());
        };
    }
}
