package business.persistence;

import business.entities.User;
import business.exceptions.UserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AdminMapperTest {
    private final static String DATABASE = "cupcake_shop";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = "123root";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static AdminMapper adminMapper;
    private static UserMapper userMapper;
    private static List<User> users;

    @BeforeAll
    public static void setUpClass() throws UserException {
        try {
            database = new Database(USER, PASSWORD, URL);
            try (Statement stmt = database.connect().createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
                stmt.execute("drop table if exists shopping_cart");
                stmt.execute("drop table if exists users");
                stmt.execute("drop table if exists cupcake_orders");
                stmt.execute("drop table if exists cupcake_bottoms");
                stmt.execute("drop table if exists cupcake_toppings");
                stmt.execute("create table " + TESTDATABASE + ".shopping_cart LIKE " + DATABASE + ".shopping_cart;");
                stmt.execute("create table " + TESTDATABASE + ".users LIKE " + DATABASE + ".users;");
                stmt.execute("create table " + TESTDATABASE + ".cupcake_orders LIKE " + DATABASE + ".cupcake_orders;");
                stmt.execute("create table " + TESTDATABASE + ".cupcake_bottoms LIKE " + DATABASE + ".cupcake_bottoms;");
                stmt.execute("create table " + TESTDATABASE + ".cupcake_toppings LIKE " + DATABASE + ".cupcake_toppings;");
                stmt.execute("INSERT INTO " + TESTDATABASE + ".cupcake_bottoms SELECT * FROM " + DATABASE + ".cupcake_bottoms;");
                stmt.execute("INSERT INTO " + TESTDATABASE + ".cupcake_toppings SELECT * FROM " + DATABASE + ".cupcake_toppings;");
            } catch (SQLException ex) {
                System.out.println("Could not open connection to database: " + ex.getMessage());
            }
            adminMapper = new AdminMapper(database);
            userMapper = new UserMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }

        users = new ArrayList<>();
        users.add(new User("user1@test.com", "test", "customer", "82931032", "Jens Paulsen"));
        users.add(new User("user2@test.com", "test", "employee", "23451322", "Frederik Jensen"));
        users.add(new User("user3@test.com", "test", "customer", "54324523", "Paul Gamer"));
        users.add(new User("user4@test.com", "test", "employee", "12332112", "Karl Flank"));
        users.add(new User("user5@test.com", "test", "employee", "90837289", "Fram hvam"));

        for (User user : users) {
            userMapper.createUser(user);
        }
    }

    @Test
    public void testGetAllUsers() throws UserException {
        List<User> usersFromAdminMapper = adminMapper.getAllUsers();

        int counter = 0;
        for(User user: usersFromAdminMapper) {
            User user_ = users.get(counter);
            assertEquals(user_.getEmail(), user.getEmail());
            assertEquals(user_.getRole(), user.getRole());
            assertEquals(user_.getPhone_number(), user.getPhone_number());
            assertEquals(user_.getName(), user.getName());
            counter++;
        }
    }

    @Test
    public void testGetUser() throws UserException {
        User user_ = adminMapper.getUser(1);

        User user = users.get(0);
        assertEquals(user.getEmail(), user_.getEmail());
        assertEquals(user.getRole(), user_.getRole());
        assertEquals(user.getPhone_number(), user_.getPhone_number());
        assertEquals(user.getName(), user_.getName());
    }
}
