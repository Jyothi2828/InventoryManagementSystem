package Inventory_System;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Exception_Handling.InvalidProductException;
import OOP_Concepts.Clothing;
import OOP_Concepts.Electronics;
import OOP_Concepts.Product;

public class InventorySystem {
    private List<Product> products;
    private List<String> salesHistory;

    public InventorySystem() {
        products = new ArrayList<>();
        salesHistory = new ArrayList<>();
    }

    public void addProduct(Product product) throws InvalidProductException {
        if (product.getQuantity() < 0) {
            throw new InvalidProductException("Product quantity cannot be negative");
        }

        // Check for duplicate product ID
        for (Product p : products) {
            if (p.getProductID().equals(product.getProductID())) {
                throw new InvalidProductException("Duplicate product ID: " + product.getProductID() +
                        ". A product with this ID already exists.");
            }
        }

        // If no duplicate, add the product to the list
        products.add(product);
    }

    public void updateQuantity(String productID, int quantity) throws InvalidProductException {
        for (Product product : products) {
            if (product.getProductID().equals(productID)) {
                if (quantity < 0) {
                    throw new InvalidProductException("Product quantity cannot be negative");
                }
                product.setQuantity(quantity);
                return;
            }
        }
        throw new InvalidProductException("Product not found with ID: " + productID);
    }

    public void processSale(String productID, int quantity) throws InvalidProductException {
        for (Product product : products) {
            if (product.getProductID().equals(productID)) {
                if (quantity > product.getQuantity()) {
                    throw new InvalidProductException("Insufficient stock for product ID: " + productID);
                }
                product.setQuantity(product.getQuantity() - quantity);
                String saleDetails = "Sale processed: " + product.getProductName() + ", Quantity sold: " + quantity;
                salesHistory.add(saleDetails);
                System.out.println(saleDetails);
                return;
            }
        }
        throw new InvalidProductException("Product not found with ID: " + productID);
    }

    public void displaySalesHistory() {
        if (salesHistory.isEmpty()) {
            System.out.println("No sales recorded yet.");
        } else {
            System.out.println("\nSales History:");
            for (String sale : salesHistory) {
                System.out.println(sale);
            }

            // Calculate total sales price
            double totalSalesPrice = calculateTotalSalesPrice();
            System.out.println("Total Sales Price: $" + totalSalesPrice);
        }
    }

    private double calculateTotalSalesPrice() {
        double totalSalesPrice = 0.0;
        for (String sale : salesHistory) {
            // Parse the sale details to extract the price
            String[] parts = sale.split(", ");
            String quantityString = parts[1].substring(parts[1].lastIndexOf(": ") + 2);
            int quantity = Integer.parseInt(quantityString);
            String productName = parts[0].substring(parts[0].indexOf(": ") + 2);
            
            // Find the product by name (assumes productName is unique)
            for (Product product : products) {
                if (product.getProductName().equals(productName)) {
                    totalSalesPrice += product.getPrice() * quantity;
                    break;
                }
            }
        }
        return totalSalesPrice;
    }

