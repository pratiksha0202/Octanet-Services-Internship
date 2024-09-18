import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

class Item {
    String name;
    double price;
    int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10.2f %-10d %-10.2f", name, price, quantity, getTotalPrice());
    }
}

public class ReceiptCalculator {

    static ArrayList<Item> items = new ArrayList<>();
    static double taxRate = 0.1;  // 10% tax
    static double discountRate = 0.05; // 5% discount for simplicity

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueInput = true;

        // Step 1: Accept input for items
        while (continueInput) {
            System.out.print("Enter item name: ");
            String name = sc.nextLine();
            System.out.print("Enter item price: ");
            double price = sc.nextDouble();
            System.out.print("Enter item quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();  // consume newline

            items.add(new Item(name, price, quantity));

            System.out.print("Do you want to add another item? (yes/no): ");
            String response = sc.nextLine();
            if (response.equalsIgnoreCase("no")) {
                continueInput = false;
            }
        }

        // Step 2: Calculate subtotal, tax, discount, and final total
        double subtotal = calculateSubtotal();
        double tax = subtotal * taxRate;
        double discount = subtotal * discountRate;
        double finalTotal = subtotal + tax - discount;

        // Step 3: Generate and display the receipt
        generateReceipt(subtotal, tax, discount, finalTotal);

        // Step 4: Optionally save the receipt as a text file
        System.out.print("Do you want to save the receipt as a text file? (yes/no): ");
        String saveResponse = sc.nextLine();
        if (saveResponse.equalsIgnoreCase("yes")) {
            saveReceiptToFile(subtotal, tax, discount, finalTotal);
        }

        sc.close();
    }

    // Method to calculate subtotal
    public static double calculateSubtotal() {
        double subtotal = 0;
        for (Item item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    // Method to generate and display receipt
    public static void generateReceipt(double subtotal, double tax, double discount, double finalTotal) {
        System.out.println("\n----- Receipt -----");
        System.out.printf("%-10s %-10s %-10s %-10s\n", "Name", "Price", "Qty", "Total");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("-------------------");
        System.out.printf("Subtotal: %.2f\n", subtotal);
        System.out.printf("Tax (%.0f%%): %.2f\n", taxRate * 100, tax);
        System.out.printf("Discount (%.0f%%): %.2f\n", discountRate * 100, discount);
        System.out.printf("Final Total: %.2f\n", finalTotal);
    }

    // Method to save receipt to a text file
    public static void saveReceiptToFile(double subtotal, double tax, double discount, double finalTotal) {
        try {
            FileWriter writer = new FileWriter("receipt.txt");
            writer.write("----- Receipt -----\n");
            writer.write(String.format("%-10s %-10s %-10s %-10s\n", "Name", "Price", "Qty", "Total"));
            for (Item item : items) {
                writer.write(item.toString() + "\n");
            }
            writer.write("-------------------\n");
            writer.write(String.format("Subtotal: %.2f\n", subtotal));
            writer.write(String.format("Tax (%.0f%%): %.2f\n", taxRate * 100, tax));
            writer.write(String.format("Discount (%.0f%%): %.2f\n", discountRate * 100, discount));
            writer.write(String.format("Final Total: %.2f\n", finalTotal));
            writer.close();
            System.out.println("Receipt saved to receipt.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt.");
            e.printStackTrace();
        }
    }
}
