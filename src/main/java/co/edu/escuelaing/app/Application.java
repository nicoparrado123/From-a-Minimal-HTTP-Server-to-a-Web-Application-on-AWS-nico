package co.edu.escuelaing.app;

import static co.edu.escuelaing.webframework.WebFramework.*;

public class Application {

    public static void main(String[] args) throws Exception {
        staticfiles("/webroot");

        String greetingPrefix = System.getenv().getOrDefault("GREETING_PREFIX", "Hello");
        String appEnv = System.getenv().getOrDefault("APP_ENV", "development");

        get("/hello", (req, resp) -> {
            String name = req.getValue("name");
            if (name == null || name.isBlank()) name = "world";
            return greetingPrefix + " " + name;
        });

        get("/pi", (req, resp) -> String.valueOf(Math.PI));

        if (appEnv.equals("development")) {
            get("/shutdown", (req, resp) -> {
                stop();
                return "Server will stop after this response.";
            });
        }

        start();
    }
}
