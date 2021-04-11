package io.github.saoxuequ.http.request.light;

import io.github.saoxuequ.http.request.core.EntityRequest;
import io.github.saoxuequ.http.request.core.Request;
import io.github.saoxuequ.http.request.utils.HeaderUtils;
import io.github.saoxuequ.http.request.utils.NamedThreadFactory;
import io.github.saoxuequ.http.request.utils.Preconditions;
import io.github.saoxuequ.http.request.core.HttpExecutor;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class UrlConnectionHttpExecutor implements HttpExecutor {

    private final ExecutorService asyncPool;

    public UrlConnectionHttpExecutor(ExecutorService asyncPool) {
        this.asyncPool = asyncPool;
    }

    public UrlConnectionHttpExecutor() {
        this(new ThreadPoolExecutor(
                2, 2, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100),
                new NamedThreadFactory("http-async-pool"),
                new ThreadPoolExecutor.AbortPolicy()));
    }

    @Override
    public <P, R> R execute(EntityRequest<P, R> request, int connectTimeout, int readTimeout) {
        try {
            URI uri = request.getUri();
            HttpURLConnection connection = openHttpConnection(uri.toURL());

            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            connection.setDoInput(true);

            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(4096);

            try {
                connection.setRequestMethod(request.getMethod());
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            }

            setHeaders(uri, request.getHeaders(), connection);
            connection.connect();

            if (connection.getDoOutput()) {
                try (OutputStream os = connection.getOutputStream()) {
                    request.getEntity().write(os);
                }
            }

            int code = connection.getResponseCode();
            String reason = connection.getRequestMethod();
            if (code != 200) {
                throwException(connection, code, reason);
            }
            try (InputStream is = connection.getInputStream()) {
                Preconditions.checkState(is != null, "response stream is null.");
                return request.getResponseReader().read(is);
            }
        } catch (Exception e) {
            throw new RuntimeException(request.getUri().toString(), e);
        }
    }

    private String throwException(HttpURLConnection connection, int code, String reason) {
        String errorResponse = "";
        try (InputStream is = connection.getInputStream() == null ? connection.getErrorStream() :
                connection.getInputStream()) {
            if (is != null) {
                errorResponse = IOUtils.toString(is);
            }
            throw new IllegalStateException(String.format("error code:%s, reason:%s, response: %s", code, reason,
                    errorResponse));
        } catch (Exception e) {
            throw new IllegalStateException(String.format("error code:%s, reason:%s, response: %s", code, reason,
                    errorResponse), e);
        }
    }

    private HttpURLConnection openHttpConnection(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        if (!HttpURLConnection.class.isInstance(urlConnection)) {
            throw new RuntimeException("Expected type for [" + url + "] is [HttpURLConnection] but got: " + urlConnection);
        }
        return (HttpURLConnection) urlConnection;
    }

    private void setHeaders(URI uri, Map<String, List<String>> headers, HttpURLConnection conn) {
        headers.forEach((k, vs) -> {
            conn.setRequestProperty(k, HeaderUtils.assembleHeaderValue(vs));
        });
    }

    @Override
    public <P, R> Future<R> executeAsync(EntityRequest<P, R> request, int connectTimeout, int readTimeout) {
        return asyncPool.submit(() -> execute(request, connectTimeout, readTimeout));
    }

    @Override
    public <R> R execute(Request<R> request, int connectTimeout, int readTimeout) {
        try {
            URI uri = request.getUri();
            HttpURLConnection connection = openHttpConnection(uri.toURL());

            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);

            connection.setDoInput(true);

            connection.setDoOutput(false);

            try {
                connection.setRequestMethod(request.getMethod());
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            }

            setHeaders(uri, request.getHeaders(), connection);
            connection.connect();

            int code = connection.getResponseCode();
            String reason = connection.getRequestMethod();
            if (code != 200) {
                throwException(connection, code, reason);
            }
            try (InputStream is = connection.getInputStream()) {
                Preconditions.checkState(is != null, "response stream is null.");
                return request.getResponseReader().read(is);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <R> Future<R> executeAsync(Request<R> request, int connectTimeout, int readTimeout) {
        return asyncPool.submit(() -> execute(request, connectTimeout, readTimeout));
    }
}
