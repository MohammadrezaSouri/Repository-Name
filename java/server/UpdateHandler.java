package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.UserDAO;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateHandler implements HttpHandler {
    private UpdateHandler() {}
    private final static UpdateHandler updateHandler = new UpdateHandler();
    public static UpdateHandler getUpdateHandler() {
        return updateHandler;
    }

    private final static ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("PUT")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        String phoneNumber = QueryUtil.getQueryParam(exchange, "phoneNumber");
        if (phoneNumber == null) {
            exchange.sendResponseHeaders(400, -1);
            return;
        }
        User userUpdate = objectMapper.readValue(exchange.getRequestBody(), User.class);
        try {
            boolean update = UserDAO.getInstance().updateUser(userUpdate, phoneNumber);
            exchange.sendResponseHeaders(update? 200 : 404 ,-1);
        } catch (SQLException e) {
            exchange.sendResponseHeaders(500, -1);
        }
    }
}
