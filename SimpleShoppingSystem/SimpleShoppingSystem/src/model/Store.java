package model;

import java.util.ArrayList;

public class Store {

    private ArrayList<Product> products;

    public Store() {
        products = new ArrayList<>();

        // Sample Products
        Category electronics = new Category(1, "Electronics");

        products.add(new Product(1, "Laptop", 65000, 5, electronics));
        products.add(new Product(2, "Mouse", 1200, 10, electronics));
        products.add(new Product(3, "Keyboard", 1800, 8, electronics));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int index) {
        if (index >= 0 && index < products.size()) {
            products.remove(index);
        }
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public void updateProduct(int index, Product product) {
        if (index >= 0 && index < products.size()) {
            products.set(index, product);
        }
    }
}