    public void displayInventory() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
        } else {
            System.out.println("\nCurrent Inventory:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public static void main(String[] args) {
        InventorySystem inventorySystem = new InventorySystem();
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\nInventory Management System:");
                System.out.println("1. Add Product");
                System.out.println("2. Update Quantity");
                System.out.println("3. Process Sale");
                System.out.println("4. Display Inventory");
                System.out.println("5. Display Sales History");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                try {
                    switch (choice) {
                        case 1:
                            System.out.println("Choose Product Type: 1. Electronics 2. Clothing");
                            int type = scanner.nextInt();
                            scanner.nextLine();  // Consume newline

                            System.out.print("Enter Product ID: ");
                            String id = scanner.nextLine();

                            // Check if product ID already exists
                            boolean idExists = false;
                            for (Product p : inventorySystem.getProducts()) {
                                if (p.getProductID().equals(id)) {
                                    idExists = true;
                                    break;
                                }
                            }

                            if (idExists) {
                                System.out.println("Error: Duplicate product ID: " + id + ". A product with this ID already exists.");
                                break;  // Exit the case 1 block
                            }

                            String name;
                            while (true) {
                                System.out.print("Enter Product Name (only alphabets): ");
                                name = scanner.nextLine();

                                // Validate product name (only alphabetic characters)
                                if (name.matches("[a-zA-Z]+")) {
                                    break;  // Valid name, exit the loop
                                } else {
                                    System.out.println("Error: Enter a valid product name with only alphabetic characters.");
                                }
                            }

                            double price;
                            while (true) {
                                try {
                                    System.out.print("Enter Product Price: ");
                                    price = Double.parseDouble(scanner.nextLine());
                                    if (price <= 0) {
                                        throw new NumberFormatException();
                                    }
                                    break;  // Valid price, exit the loop
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Enter a valid numeric price greater than zero.");
                                }
                            }

                            int quantity;
                            while (true) {
                                try {
                                    System.out.print("Enter Product Quantity: ");
                                    quantity = Integer.parseInt(scanner.nextLine());
                                    if (quantity < 0) {
                                        throw new NumberFormatException();
                                    }
                                    break;  // Valid quantity, exit the loop
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Enter a valid non-negative numeric quantity.");
                                }
                            }

                            try {
                                if (type == 1) {
                                    System.out.print("Enter Brand: ");
                                    String brand = scanner.nextLine();
                                    System.out.print("Enter Warranty Period (months): ");
                                    int warranty = scanner.nextInt();
                                    scanner.nextLine();  // Consume newline

                                    Electronics electronics = new Electronics(id, name, price, quantity, brand, warranty);
                                    inventorySystem.addProduct(electronics);
                                    System.out.println("Product added successfully.");
                                } else if (type == 2) {
                                    System.out.print("Enter Size: ");
                                    String size = scanner.nextLine();
                                    System.out.print("Enter Material: ");
                                    String material = scanner.nextLine();

                                    Clothing clothing = new Clothing(id, name, price, quantity, size, material);
                                    inventorySystem.addProduct(clothing);
                                    System.out.println("Product added successfully.");
                                } else {
                                    System.out.println("Invalid product type selected.");
                                }
                            } catch (InvalidProductException e) {
                                System.out.println("Error adding product: " + e.getMessage());
                            }
                            break;

                        case 2:
                            System.out.print("Enter Product ID to update quantity: ");
                            String updateID = scanner.nextLine();
                            System.out.print("Enter new quantity: ");
                            int newQuantity;
                            while (true) {
                                try {
                                    newQuantity = Integer.parseInt(scanner.nextLine());
                                    if (newQuantity < 0) {
                                        throw new NumberFormatException();
                                    }
                                    break;  // Valid quantity, exit the loop
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Enter a valid non-negative numeric quantity.");
                                }
                            }

                            try {
                                inventorySystem.updateQuantity(updateID, newQuantity);
                                System.out.println("Quantity updated successfully.");
                            } catch (InvalidProductException e) {
                                System.out.println("Error updating quantity: " + e.getMessage());
                            }
                            break;

                        case 3:
                            System.out.print("Enter Product ID to process sale: ");
                            String saleID = scanner.nextLine();
                            System.out.print("Enter quantity to sell: ");
                            int saleQuantity;
                            while (true) {
                                try {
                                    saleQuantity = Integer.parseInt(scanner.nextLine());
                                    if (saleQuantity < 0) {
                                        throw new NumberFormatException();
                                    }
                                    break;  // Valid quantity, exit the loop
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Enter a valid non-negative numeric quantity.");
                                }
                            }

                            try {
                                inventorySystem.processSale(saleID, saleQuantity);
                                System.out.println("Sale processed successfully.");
                            } catch (InvalidProductException e) {
                                System.out.println("Error processing sale: " + e.getMessage());
                            }
                            break;

                        case 4:
                            inventorySystem.displayInventory();
                            break;

                        case 5:
                            inventorySystem.displaySalesHistory();
                            break;

                        case 6:
                            System.out.println("Exiting...");
                            return;

                        default:
                            System.out.println("Invalid choice! Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } finally {
            scanner.close();
        }
    }
}