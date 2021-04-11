package io.github.saoxuequ.http.request.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 类似 slf4j 的 string 模版
 */
public class UriTemplate {
    private final String template;

    public UriTemplate(String template) {
        Preconditions.checkArgument(template != null, "tempate is null");
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    protected String format(List<String> args) {
        if (args == null || args.size() == 0) {
            return getTemplate();
        }
        StringBuilder sb = new StringBuilder(getTemplate());

        if (args != null && args.size() > 0) {
            for (Object o : args) {
                int i = sb.indexOf("{}");
                if (i == -1) {
                    break;
                }
                sb.delete(i, i + 2);
                sb.insert(i, o);
            }
        }

        return sb.toString();
    }

    public URI generateUri(List<String> args) {
        try {
            return new URI(format(args));
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    public static UriTemplate of(String template) {
        return new UriTemplate(template);
    }
}
