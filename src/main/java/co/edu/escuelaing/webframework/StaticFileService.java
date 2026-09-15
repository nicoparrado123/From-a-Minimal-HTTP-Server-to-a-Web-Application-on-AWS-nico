package co.edu.escuelaing.webframework;

public class StaticFileService {
    private String staticFilesPath = "/webroot";

    public void setStaticFilesPath(String path) {
        this.staticFilesPath = path;
    }

    public String getStaticFilesPath() {
        return staticFilesPath;
    }

    // TODO: leer el recurso desde resources/webroot y devolver los bytes
}
