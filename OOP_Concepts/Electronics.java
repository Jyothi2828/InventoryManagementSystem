package OOP_Concepts;

// Electronics class extending Product (inherits attributes and methods)
public class Electronics extends Product {
    private String brand;      // Brand of the electronics
    private int warranty;      // Warranty period in months

    // Constructor for initializing Electronics object
    public Electronics(String productID, String productName, double price, int quantity, String brand, int warranty) {
        super(productID, productName, price, quantity);  // Call to superclass constructor (Product)
        this.brand = brand;       // Initialize brand
        this.warranty = warranty; // Initialize warranty period
    }

    // Getter for brand
    public String getBrand() {
        return brand;
    }

    // Getter for warranty period
    public int getWarranty() {
        return warranty;
    }

    // Override toString method to provide custom string representation
    @Override
    public String toString() {
        return super.toString() + ", Brand: " + brand + ", Warranty: " + warranty + " months";
        // Calls super.toString() to include inherited attributes (productID, productName, price, quantity)
        // Adds brand and warranty details specific to Electronics
    }
}
