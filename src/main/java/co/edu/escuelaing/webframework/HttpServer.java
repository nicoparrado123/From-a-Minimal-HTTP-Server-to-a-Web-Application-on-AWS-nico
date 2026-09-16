package co.edu.escuelaing.webframework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private static boolean running = false;
    private static Router router;
    private static StaticFileService staticFileService;

    public static void setRouter(Router r) { router = r; }
    public static void setStaticFileService(StaticFileService s) { staticFileService = s; }

    public static void start(int port) throws IOException {
        running = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);
            while (running) {
                try (Socket client = serverSocket.accept()) {
                    handleRequest(client);
                }
            }
        }
        System.out.println("Server stopped gracefully.");
    }

    public static void stop() { running = false; }

    private static void handleRequest(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        OutputStream out = client.getOutputStream();

        String requestLine = in.readLine();
        if (requestLine == null || requestLine.isBlank()) return;

        String[] parts = requestLine.split(" ");
        if (parts.length < 2) return;

        String fullPath = parts[1];           // e.g. /hello?name=Pedro
        String path = fullPath;
        String queryString = "";

        int qIdx = fullPath.indexOf('?');
        if (qIdx >= 0) {
            path = fullPath.substring(0, qIdx);
            queryString = fullPath.substring(qIdx + 1);
        }

        Request req = new Request(path);
        if (!queryString.isBlank()) {
            for (String param : queryString.split("&")) {
                String[] kv = param.split("=", 2);
                req.addQueryParam(kv[0], kv.length > 1 ? java.net.URLDecoder.decode(kv[1], "UTF-8") : "");
            }
        }

        Response resp = new Response();
        Route route = router.getRoute(path);

        if (route != null) {
            String body = route.handle(req, resp);
            sendText(out, resp.getStatusCode(), resp.getContentType(), body);
        } else {
            byte[] fileBytes = staticFileService.getResource(path);
            if (fileBytes != null) {
                String mime = staticFileService.getMimeType(path);
                sendBytes(out, 200, mime, fileBytes);
            } else {
                sendText(out, 404, "text/plain", "404 Not Found");
            }
        }
    }

    private static void sendText(OutputStream out, int status, String contentType, String body) throws IOException {
        byte[] bytes = body.getBytes("UTF-8");
        sendBytes(out, status, contentType + "; charset=UTF-8", bytes);
    }

    private static void sendBytes(OutputStream out, int status, String contentType, byte[] body) throws IOException {
        String statusText = status == 200 ? "OK" : "Not Found";
        String header = "HTTP/1.1 " + status + " " + statusText + "\r\n"
                + "Content-Type: " + contentType + "\r\n"
                + "Content-Length: " + body.length + "\r\n"
                + "Connection: close\r\n\r\n";
        out.write(header.getBytes("UTF-8"));
        out.write(body);
        out.flush();
    }
}
