package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.UserDAO;
import model.User;

import java.io.IOException;
import java.sql.SQLException;


public class RegisterHandler implements HttpHandler {
    private RegisterHandler(){}
    private final static RegisterHandler registerHandler = new RegisterHandler();
    public static RegisterHandler getRegisterHandler(){return registerHandler;}

    final static ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("POST")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        User user = mapper.readValue(exchange.getRequestBody(), User.class);
        try {
            UserDAO.getInstance().saveUser(user);
            exchange.sendResponseHeaders(200, -1);
            IO.println("User has been saved");

        } catch (SQLException e) {
            exchange.sendResponseHeaders(500, -1);
        }
    }

}
