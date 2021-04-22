package business.entities;

public class Order {
    private int userId;
    private int orderId;
    private int cupcakeToppingId;
    private int cupcakeBottomId;
    private int amount;
    private String orderDate;
    private String[] cupcakeBotTopNames;
    private double[] cupcakePrices;

    public Order(int userId, int orderId, int cupcakeToppingId, int cupcakeBottomId, int amount, String orderDate) {
        this.userId = userId;
        this.orderId = orderId;
        this.cupcakeToppingId = cupcakeToppingId;
        this.cupcakeBottomId = cupcakeBottomId;
        this.amount = amount;
        this.orderDate = orderDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public int getUserId() {
        return userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCupcakeToppingId() {
        return cupcakeToppingId;
    }

    public int getCupcakeBottomId() {
        return cupcakeBottomId;
    }

    public int getAmount() {
        return amount;
    }

    public String[] getCupcakeBotTopNames() {
        return cupcakeBotTopNames;
    }

    public double[] getCupcakePrices() {
        return cupcakePrices;
    }

    public void setCupcakeBotTopNames(String[] cupcakeBotTopNames) {
        this.cupcakeBotTopNames = cupcakeBotTopNames;
    }

    public void setCupcakePrices(double[] cupcakePrices) {
        this.cupcakePrices = cupcakePrices;
    }
}
