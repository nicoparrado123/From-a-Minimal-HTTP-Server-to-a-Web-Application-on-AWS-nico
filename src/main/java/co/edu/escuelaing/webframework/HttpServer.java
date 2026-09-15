package co.edu.escuelaing.webframework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static boolean running = false;

    public static void start(int port) throws IOException {
        running = true;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port);
            while (running) {
                try (Socket clientSocket = serverSocket.accept()) {
                    // TODO: parsear la request y despachar al Router
                }
            }
        }

        System.out.println("Servidor detenido.");
    }

    public static void stop() {
        running = false;
    }
}
