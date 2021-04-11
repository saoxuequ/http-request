package io.github.saoxuequ.http.request.utils;

import java.util.Iterator;
import java.util.List;

public class Joiner {

    private final String separator;

    public Joiner(String separator) {
        this.separator = separator;
    }

    public static Joiner on(String separator) {
        return new Joiner(separator);
    }

    public String join(List<String> parts) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = parts.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }
}
