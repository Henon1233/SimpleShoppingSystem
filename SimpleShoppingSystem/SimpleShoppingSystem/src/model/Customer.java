package model;

public class Customer extends Person {

    public Customer() {
        super();
    }

    public Customer(String name) {
        super(name);
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer : " + name);
    }

}