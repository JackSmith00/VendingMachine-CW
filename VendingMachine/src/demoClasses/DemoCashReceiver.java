package demoClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Currency;

import hardwareComponents.CashReceiver;
import paymentMethods.Cash;
import vendingMachine.VendingMachineController;

/**
 * A class to mimic the functionality of the 
 * cash receiver hardware in the vending machine
 * 
 * @author Jack
 *
 */
public class DemoCashReceiver implements CashReceiver, ActionListener {
	
	private final Currency acceptedCurrency = Currency.getInstance("GBP"); // only accept specified currency
	private VendingMachineController controller; // a pointer to the controller to inform of any changes to credit
	
	/**
	 * Constructor for a DemoCashReceiver
	 * 
	 * @param controller the controller class of the system
	 */
	public DemoCashReceiver(VendingMachineController controller) {
		this.controller = controller;
	}
	
	/**
	 * Called to mimic the insertion of
	 * cash into the cash receiver
	 * @param cash the cash that is inserted
	 */
	public void insert(Cash cash) {
		// do not accept fake or foreign cash
		if(cash.isFake() || cash.getCurrency() != acceptedCurrency) {
			/*
			 * for demonstration purposes.
			 * prints the cash to the console that would be
			 * ejected immediately by the CashReceiver
			 */
			System.out.println(cash.getCurrency().getSymbol() + cash.getValue().doubleValue() + " rejected");
			return;
		}
		
		// increment credit by correct amount
		controller.cashInserted(cash.getValue());
	}
	
	@Override
	public void eject(BigDecimal amount) {
		// demonstration purposes - would be a physical action undertaken
		System.out.println(acceptedCurrency.getSymbol() + amount.floatValue() + " dispensed");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * When a coin button is selected, mimic an
		 * insertion of the corresponding Cash object
		 */
		switch(e.getActionCommand()) {
		case "1p":
			insert(new Cash(Currency.getInstance("GBP"), 0.01));
			break;
		case "2p":
			insert(new Cash(Currency.getInstance("GBP"), 0.02));
			break;
		case "5p":
			insert(new Cash(Currency.getInstance("GBP"), 0.05));
			break;
		case "10p":
			insert(new Cash(Currency.getInstance("GBP"), 0.1));
			break;
		case "20p":
			insert(new Cash(Currency.getInstance("GBP"), 0.2));
			break;
		case "50p":
			insert(new Cash(Currency.getInstance("GBP"), 0.5));
			break;
		case "£1":
			insert(new Cash(Currency.getInstance("GBP"), 1.0));
			break;
		case "£2":
			insert(new Cash(Currency.getInstance("GBP"), 2));
			break;
		case "£5":
			insert(new Cash(Currency.getInstance("GBP"), 5));
			break;
		case "£10":
			insert(new Cash(Currency.getInstance("GBP"), 10));
			break;
		case "£20":
			insert(new Cash(Currency.getInstance("GBP"), 20));
			break;
		case "1pee":
			insert(new Cash(Currency.getInstance("GBP"), 0.01, true));
			break;
		case "2c":
			insert(new Cash(Currency.getInstance("GBP"), 0.02, true));
			break;			
		case "5d":
			insert(new Cash(Currency.getInstance("GBP"), 0.05, true));
			break;			
		case "ten":
			insert(new Cash(Currency.getInstance("GBP"), 0.1, true));
			break;			
		case "2-0p":
			insert(new Cash(Currency.getInstance("GBP"), 0.2, true));
			break;			
		case "50":
			insert(new Cash(Currency.getInstance("GBP"), 0.5, true));
			break;			
		case "$1":
			insert(new Cash(Currency.getInstance("USD"), 1));
			break;			
		case "€2":
			insert(new Cash(Currency.getInstance("EUR"), 2));
			break;			
		case "$5":
			insert(new Cash(Currency.getInstance("USD"), 5));
			break;			
		case "¥10":
			insert(new Cash(Currency.getInstance("JPY"), 10));
			break;			
		case "£2Ø":
			insert(new Cash(Currency.getInstance("GBP"), 20, true));
			break;			
		}
	}
}
