package business.persistence;

import business.entities.CupcakeAccessories;

import java.util.LinkedHashMap;
import java.util.Set;

public class CupcakeMapper {
    private LinkedHashMap<Integer, CupcakeAccessories> toppings;
    private LinkedHashMap<Integer, CupcakeAccessories > bottoms;
    // TODO: Indsæt toppings og bottoms ind i database

    public CupcakeMapper() {
        toppings = new LinkedHashMap<Integer, CupcakeAccessories>();
        bottoms = new LinkedHashMap<Integer, CupcakeAccessories>();

        toppings.put(0, new CupcakeAccessories(5.00, "Chocolate"));
        toppings.put(1, new CupcakeAccessories(5.00, "Blueberry"));
        toppings.put(2, new CupcakeAccessories(5.00, "Rasberry"));
        toppings.put(3, new CupcakeAccessories(6.00, "Crispy"));
        toppings.put(4, new CupcakeAccessories(6.00, "Strawberry"));
        toppings.put(5, new CupcakeAccessories(7.00, "Rum/Raisin"));
        toppings.put(6, new CupcakeAccessories(8.00, "Orange"));
        toppings.put(7, new CupcakeAccessories(8.00, "Lemon"));
        toppings.put(8, new CupcakeAccessories(9.00, "Blue cheese"));

        bottoms.put(0, new CupcakeAccessories(5.00, "Chocolate"));
        bottoms.put(1, new CupcakeAccessories(5.00, "Vanilla"));
        bottoms.put(2, new CupcakeAccessories(5.00, "Nutmeg"));
        bottoms.put(3, new CupcakeAccessories(6.00, "Pistacio"));
        bottoms.put(4, new CupcakeAccessories(7.00, "Almond"));
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
}
