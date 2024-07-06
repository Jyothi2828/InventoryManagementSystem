package OOP_Concepts;

// Clothing class extending Product (inherits attributes and methods)
public class Clothing extends Product {
    private String size;       // Size of the clothing
    private String material;   // Material of the clothing

    // Constructor for initializing Clothing object
    public Clothing(String productID, String productName, double price, int quantity, String size, String material) {
        super(productID, productName, price, quantity);  // Call to superclass constructor (Product)
        this.size = size;       // Initialize size
        this.material = material; // Initialize material
    }

    // Getter for size
    public String getSize() {
        return size;
    }

    // Getter for material
    public String getMaterial() {
        return material;
    }

    // Override toString method to provide custom string representation
    @Override
    public String toString() {
        return super.toString() + ", Size: " + size + ", Material: " + material;
        // Calls super.toString() to include inherited attributes (productID, productName, price, quantity)
        // Adds size and material details specific to Clothing
    }
}
