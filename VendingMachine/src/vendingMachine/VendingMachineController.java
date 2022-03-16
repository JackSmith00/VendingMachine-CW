package vendingMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import demoClasses.DemoBank;
import demoClasses.DemoCardScanner;
import demoClasses.DemoCashReceiver;
import demoClasses.DemoDispenser;
import demoClasses.DemoStockScanner;
import demoClasses.VirtualWallet;
import externalElements.Bank;
import gui.VendingMachineGUI;
import hardwareComponents.CardScanner;
import hardwareComponents.CashReceiver;
import hardwareComponents.Dispenser;
import hardwareComponents.StockScanner;
import paymentMethods.LoyaltyCard;

/**
 * A controller for the vending machine. In charge
 * of all logic and communication between elements
 * within the vending machine
 * 
 * @author Jack
 *
 */
public class VendingMachineController implements ActionListener {
	
	// Attributes
	private Timer timer;
	private boolean cardCustomer = false;
	private final double discount = 0.25;
	
	// Payment attributes
	private LoyaltyCard scannedCard; // holds the currently scanned valid card
	private BigDecimal credit = BigDecimal.valueOf(0); // holds any inserted credit balance

	// Linking components
	private EnumMap<Product, BigDecimal> prices = new EnumMap<Product, BigDecimal>(Product.class);
	private EnumMap<Product, StockScanner> stockScanners = new EnumMap<Product, StockScanner>(Product.class);
	
	// Hardware components
	private StockScanner cokeScanner, lemonadeScanner, tangoScanner, waterScanner, pepsiScanner, spriteScanner;
	private Dispenser dispenser;
	private CashReceiver cashReceiver;
	private CardScanner cardScanner;
	
	// External components
	private Bank linkedBank; // the bank linked to the scanner
	
	// GUI components
	private VendingMachineGUI gui;
	private JLabel output;
	
	public static void main(String[] args) {
		
		// initialise a controller
		VendingMachineController controller = new VendingMachineController();
		
		// initialise the gui
		controller.gui = new VendingMachineGUI(controller);
		controller.gui.setBounds(200, 80, 500, 700);
		controller.gui.setVisible(true);
		
		// create a direct reference to the gui output
		controller.output = controller.gui.getOutput();
		
		// initialise a virtual wallet for demo purposes
		VirtualWallet virtualWallet = new VirtualWallet((DemoCashReceiver)controller.cashReceiver, (DemoCardScanner)controller.cardScanner);
		virtualWallet.setLocation(720, 150);
		virtualWallet.setVisible(true);	
	}
	
	/**
	 * Create a new VendingMachineController object
	 * that will initialise demo hardware and set the
	 * prices of products to the predefined amounts
	 */
	public VendingMachineController() {
		initialiseScanners();
		dispenser = new DemoDispenser((DemoStockScanner)cokeScanner, (DemoStockScanner)lemonadeScanner, (DemoStockScanner)tangoScanner, (DemoStockScanner)waterScanner, (DemoStockScanner)pepsiScanner, (DemoStockScanner)spriteScanner);
		cashReceiver = new DemoCashReceiver(this);
		cardScanner = new DemoCardScanner(this);
		linkedBank = new DemoBank();
		timer = new Timer();
		setPrices(1.5, 1.2, 1.4, 1, 1.3, 1.2); // initial prices from original GUI provided
	}
	
	/**
	 * Give initial stock values to each demo 
	 * scanner in the vending machine to 
	 * replicate the actual stock
	 */
	private void initialiseScanners() {
		cokeScanner = new DemoStockScanner(5);
		stockScanners.put(Product.COKE, cokeScanner);
		
		lemonadeScanner = new DemoStockScanner(7);
		stockScanners.put(Product.LEMONADE, lemonadeScanner);
		
		tangoScanner = new DemoStockScanner(2);
		stockScanners.put(Product.TANGO, tangoScanner);
		
		waterScanner = new DemoStockScanner(10);
		stockScanners.put(Product.WATER, waterScanner);

		pepsiScanner = new DemoStockScanner(4);
		stockScanners.put(Product.PEPSI, pepsiScanner);
		
		spriteScanner = new DemoStockScanner(0);
		stockScanners.put(Product.SPRITE, spriteScanner);
	}
	
