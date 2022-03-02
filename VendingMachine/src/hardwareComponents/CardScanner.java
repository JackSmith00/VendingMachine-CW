package hardwareComponents;

import externalElements.Bank;
import paymentMethods.LoyaltyCard;

/**
 * A class to mimic the functionality of a card
 * scanner that will validate cards with an
 * external bank
 * 
 * @author Jack
 *
 */
public class CardScanner {

	private LoyaltyCard scannedCard; // holds the currently scanned valid card
	private boolean listening = true; // whether the card scanner is listening for new scans, prevents double scanning
	
	/**
	 * Called whenever a card is presented for
	 * scanning on the card scanner
	 * 
	 * @param card the card presented
	 * @return a boolean representing the outcome of the scan,
	 * true when successful with a valid card, false when scan
	 * fails with an invalid card
	 */
	public boolean scan(LoyaltyCard card) {
		
		if(!listening) {
			// only scan card when listening
			return false;
		}
		
		if(Bank.validateCard(card)) {
			// successful validation
			scannedCard = card;
			listening = false;
			return true;
		}
		
		// unsuccessful validation
		scannedCard = null;
		return false;
	}
	
	/**
	 * Resets the card scanner to default
	 * if a sale is cancelled
	 */
	public void reset() {
		scannedCard = null;
		listening = true;
	}
	
	/**
	 * @return the currently scanned, valid card
	 */
	public LoyaltyCard getScannedCard() {
		return scannedCard;
	}
	
}