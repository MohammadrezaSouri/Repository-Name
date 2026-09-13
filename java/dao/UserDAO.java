package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private UserDAO(){}
    private static final UserDAO INSTANCE = new UserDAO();
    public static UserDAO getInstance(){
        return INSTANCE;
    }

    public void saveUser (User user) throws SQLException {
            String sql = "INSERT INTO users (first_name, last_name, phone_number, user_name) VALUES (?, ?, ?, ?)";
            try (Connection connection = DBConnectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3,user.getPhoneNumber());
                preparedStatement.setString(4, user.getUserName());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public  User findByPhoneNumber(String phoneNumber) throws SQLException {
            String sql = "SELECT * FROM users WHERE phone_number = ?";
            try (Connection connection = DBConnectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, phoneNumber);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int userId = resultSet.getInt("id");
                    String firstname = resultSet.getString("first_name");
                    String lastname = resultSet.getString("last_name");
                    String phonenumber = resultSet.getString("phone_number");
                    String username = resultSet.getString("user_name");
                    return new User(userId, firstname, username, lastname, phonenumber);
                }
            }
            return null;
    }
    public boolean deleteUser(String phoneNumber) throws SQLException {
        String sql = "DELETE FROM users WHERE phone_number = ?";
        try (Connection connection = DBConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, phoneNumber);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return true;
            }
        }
        return false;
    }
    public boolean updateUser(User userUpdate, String phoneNumber) throws SQLException {
        String sql = "Update users Set first_name = ?, last_name = ?, user_name = ? where phone_number = ?";
        try (Connection connection = DBConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userUpdate.getFirstName());
            preparedStatement.setString(2, userUpdate.getLastName());
            preparedStatement.setString(3, userUpdate.getUserName());
            preparedStatement.setString(4, phoneNumber);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return true;
            }
        }
        return false;
    }

}

