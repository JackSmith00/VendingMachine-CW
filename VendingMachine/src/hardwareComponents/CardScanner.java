package hardwareComponents;

/**
 * An interface of a card scanner that can
 * read and store a loyalty card and debit the
 * associated bank account
 * 
 * @author Jack
 *
 */
public interface CardScanner {

	/**
	 * Resets the card scanner to default
	 * if a sale is cancelled
	 */
	public void reset();
	
	/**
	 * Allows the controller to prevent
	 * the card scanner from listening
	 * for new scans
	 */
	public void disable();
}
