package model;

public class Product {
    private int id;
    private String name;
    private int amountNumber;

    public Product(int id, String name, int amountNumber) {
        this.id = id;
        this.name = name;
        this.amountNumber = amountNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountNumber() {
        return amountNumber;
    }

    public void setAmountNumber(int amountNumber) {
        this.amountNumber = amountNumber;
    }
}
