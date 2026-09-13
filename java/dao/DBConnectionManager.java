package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {
    private DBConnectionManager(){}
    private final static DBConnectionManager dbConnectionManager = new DBConnectionManager();
    public static DBConnectionManager getInstance(){
        return dbConnectionManager;
    }

    private final static Properties prop = new Properties();

    static {
        try(InputStream inputStream = DBConnectionManager.class.getClassLoader().getResourceAsStream("Database")) {
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Database!", e);
        }
    }
    public static Connection getConnection()throws SQLException {
        return DriverManager.getConnection(
                prop.getProperty("url"),
                prop.getProperty("username"),
                prop.getProperty("password")
        );
    }

}
