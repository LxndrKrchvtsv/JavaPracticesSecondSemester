package entity;

public class Service extends AbstractPriceable {
    private String name;
    private Euro totalCost;
    private int numberOfUsers;


    public Service() {
        super();
    }

    public Service(String name, Euro totalCost, int numberOfUsers) {
        this.name = name;
        this.totalCost = totalCost;
        this.numberOfUsers = numberOfUsers;
    }

    public String getName() {
        return name;
    }

    public Euro getTotalCost() {
        return totalCost;
    }

    public int getUsers() {
        return numberOfUsers;
    }

    @Override
    public Euro getPrice() {
        return totalCost.mul(1.0 / numberOfUsers, RoundMethod.CEIL, 0);
    }

    public String fieldsToString() {
        return getName() + "; " + totalCost + "; " + getPrice();
    }

}