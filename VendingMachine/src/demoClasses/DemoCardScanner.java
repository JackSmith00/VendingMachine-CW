package demoClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hardwareComponents.CardScanner;
import paymentMethods.LoyaltyCard;
import vendingMachine.VendingMachineController;

/**
 * A class to mimic the functionality of a card scanner
 * 
 * @author Jack
 *
 */
public class DemoCardScanner implements CardScanner, ActionListener {

	private boolean listening = true; // whether the card scanner is listening for new scans, prevents double scanning
	private VendingMachineController controller; // a pointer to the controller to inform of any scans
	
	/**
	 * Constructor for a DemoCardScanner
	 * 
	 * @param controller the controller class of the system
	 */
	public DemoCardScanner(VendingMachineController controller) {
		this.controller = controller;
	}
	
	/**
	 * Called to scan a card presented
	 * to the card scanner
	 * 
	 * @param card the card presented
	 */
	public void scan(LoyaltyCard card) {
		controller.cardScanned(card);
	}
	
	@Override
	public void reset() {
		listening = true; // listen for new card scans
	}

	@Override
	public void disable() {
		listening = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*
		 * The `linkedAccount` variable will hold an account
		 * number of a demo bank account when a valid card
		 * is presented
		 */
		int linkedAccount = 0;
		
		switch(e.getActionCommand()) { // only need to set for valid cards
		case "card1":
			linkedAccount = 123456789;
			break;
		case "card2":
			linkedAccount = 987654321;
			break;
		}
		
		if(listening) {	// only scan the card when listening for scans
			scan(new LoyaltyCard(linkedAccount));
		}
		
	}
	
}
