package Exception_Handling;

// Custom exception class InvalidProductException extending InventoryException
public class InvalidProductException extends InventoryException {

	// Constructor to initialize InvalidProductException with a custom error message
	public InvalidProductException(String message) {
		super(message); // Call to superclass (InventoryException) constructor with the provided message
	}
}
