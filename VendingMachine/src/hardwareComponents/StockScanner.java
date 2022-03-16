package hardwareComponents;

/**
 * Used to check availability of a product
 * within the vending machine
 * @author Jack
 *
 */
public interface StockScanner {

	/**
	 * Checks if a product is available
	 * @return true when product is available, false otherwise
	 */
	public boolean checkAvailability();
}
