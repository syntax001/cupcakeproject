package business.persistence;

import business.entities.CupcakeAccessories;
import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CupcakeMapper {
    private Database database;
    private LinkedHashMap<Integer, CupcakeAccessories> toppings;
    private LinkedHashMap<Integer, CupcakeAccessories> bottoms;
    // TODO: Indsæt toppings og bottoms ind i database

    public CupcakeMapper(Database database) throws UserException {
        this.database = database;
        toppings = new LinkedHashMap<>();
        bottoms = new LinkedHashMap<>();

        // Union?????
        int counter = 1;
        try (Connection connection = database.connect()) {
            String sql1 = "SELECT name, price FROM cupcake_toppings";

            try (PreparedStatement ps = connection.prepareStatement(sql1)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String toppingName = rs.getString("name");
                    int price = rs.getInt("price");
                    toppings.put(counter, new CupcakeAccessories(price, toppingName));
                    counter++;
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

            String sql2 = "SELECT name, price FROM cupcake_bottoms";

            try (PreparedStatement ps = connection.prepareStatement(sql2)) {
                ResultSet rs = ps.executeQuery();
                counter = 1;
                while (rs.next()) {
                    String bottomName = rs.getString("name");
                    int price = rs.getInt("price");
                    bottoms.put(counter, new CupcakeAccessories(price, bottomName));
                    counter++;
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public double[] getPrices(int top, int bottom) {
        double toppingPrice = toppings.get(top).getPrice();
        double bottomPrice = bottoms.get(bottom).getPrice();
        double[] prices = new double[2];

        prices[0] = toppingPrice;
        prices[1] = bottomPrice;

        return prices;
    }

    public String[] getCupcakeTopBotNames(int top, int bottom) {
        String[] cupcakeTopBotNames = new String[2];

        cupcakeTopBotNames[0] = toppings.get(top).getAccessories();
        cupcakeTopBotNames[1] = bottoms.get(bottom).getAccessories();

        return cupcakeTopBotNames;
    }

    public void uploadCupcakeOrder(User user, int topping, int bottom, int amount) throws UserException {
        int userId = user.getId();

        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO cupcake_orders (customer_id, cupcake_toppings_id, cupcake_bottoms_id, amount) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setInt(2, topping);
                ps.setInt(3, bottom);
                ps.setInt(4, amount);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public List<Order> getOrders(User user) throws UserException {
        int userId = user.getId();
        List<Order> orders = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT order_id, cupcake_toppings_id, cupcake_bottoms_id, amount, order_date FROM cupcake_orders WHERE customer_id=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int cupcakeToppingId = rs.getInt("cupcake_toppings_id");
                int cupcakeBottomId = rs.getInt("cupcake_bottoms_id");
                int amount = rs.getInt("amount");
                String orderDate = rs.getString("order_date");

                Order order = new Order(userId, orderId, cupcakeToppingId, cupcakeBottomId, amount, orderDate);
                orders.add(order);
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }

        return orders;
    }
}
