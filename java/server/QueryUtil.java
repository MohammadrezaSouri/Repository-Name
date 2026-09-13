package server;

import com.sun.net.httpserver.HttpExchange;

public class QueryUtil {
    private QueryUtil(){}
    public static String getQueryParam(HttpExchange exchange, String key){
        String query = exchange.getRequestURI().getQuery();
        if (query == null){
            return null;
        }
        String[] params = query.split("&");
        for (String param : params) {
            if (param.split("=")[0].equals(key)){
                return param.split("=")[1];
            }
        }
        return null;
    }
}
