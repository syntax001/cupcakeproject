package business.persistence;

import java.util.ArrayList;
import java.util.List;

public class CupcakeMapper {
    List<Double> toppings;
    List<Double> bottoms;

    // TODO: Indsæt toppings og bottoms ind i database

    public CupcakeMapper() {
        toppings = new ArrayList<>();
        bottoms = new ArrayList<>();

        toppings.add(5.00);
        toppings.add(5.00);
        toppings.add(5.00);
        toppings.add(6.00);
        toppings.add(6.00);
        toppings.add(7.00);
        toppings.add(8.00);
        toppings.add(8.00);
        toppings.add(9.00);

        bottoms.add(5.00);
        bottoms.add(5.00);
        bottoms.add(5.00);
        bottoms.add(6.00);
        bottoms.add(7.00);
    }

    public double [] getPrices(int top, int bottom) {
        double toppingPrice = toppings.get(top);
        double bottomPrice = bottoms.get(bottom);
        double [] prices = new double[2];

        prices[0] = toppingPrice;
        prices[1] = bottomPrice;

        return prices;
    }

}
