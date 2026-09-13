package View;

import com.sun.net.httpserver.HttpServer;
import server.DeleteHandler;
import server.RegisterHandler;
import server.UpdateHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    static void main() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/users/register", RegisterHandler.getRegisterHandler());
        server.createContext("/users/update", UpdateHandler.getUpdateHandler());
        server.createContext("/users/delete", DeleteHandler.getDeleteHandler());

        server.setExecutor(null);
        server.start();

        IO.println("server started");
    }
}
