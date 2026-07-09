package model;

import java.util.ArrayList;

public class Order {

    private int id;
    private ArrayList<CartItem> items;

    public Order(int id, ArrayList<CartItem> items) {
        this.id = id;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0;

        for (CartItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public String getItemsSummary() {
        StringBuilder summary = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            summary.append(item.getProduct().getName())
                    .append(" x")
                    .append(item.getQuantity());

            if (i < items.size() - 1) {
                summary.append(", ");
            }
        }

        return summary.toString();
    }
}
