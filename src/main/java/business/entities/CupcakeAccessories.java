package business.entities;

public class CupcakeAccessories {
    private String accessories = "";
    private double price = 0;

    public CupcakeAccessories(double price, String accessories) {
        this.accessories = accessories;
        this.price = price;
    }

    public String getAccessories() {
        return accessories;
    }

    public double getPrice() {
        return price;
    }
}
