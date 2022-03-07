package demoClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import externalElements.Bank;
import hardwareComponents.CardScanner;
import paymentMethods.LoyaltyCard;
import vendingMachine.VendingMachineController;

/**
 * A class to mimic the functionality of a card
 * scanner that will validate cards with an
 * external bank
 * 
 * @author Jack
 *
 */
public class DemoCardScanner implements CardScanner, ActionListener {

	private LoyaltyCard scannedCard; // holds the currently scanned valid card
	private boolean listening = true; // whether the card scanner is listening for new scans, prevents double scanning
	private VendingMachineController controller;
	
	public DemoCardScanner(VendingMachineController controller) {
		this.controller = controller;
	}
	
	/**
	 * Called whenever a card is presented for
	 * scanning on the card scanner
	 * 
	 * @param card the card presented
	 * @return a boolean representing the outcome of the scan,
	 * true when successful with a valid card, false when scan
	 * fails with an invalid card
	 */
	private boolean scan(LoyaltyCard card) {
		
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
	
	@Override
	public void reset() {
		scannedCard = null;
		listening = true;
	}
	
	@Override
	public LoyaltyCard getScannedCard() {
		return scannedCard;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int linkedAccount = 0;
		
		switch(e.getActionCommand()) {
		case "card1":
			linkedAccount = 123456789;
			break;
		case "card2":
			linkedAccount = 987654321;
			break;
		}
		
		if(listening) {	// only scan the card when listening for scans
			controller.cardScanned(scan(new LoyaltyCard(linkedAccount)));
		}
		
	}
	
}
