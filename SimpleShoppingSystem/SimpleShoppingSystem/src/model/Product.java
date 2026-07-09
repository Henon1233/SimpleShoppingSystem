package model;

public class Product {

    private static int totalProducts = 0;

    private int id;
    private String name;
    private double price;
    private int quantity;
    private Category category;

    public Product() {
        this(0, "", 0, 0, new Category());
    }

    public Product(String name, double price) {
        this(0, name, price, 0, new Category());
    }

    public Product(int id, String name, double price, int quantity, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;

        totalProducts++;
    }

    public static int getTotalProducts() {
        return totalProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price=price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category=category;
    }

    public void displayProduct() {

        System.out.println("---------------------------");
        System.out.println("ID : " + id);
        System.out.println("Name : " + name);
        System.out.println("Price : " + price);
        System.out.println("Quantity : " + quantity);
        System.out.println("Category : " + category.getName());
        System.out.println("---------------------------");

    }

}