package co.edu.escuelaing.webframework;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private final Map<String, String> queryParams = new HashMap<>();
    private final String path;

    public Request(String path) {
        this.path = path;
    }

    public String getValue(String name) {
        return queryParams.get(name);
    }

    public void addQueryParam(String name, String value) {
        queryParams.put(name, value);
    }

    public String getPath() {
        return path;
    }
}