	/**
	 * Sets the prices of the products in the
	 * vending machine
	 */
	public void setPrices(double coke, double lemonade, double tango, double water, double pepsi, double sprite) {
		if(!prices.containsKey(Product.COKE)) { // if values have not already been added, set them up
			prices.put(Product.COKE, BigDecimal.valueOf(coke));
			prices.put(Product.LEMONADE, BigDecimal.valueOf(lemonade));
			prices.put(Product.TANGO, BigDecimal.valueOf(tango));
			prices.put(Product.WATER, BigDecimal.valueOf(water));
			prices.put(Product.PEPSI, BigDecimal.valueOf(pepsi));
			prices.put(Product.SPRITE, BigDecimal.valueOf(sprite));
		} else { // products already have prices
			prices.replace(Product.COKE, BigDecimal.valueOf(coke));
			prices.replace(Product.LEMONADE, BigDecimal.valueOf(lemonade));
			prices.replace(Product.TANGO, BigDecimal.valueOf(tango));
			prices.replace(Product.WATER, BigDecimal.valueOf(water));
			prices.replace(Product.PEPSI, BigDecimal.valueOf(pepsi));
			prices.replace(Product.SPRITE, BigDecimal.valueOf(sprite));
		}
	}
	
	/**
	 * Discounts the product prices by a given percentage
	 * 
	 * @param discountBy the percentage to discount prices by
	 */
	public void applyPercentageDiscount(double discountBy) {
		for(Product p: prices.keySet()) { // loop each product
			prices.replace(p, prices.get(p).multiply(BigDecimal.valueOf(1 - discountBy))); // apply discount
		}
		gui.updatePrices(); // update the gui
	}
	
	/**
	 * Discounts the product prices by a given amount
	 * 
	 * @param discountBy the amount to discount prices by
	 */
	public void applyAmountDiscount(double discountBy) {
		for(Product p: prices.keySet()) {
			prices.replace(p, prices.get(p).subtract(BigDecimal.valueOf(discountBy))); // apply discount
		}
		gui.updatePrices();	// update the gui
	}
	
	/**
	 * Returns the price of a product
	 * 
	 * @param product the product to find the price of
	 * @return the price of the product
	 */
	public double getPrice(Product product) {
		return prices.get(product).doubleValue();
	}
	
	public void cashInserted(BigDecimal value) {
		
		credit = credit.add(value);
	
		if(!cardCustomer) { // only update gui if not a card customer
			output.setText("Credit: " + gui.formatCurrency(credit.doubleValue()));
			gui.setSelectorEnabled(true); // cash inserted so products can be selected
		}
	}
	
	/**
	 * Charges the held credit by the amount
	 * specified
	 * 
	 * @param amount the amount to debit the credit
	 * @return a boolean indication of the outcome,
	 * true where credit is successfully debited, false otherwise
	 */
	private boolean chargeCredit(BigDecimal amount) {
		if(credit.compareTo(amount) >= 0) {
			// where there are sufficient funds
			credit = credit.subtract(amount);
			return true;
		} else {
			// where there are insufficient funds
			return false;
		}
	}
	
