package business.persistence;

import business.entities.User;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminMapper {
    private Database database;

    public AdminMapper(Database database) {
        this.database = database;
    }

    public List<User> getAllUsers() throws UserException {
        List<User> users = new ArrayList<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT customer_id, name, email, phone_number, balance, role FROM users";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String email = rs.getString("email");
                String role = rs.getString("role");
                String phone_number = rs.getString("phone_number");
                String name = rs.getString("name");
                int balance = rs.getInt("balance");

                User user = new User(customerId, email, role, phone_number, name, balance);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return users;
    }

    public User getUser(int userId) throws UserException {
        User user;
        try (Connection connection = database.connect()) {
            String sql = "SELECT name, email, phone_number, balance, role FROM users WHERE customer_id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                String role = rs.getString("role");
                String phone_number = rs.getString("phone_number");
                String name = rs.getString("name");
                int balance = rs.getInt("balance");

                user = new User(userId, email, role, phone_number, name, balance);
            } else {
                throw new UserException("User not found in database");
            }
        } catch (SQLException | UserException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return user;
    }
}
