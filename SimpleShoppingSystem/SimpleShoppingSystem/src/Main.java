import model.AppData;
import model.CartItem;
import model.Category;
import model.Order;
import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    addToCart();
                    break;
                case 5:
                    viewCart();
                    break;
                case 6:
                    checkout();
                    break;
                case 7:
                    viewOrders();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("===== Simple Shopping System =====");
        System.out.println("1. View products");
        System.out.println("2. Add product");
        System.out.println("3. Delete product");
        System.out.println("4. Add product to cart");
        System.out.println("5. View cart");
        System.out.println("6. Checkout");
        System.out.println("7. View orders");
        System.out.println("0. Exit");
    }

    private static void viewProducts() {
        ArrayList<Product> products = AppData.store.getProducts();

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println();
        System.out.printf("%-5s %-20s %-15s %-10s %-10s%n", "ID", "Name", "Category", "Price", "Quantity");
        System.out.println("---------------------------------------------------------------");

        for (Product product : products) {
            System.out.printf(
                    "%-5d %-20s %-15s %-10.2f %-10d%n",
                    product.getId(),
                    product.getName(),
                    product.getCategory().getName(),
                    product.getPrice(),
                    product.getQuantity()
            );
        }
    }

    private static void addProduct() {
        System.out.println();
        System.out.println("Add Product");

        String name = readText("Product name: ");
        String categoryName = readText("Category: ");
        double price = readDouble("Price: ");
        int quantity = readInt("Quantity: ");

        if (name.isEmpty() || categoryName.isEmpty()) {
            System.out.println("Product name and category cannot be empty.");
            return;
        }

        if (price < 0 || quantity < 0) {
            System.out.println("Price and quantity cannot be negative.");
            return;
        }

        int id = getNextProductId();
        Category category = new Category(id, categoryName);
        Product product = new Product(id, name, price, quantity, category);

        AppData.store.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private static void deleteProduct() {
        viewProducts();

        if (AppData.store.getProducts().isEmpty()) {
            return;
        }

        int id = readInt("Enter product ID to delete: ");
        Product product = findProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        AppData.store.getProducts().remove(product);
        removeProductFromCart(product);
        System.out.println("Product deleted successfully.");
    }

    private static void addToCart() {
        viewProducts();

        if (AppData.store.getProducts().isEmpty()) {
            return;
        }

        int id = readInt("Enter product ID: ");
        Product product = findProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        int quantity = readInt("Enter quantity: ");

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        int alreadyInCart = getCartQuantity(product);

        if (quantity + alreadyInCart > product.getQuantity()) {
            System.out.println("Not enough stock available.");
            return;
        }

        CartItem existingItem = findCartItem(product);

        if (existingItem == null) {
            AppData.cart.add(new CartItem(product, quantity));
        } else {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        }

        System.out.println("Product added to cart.");
    }

    private static void viewCart() {
        if (AppData.cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println();
        System.out.printf("%-20s %-10s %-10s %-10s%n", "Product", "Price", "Quantity", "Total");
        System.out.println("-------------------------------------------------------");

        for (CartItem item : AppData.cart) {
            System.out.printf(
                    "%-20s %-10.2f %-10d %-10.2f%n",
                    item.getProduct().getName(),
                    item.getProduct().getPrice(),
                    item.getQuantity(),
                    item.getTotal()
            );
        }

        System.out.printf("Grand Total: %.2f%n", getCartTotal());
    }

    private static void checkout() {
        if (AppData.cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        for (CartItem item : AppData.cart) {
            Product product = item.getProduct();

            if (item.getQuantity() > product.getQuantity()) {
                System.out.println("Not enough stock for " + product.getName() + ".");
                return;
            }
        }

        ArrayList<CartItem> orderItems = new ArrayList<>();

        for (CartItem item : AppData.cart) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
            orderItems.add(new CartItem(product, item.getQuantity()));
        }

        int orderId = AppData.orders.size() + 1;
        AppData.orders.add(new Order(orderId, orderItems));
        AppData.cart.clear();

        System.out.println("Checkout complete. Order ID: " + orderId);
    }

    private static void viewOrders() {
        if (AppData.orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }

        System.out.println();
        System.out.printf("%-10s %-35s %-10s%n", "Order ID", "Items", "Total");
        System.out.println("-------------------------------------------------------------");

        for (Order order : AppData.orders) {
            System.out.printf(
                    "%-10d %-35s %-10.2f%n",
                    order.getId(),
                    order.getItemsSummary(),
                    order.getTotal()
            );
        }
    }

    private static Product findProductById(int id) {
        for (Product product : AppData.store.getProducts()) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }

    private static CartItem findCartItem(Product product) {
        for (CartItem item : AppData.cart) {
            if (item.getProduct().getId() == product.getId()) {
                return item;
            }
        }

        return null;
    }

    private static int getCartQuantity(Product product) {
        CartItem item = findCartItem(product);
        return item == null ? 0 : item.getQuantity();
    }

    private static double getCartTotal() {
        double total = 0;

        for (CartItem item : AppData.cart) {
            total += item.getTotal();
        }

        return total;
    }

    private static int getNextProductId() {
        int highestId = 0;

        for (Product product : AppData.store.getProducts()) {
            if (product.getId() > highestId) {
                highestId = product.getId();
            }
        }

        return highestId + 1;
    }

    private static void removeProductFromCart(Product product) {
        AppData.cart.removeIf(item -> item.getProduct().getId() == product.getId());
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
