package co.edu.escuelaing.webframework;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class StaticFileService {

    private String staticFilesPath = "/webroot";

    private static final Map<String, String> MIME_TYPES = new HashMap<>();
    static {
        MIME_TYPES.put("html", "text/html");
        MIME_TYPES.put("css",  "text/css");
        MIME_TYPES.put("js",   "application/javascript");
        MIME_TYPES.put("png",  "image/png");
        MIME_TYPES.put("jpg",  "image/jpeg");
        MIME_TYPES.put("jpeg", "image/jpeg");
        MIME_TYPES.put("gif",  "image/gif");
        MIME_TYPES.put("ico",  "image/x-icon");
        MIME_TYPES.put("svg",  "image/svg+xml");
    }

    public void setStaticFilesPath(String path) { this.staticFilesPath = path; }

    public byte[] getResource(String requestPath) {
        String resource = staticFilesPath + requestPath;
        try (InputStream is = getClass().getResourceAsStream(resource)) {
            if (is == null) return null;
            return is.readAllBytes();
        } catch (Exception e) {
            return null;
        }
    }

    public String getMimeType(String path) {
        int dot = path.lastIndexOf('.');
        if (dot < 0) return "application/octet-stream";
        String ext = path.substring(dot + 1).toLowerCase();
        return MIME_TYPES.getOrDefault(ext, "application/octet-stream");
    }
}
