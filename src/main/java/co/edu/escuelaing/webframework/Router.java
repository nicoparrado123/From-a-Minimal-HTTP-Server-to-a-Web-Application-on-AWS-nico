package co.edu.escuelaing.webframework;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private final Map<String, Route> routes = new HashMap<>();

    public void addRoute(String path, Route route) {
        routes.put(path, route);
    }

    public Route getRoute(String path) {
        return routes.get(path);
    }
}
