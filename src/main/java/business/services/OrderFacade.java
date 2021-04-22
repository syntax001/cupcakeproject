package business.services;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.CupcakeMapper;
import business.persistence.Database;

import java.util.ArrayList;
import java.util.List;

public class OrderFacade {

    private CupcakeMapper cupcakeMapper;

    public OrderFacade(Database database) throws UserException {
        cupcakeMapper = new CupcakeMapper(database);
    }

    public double[] calcPrices(int topping, int bottom, int amount) {
        double toppingPrice = 0;
        double bottomPrice = 0;
        double[] cupcakeTopBotPrice = new double[2];
        double[] prices = new double[4];

        cupcakeTopBotPrice = cupcakeMapper.getPrices(topping, bottom);
        toppingPrice = cupcakeTopBotPrice[0];
        bottomPrice = cupcakeTopBotPrice[1];

        prices[0] = (toppingPrice + bottomPrice) * amount;
        prices[1] = toppingPrice + bottomPrice;
        prices[2] = toppingPrice;
        prices[3] = bottomPrice;

        return prices;
    }

    public String[] getTopBotNames(int topping, int bottom) {
        String[] cupcakebotTopNames = cupcakeMapper.getCupcakeTopBotNames(topping, bottom);

        return cupcakebotTopNames;
    }

    public void uploadOrder(User user, int topping, int bottom, int amount) throws UserException {
        cupcakeMapper.uploadCupcakeOrder(user, topping, bottom, amount);
    }

   public List<Order> getOrders(User user) throws UserException {
        List<Order> orders = cupcakeMapper.getOrders(user);

        for (Order order: orders) {
            int cupcakeToppingId = order.getCupcakeToppingId();
            int cupcakeBottomId = order.getCupcakeBottomId();
            int amount = order.getAmount();

            String[] cupcakebotTopNames = cupcakeMapper.getCupcakeTopBotNames(cupcakeToppingId, cupcakeBottomId);
            double[] cupcakesPrices = calcPrices(cupcakeToppingId, cupcakeBottomId, amount);

            order.setCupcakeBotTopNames(cupcakebotTopNames);
            order.setCupcakePrices(cupcakesPrices);
        }

        return orders;
    }




}
