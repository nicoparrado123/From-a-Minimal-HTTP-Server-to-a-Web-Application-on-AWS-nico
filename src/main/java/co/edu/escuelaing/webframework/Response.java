package co.edu.escuelaing.webframework;

public class Response {
    private int statusCode = 200;
    private String contentType = "text/plain";

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
