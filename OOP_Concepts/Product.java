package OOP_Concepts;

// Product class is declared as abstract to prevent direct instantiation
public abstract class Product {
    private String productID;      // Unique identifier for the product
    private String productName;    // Name of the product
    private double price;          // Price of the product
    private int quantity;          // Quantity of the product in stock

    // Constructor to initialize a Product object
    public Product(String productID, String productName, double price, int quantity) {
        this.productID = productID;       // Initialize product ID
        this.productName = productName;   // Initialize product name
        this.price = price;               // Initialize product price
        this.quantity = quantity;         // Initialize product quantity
    }

    // Getter for product ID
    public String getProductID() {
        return productID;
    }

    // Getter for product name
    public String getProductName() {
        return productName;
    }

    // Getter for product price
    public double getPrice() {
        return price;
    }

    // Getter for product quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for product quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to calculate total price of the product (price * quantity)
    public double calculateTotalPrice() {
        return price * quantity;
    }

    // Override toString method to provide custom string representation
    @Override
    public String toString() {
        return "Product ID: " + productID + ", Product Name: " + productName + ", Price: " + price + ", Quantity: " + quantity;
        // Returns a formatted string representation of the Product object
    }
}
