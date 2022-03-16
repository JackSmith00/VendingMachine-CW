package paymentMethods;

/**
 * A class to represent a loyalty/pre-paid card
 * that would be scanned by the vending machine
 * 
 * @author Jack
 *
 */
public class LoyaltyCard {
	
	private int cardNumber; // unique number of the card
	
	/**
	 * Creates a new LoyaltyCard object
	 * 
	 * @param cardNumber the unique card number of the card
	 */
	public LoyaltyCard(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	/**
	 * @return the unique number of the card
	 */
	public int getCardNumber() {
		return cardNumber;
	}

}
