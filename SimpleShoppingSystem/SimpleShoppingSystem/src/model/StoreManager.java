package model;

public class StoreManager extends Person {

    public StoreManager() {
        super();
    }

    public StoreManager(String name) {
        super(name);
    }

    @Override
    public void displayInfo() {
        System.out.println("Store Manager : " + name);
    }

}