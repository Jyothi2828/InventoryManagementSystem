package Exception_Handling;

// Custom exception class InventoryException extending Exception
public class InventoryException extends Exception {
    
    // Constructor to initialize InventoryException with a custom error message
    public InventoryException(String message) {
        super(message);  // Call to superclass (Exception) constructor with the provided message
    }
}
