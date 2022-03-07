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
	
	private final Currency acceptedCurrency = Currency.getInstance("GBP");
	private BigDecimal credit = BigDecimal.valueOf(0);
	private VendingMachineController controller;
	
	public DemoCashReceiver(VendingMachineController controller) {
		this.controller = controller;
	}
	
	public void insert(Cash cash) {
		// do not accept fake or foreign cash
		if(cash.isFake() || cash.getCurrency() != acceptedCurrency) {
			// show that currency is being rejected for testing
			System.out.println(cash.getCurrency().getSymbol() + cash.getValue().doubleValue() + " rejected");
		}
		
		// increment credit by correct amount
		credit = credit.add(cash.getValue());
	}
	
	@Override
	public boolean charge(BigDecimal amount) {
		if(credit.compareTo(amount) >= 0) {
			// where there are sufficient funds
			credit = credit.subtract(amount);
			return true;
		} else {
			// where there are insufficient funds
			return false;
		}
	}
	
	public boolean isEmpty() {
		return credit.compareTo(BigDecimal.valueOf(0)) <= 0;
	}
	
	public double eject() {
		double ejected = credit.doubleValue();
		credit = BigDecimal.valueOf(0);
		return ejected;
	}
	
	@Override
	public BigDecimal getCredit() {
		return credit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
		
		if(!isEmpty()) {
			controller.creditUpdated();
		}
	}
}
