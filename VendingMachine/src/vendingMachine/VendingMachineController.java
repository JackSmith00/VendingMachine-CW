package vendingMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import gui.VendingMachineGUI;
import hardwareComponents.CardScanner;
import hardwareComponents.CashReceiver;
import hardwareComponents.ProductDispenser;
import hardwareComponents.StockScanner;

public class VendingMachineController implements ActionListener {

	private EnumMap<Product, BigDecimal> prices = new EnumMap<Product, BigDecimal>(Product.class);
	private Timer timer;
	
	private StockScanner cokeScanner, lemonadeScanner, tangoScanner, waterScanner, pepsiScanner, spriteScanner;
	private ProductDispenser dispenser;
	private CashReceiver cashReceiver;
	private CardScanner cardScanner;
	
	private VendingMachineGUI gui;
	private JLabel output;
	
	private boolean cardCustomer = false;
	
	public static void main(String[] args) {
		
		VendingMachineController controller = new VendingMachineController();
		controller.gui = new VendingMachineGUI(controller, controller.cashReceiver, controller.cardScanner);
		controller.gui.setVisible(true);
		controller.output = controller.gui.getOutput();
		
	}
	
	/**
	 * Create a new VendingMachineController object
	 * that will initialise scanners and set the
	 * prices of products to the predefined amounts
	 */
	public VendingMachineController() {
		initialiseScanners();
		dispenser = new ProductDispenser(cokeScanner, lemonadeScanner, tangoScanner, waterScanner, pepsiScanner, spriteScanner);
		cashReceiver = new CashReceiver(this);
		cardScanner = new CardScanner(this);
		timer = new Timer();
		setPrices(1.5, 1.2, 1.4, 1, 1.3, 1.2);
	}
	
	/**
	 * Give initial stock values to each scanner
	 * in the vending machine to replicate
	 * the actual stock
	 */
	private void initialiseScanners() {
		cokeScanner = new StockScanner(5);
		lemonadeScanner = new StockScanner(7);
		tangoScanner = new StockScanner(2);
		waterScanner = new StockScanner(10);
		pepsiScanner = new StockScanner(4);
		spriteScanner = new StockScanner(0);
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
	
	public void applyPercentageDiscount(double discountBy) {
		for(Product p: prices.keySet()) {
			prices.replace(p, prices.get(p).multiply(BigDecimal.valueOf(1 - discountBy)));
		}
		gui.updatePrices();
	}
	
	public void applyAmountDiscount(double discountBy) {
		for(Product p: prices.keySet()) {
			prices.replace(p, prices.get(p).subtract(BigDecimal.valueOf(discountBy)));
		}
		gui.updatePrices();		
	}
	
	public double getPrice(Product product) {
		return prices.get(product).doubleValue();
	}
	
	public void creditUpdated() {
		if(!cardCustomer) {
			output.setText("Credit: " + gui.formatCurrency(cashReceiver.getCredit().doubleValue()));
			gui.setSelectorEnabled(true);
		}
	}
	
	public void cardScanned(boolean valid) {
		if(valid) { // when a legitimate card is scanned
			cardCustomer = true;
			output.setText("Account linked");
			applyAmountDiscount(0.25); // discount by 25p
			gui.setSelectorEnabled(true);
		} else { // when card is not legitimate
			String originalText = output.getText();
			output.setText("Card not recognised");
			/*
			 * TODO
			 * https://www.youtube.com/watch?v=hhnkP2bR5EI
			 */
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					if(output.getText().compareTo("Card not recognised") == 0) {						
						output.setText(originalText);
					}
				}
			}, 1600);
		}
	}
	
	//TODO
	private void purchase(Product product) {
		
	}
	
	//TODO
	private void clear() {
		gui.clearSelection();
	}
	
	//TODO
	private void cancel() {
		clear();
		gui.setSelectorEnabled(false);
		
		boolean cashReturned = false;
		
		if(cardCustomer) {
			cardScanner.reset();
			output.setText("Signed out");
		}
		
		if(cashReceiver.getCredit().compareTo(BigDecimal.valueOf(0)) > 0) {
			double ejected = cashReceiver.ejectAll();
			cashReturned = true;
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					output.setText("Ejected: " + gui.formatCurrency(ejected));
				}
			}, cardCustomer ? 1500 : 0);
			
		}
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(output.getText().startsWith("Ejected: ") || output.getText().compareTo("Signed out") == 0) {
					output.setText(gui.getPlaceholder());
				}
			}
		}, cardCustomer && cashReturned ? 3000 : 1600);
		
		cardCustomer = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
		case "Purchase":
			purchase(gui.getSelectedProduct());
			break;
		case "Clear":
			clear();
			break;
		case "Cancel":
			cancel();
			break;
		}
		
	}
}
