package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.UserDAO;


import java.io.IOException;
import java.sql.SQLException;

public class DeleteHandler implements HttpHandler {
    private DeleteHandler(){}
    private final static DeleteHandler deleteHandler = new DeleteHandler();
    public static DeleteHandler getDeleteHandler(){return deleteHandler;}

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("DELETE")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        String phoneNumber = QueryUtil.getQueryParam(exchange, "phoneNumber");
        if (phoneNumber == null) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        try {
            boolean deleted = UserDAO.getInstance().deleteUser(phoneNumber);
            exchange.sendResponseHeaders(deleted ? 200 : 404, -1);
        } catch (SQLException e) {
            exchange.sendResponseHeaders(500, -1);
        }
    }
}
