package business.persistence;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CupcakeMapperTest {
    private final static String DATABASE = "cupcake_shop";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = "123root";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static CupcakeMapper cupcakeMapper;
    private static OrderFacade orderFacade;

    @BeforeAll
    public static void setUpClass() {
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
                stmt.execute("INSERT INTO "+ TESTDATABASE +".cupcake_bottoms SELECT * FROM "+ DATABASE +".cupcake_bottoms;");
                stmt.execute("INSERT INTO "+ TESTDATABASE +".cupcake_toppings SELECT * FROM "+ DATABASE +".cupcake_toppings;");
            } catch (SQLException ex) {
                System.out.println("Could not open connection to database: " + ex.getMessage());
            }
            cupcakeMapper = new CupcakeMapper(database);
            orderFacade = new OrderFacade(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        } catch (UserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOrderUpload() throws UserException {
        User user = new User("test@test.com", "123", "customer", "73899212", "test");
        user.setId(1);
        for(int i = 0; i >= 1; i++) {
            orderFacade.createOrder(user, 2*i, 5, 10*i);
        }
        List<Order> orders = cupcakeMapper.getOrders(user);

        int counter = 0;
        for(Order order: orders) {
            assertEquals(1, order.getUserId());
            assertEquals(1*2, order.getOrderId());
            assertEquals(2*counter, order.getCupcakeToppingId());
            assertEquals(5, order.getCupcakeBottomId());
            assertEquals(10*counter, order.getAmount());
            counter++;
        }
    }

    @Test
    public void testGetPrices() {
        double[] prices = cupcakeMapper.getPrices(9, 4);
        assertEquals(9.00, prices[0]);
        assertEquals(6.00, prices[1]);
    }

    @Test
    public void testCalcPrices() {
        double[] prices = orderFacade.calcPrices(9, 3, 7);
        assertEquals(98.00, prices[0]);
        assertEquals(14.00, prices[1]);
    }

    @Test
    public void testGetCupcakeTopBotNames() {
        String[] topBotNames = cupcakeMapper.getCupcakeTopBotNames(2, 2);
        assertEquals("Blueberry", topBotNames[0]);
        assertEquals("Vanilla", topBotNames[1]);
    }
}