	private double dispenseChange() {
		cashReceiver.eject(credit);
		double ejected = credit.doubleValue();
		credit = BigDecimal.valueOf(0);
		// potentially wait to change text, allowing customer to read the previous output
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// make sure output text has not already changed
				if((cardCustomer && output.getText().compareTo("Singed out") == 0) || !cardCustomer) {
					output.setText("Ejected: " + gui.formatCurrency(ejected));
				}
			}
		}, cardCustomer ? 1500 : 0); // only wait if the customer is a cardCustomer, as previous message needs to be shown
		return ejected;
	}
	
	/**
	 * Called whenever a card is scanned by
	 * the card scanner
	 * 
	 * @param valid true when the scanned card is valid, false otherwise
	 */
	public void cardScanned(LoyaltyCard card) {
		if(linkedBank.validateCard(card)) {
			// successful validation
			scannedCard = card; // store card as in use
			cardScanner.disable(); // don't listen to additional card scans
			
			cardCustomer = true; // customer is now a card customer
			output.setText(VendingMachineGUI.CARD_LOGIN_MESSAGE); // show successful log in
			applyAmountDiscount(discount); // discount by 25p
			gui.setSelectorEnabled(true); // account linked so products can be selected
		} else { // when card is not legitimate
			String originalText = output.getText(); // allows output to return to original text after failed message
			output.setText("Card not recognised"); // show login fail message
			/*
			 * Wait a certain amount of time before reverting
			 * back to the original output text
			 * 
			 * The code used below was influenced by the
			 * following resource:
			 * 
			 * Coding Blocks, 2018. Java: The correct (and incorrect) way to add a delay into your code without consuming 100% CPU [online video].
			 * Available from: https://www.youtube.com/watch?v=hhnkP2bR5EI
			 * 
			 * The code was not copied verbatim, it was adapted
			 * for use within this vending machine program
			 */
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// make sure output text has not changed already before reverting
					if(output.getText().compareTo("Card not recognised") == 0) {						
						output.setText(originalText);
					}
				}
			}, 1500); // wait 1.5 seconds before reverting
		}
	}
	
	/**
	 * Used to charge the account linked to
	 * the currently scanned card
	 * 
	 * @param amount the amount to charge the account
	 * @return a boolean indicating the outcome of the charge,
	 * true when successfully debited, false otherwise
	 */
	public boolean chargeAccount(BigDecimal amount) {
		return linkedBank.chargeAccount(scannedCard.getCardNumber(), amount);
	}
	
	/**
	 * Called when a customer attempts
	 * to purchase a product
	 * 
	 * @param product the product the customer wishes to purchase
	 */
	private void purchase(Product product) {
		// check drink availability
		if(!stockScanners.get(product).checkAvailability()) {
			// out of stock
			String originalText = output.getText(); // allows output to return to original text after out-of-stock message
			output.setText("Product not available, sorry");
			
			// wait before reverting back to the original output text
			timer.schedule(new TimerTask() {	
				@Override
				public void run() {
					// make sure output text has not changed already before reverting
					if(output.getText().compareTo("Product not available, sorry") == 0) {
						output.setText(originalText);
					}
				}
			}, 1500); // wait 1.5 seconds before reverting
			return; // take no further action
		}
		
		// product in stock
		if(cardCustomer) {
			// try to charge from bank
			if(chargeAccount(prices.get(product))) {
				// where there are sufficient funds
				dispenser.dispense(product);
				output.setText("Purchase successful");
			} else {
				// where there are insufficient funds
				output.setText("Insufficient funds");
			}	
		} else { // cash customer
			// try to charge from inserted cash
			if(chargeCredit(prices.get(product))) {
				// where there are sufficient funds
				dispenser.dispense(product);
				output.setText("Purchase successful");
			} else {
				// where there are insufficient funds
				output.setText("Insufficient funds");
			}
		}
		
		// next steps depending on type of customer and amount of credit
		if(credit.compareTo(BigDecimal.valueOf(0)) <= 0 && !cardCustomer) {
			// where they are not a card customer and there is no credit remaining
			gui.setSelectorEnabled(false); // can no longer select drinks as credit == 0
			
			// wait to change text, allowing customer to read the previous output
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// make sure output text has not already changed
					if(output.getText().compareTo("Purchase successful") == 0) {
						// revert to placeholder text
						output.setText(VendingMachineGUI.PLACEHOLDER);
					}
				}
			}, 1500); // wait 1.5 seconds
		} else if(cardCustomer) {
			// where they are a card customer, account stays linked for future purchases
			
			// wait to change text, allowing customer to read the previous output
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// make sure output text has not already changed
					if(output.getText().compareTo("Purchase successful") == 0
							|| output.getText().compareTo("Insufficient funds") == 0) {
						// revert to account linked message
						output.setText(VendingMachineGUI.CARD_LOGIN_MESSAGE);
					}
				}
			}, 1500); // wait 1.5 seconds
		} else {
			// where they are not a card customer, but still have credit remaining
			
			// wait to change text, allowing customer to read the previous output
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// make sure output text has not already changed
					if(output.getText().compareTo("Purchase successful") == 0
							|| output.getText().compareTo("Insufficient funds") == 0) {
						// show remaining credit
						output.setText("Credit: " + gui.formatCurrency(credit.doubleValue()));
					}
				}
			}, 1500); // wait 1.5 seconds
		}
	}
	
	/**
	 * Clears the current drink selection
	 */
	private void clear() {
		gui.clearSelection();
	}
	
	/**
	 * Cancels the interaction with the
	 * vending machine
	 */
	private void cancel() {
		clear(); // clear the current selection, ready for the next customer
		gui.setSelectorEnabled(false); // can no longer select products
		
		boolean cashReturned = false; // indicates if any cash has been returned from the machine
		
		if(cardCustomer) {
			cardScanner.reset(); // reset the card scanner, logging the customer out
			scannedCard = null;
			applyAmountDiscount(-discount); // remove applied discount
			output.setText("Signed out");
		}
		
		if(credit.compareTo(BigDecimal.valueOf(0)) > 0) {
			// if there is still credit in the machine, return it
			dispenseChange();
			cashReturned = true;
		}
		// wait to change text, allowing customer to read the previous output
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// make sure output text has not already changed
				if(output.getText().startsWith("Ejected: ") || output.getText().compareTo("Signed out") == 0) {
					// return to placeholder text
					output.setText(VendingMachineGUI.PLACEHOLDER);
				}
			}
		}, cardCustomer && cashReturned ? 3000 : 1500); // wait 3 seconds if the cash is returned to a card customer, otherwise wait 1.5 seconds
		
		// no longer a card customer
		cardCustomer = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// handle different action buttons
		switch(e.getActionCommand()) {
		case "Purchase": // purchase button selected
			if(gui.getSelectedProduct() != null) { // make sure a product is selected before attempting to purchase
				purchase(gui.getSelectedProduct()); // try to purchase product
			}
			break; // no further action needed
		case "Clear": // clear button selected
			clear(); // clear user selection
			break; // no further action
		case "Cancel": // cancel button selected
			cancel(); // log out customer, eject remaining credit
			break; // no further action
		}
		
	}
}
