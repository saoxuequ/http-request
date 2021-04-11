package io.github.saoxuequ.http.request.utils;

import java.util.List;

public class HeaderUtils {

    private HeaderUtils() {
    }

    private static final Joiner HEADER_VALUES_JOINER = Joiner.on("; ");

    public static String assembleHeaderValue(List<String> vs) {
        if (vs == null || vs.size() == 0) {
            return "";
        }

        return HEADER_VALUES_JOINER.join(vs);
    }
}
