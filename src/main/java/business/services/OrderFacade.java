package business.services;

import business.persistence.CupcakeMapper;

public class OrderFacade {

    CupcakeMapper cupcakeMapper;

    public OrderFacade() {
        cupcakeMapper = new CupcakeMapper();
    }

    public double[] calcPrices(int topping, int bottom, int amount) {
        double toppingPrice = 0;
        double bottomPrice = 0;
        double[] cupcakeTopBotPrice = new double[2];
        double[] prices = new double[2];

        cupcakeTopBotPrice = cupcakeMapper.getPrices(topping, bottom);
        toppingPrice = cupcakeTopBotPrice[0];
        bottomPrice = cupcakeTopBotPrice[1];

        prices[0] = (toppingPrice + bottomPrice) * amount;
        prices[1] = toppingPrice + bottomPrice;

        return prices;
    }
}