package business.persistence;

import business.entities.CupcakeAccessories;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.LinkedHashMap;

public class CupcakeMapper {
    private Database database;
    private LinkedHashMap<Integer, CupcakeAccessories> toppings;
    private LinkedHashMap<Integer, CupcakeAccessories> bottoms;
    // TODO: Indsæt toppings og bottoms ind i database

    public CupcakeMapper(Database database) throws UserException {
        this.database = database;
        toppings = new LinkedHashMap<Integer, CupcakeAccessories>();
        bottoms = new LinkedHashMap<Integer, CupcakeAccessories>();

        // Union?????
        int counter = 0;
        try (Connection connection = database.connect()) {
            String sql1 = "SELECT name, price FROM cupcake_toppings";

            try (PreparedStatement ps = connection.prepareStatement(sql1)) {
                ResultSet rs = ps.executeQuery();
                counter = 0;
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
                counter = 0;
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

        //TODO: Ændre upload af order (Fjernet orders)

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
 /*
    public String [] getOrders(User user) {
        String [] orders;
        try (Connection connection = database.connect())
        {
            String sql = "SELECT customer_id, role, name, phone_number, balance FROM users WHERE email=? AND password=?";
            // TODO: Ændre databse (Fjern orders, tilføj customer_id til cupcake_orders

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String role = rs.getString("role");
                    String name = rs.getString("name");
                    String phone_number = rs.getString("phone_number");
                    int balance = rs.getInt("balance");
                    int id = rs.getInt("customer_id");
                    User user = new User(email, password, role, phone_number, name, balance);
                    user.setId(id);
                    return user;
                } else
                {
                    throw new UserException("Could not validate user");
                }
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException("Connection to database could not be established");
        }

        return orders;
    }

  */
}
