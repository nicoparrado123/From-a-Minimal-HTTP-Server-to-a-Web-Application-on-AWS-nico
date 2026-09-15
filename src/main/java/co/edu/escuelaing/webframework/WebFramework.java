package co.edu.escuelaing.webframework;

public class WebFramework {
    private static final Router router = new Router();
    private static final StaticFileService staticFileService = new StaticFileService();

    public static void get(String path, Route route) {
        router.addRoute(path, route);
    }

    public static void staticfiles(String path) {
        staticFileService.setStaticFilesPath(path);
    }

    public static void start() throws Exception {
        String portValue = System.getenv("PORT");
        int port = (portValue == null || portValue.isBlank()) ? 8080 : Integer.parseInt(portValue);
        HttpServer.start(port);
    }

    public static void stop() {
        HttpServer.stop();
    }
}
