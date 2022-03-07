package hardwareComponents;

import paymentMethods.LoyaltyCard;

public interface CardScanner {

	/**
	 * Resets the card scanner to default
	 * if a sale is cancelled
	 */
	public void reset();
	
	/**
	 * @return the currently scanned, valid card
	 */
	public LoyaltyCard getScannedCard();
}
