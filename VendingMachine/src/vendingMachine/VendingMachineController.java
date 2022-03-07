package vendingMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

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

public class VendingMachineController implements ActionListener {

	private EnumMap<Product, BigDecimal> prices = new EnumMap<Product, BigDecimal>(Product.class);
	private EnumMap<Product, StockScanner> stockScanners = new EnumMap<Product, StockScanner>(Product.class);
	private Timer timer;
	
	private StockScanner cokeScanner, lemonadeScanner, tangoScanner, waterScanner, pepsiScanner, spriteScanner;
	private Dispenser dispenser;
	private CashReceiver cashReceiver;
	private CardScanner cardScanner;
	
	private VendingMachineGUI gui;
	private JLabel output;
	
	private boolean cardCustomer = false;
	private final double discount = 0.25;
	
	public static void main(String[] args) {
		
		VendingMachineController controller = new VendingMachineController();
		
		controller.gui = new VendingMachineGUI(controller);
		controller.gui.setBounds(200, 80, 500, 700);
		controller.gui.setVisible(true);
		
		VirtualWallet virtualWallet = new VirtualWallet((DemoCashReceiver)controller.cashReceiver, (DemoCardScanner)controller.cardScanner);
		virtualWallet.pack();
		virtualWallet.setLocation(720, 150);
		virtualWallet.setVisible(true);
		
		controller.output = controller.gui.getOutput();
		
	}
	
	/**
	 * Create a new VendingMachineController object
	 * that will initialise scanners and set the
	 * prices of products to the predefined amounts
	 */
	public VendingMachineController() {
		initialiseScanners();
		dispenser = new DemoDispenser((DemoStockScanner)cokeScanner, (DemoStockScanner)lemonadeScanner, (DemoStockScanner)tangoScanner, (DemoStockScanner)waterScanner, (DemoStockScanner)pepsiScanner, (DemoStockScanner)spriteScanner);
		cashReceiver = new DemoCashReceiver(this);
		cardScanner = new DemoCardScanner(this);
		timer = new Timer();
		setPrices(1.5, 1.2, 1.4, 1, 1.3, 1.2);
	}
	
	/**
	 * Give initial stock values to each scanner
	 * in the vending machine to replicate
	 * the actual stock
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
			output.setText(VendingMachineGUI.CARD_LOGIN_MESSAGE);
			applyAmountDiscount(discount); // discount by 25p
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
		// check drink availability
		if(!stockScanners.get(product).checkAvailability()) {
			// out of stock
			String originalText = output.getText();
			output.setText("Product not available, sorry");
			timer.schedule(new TimerTask() {	
				@Override
				public void run() {
					if(output.getText().compareTo("Product not available, sorry") == 0) {
						output.setText(originalText);
					}
				}
			}, 1500);
			return;
		}
		
		// product in stock
		if(cardCustomer) {
			// try to charge from bank
			if(Bank.chargeAccount(cardScanner.getScannedCard().getCardNumber(), prices.get(product))) {
				// where there are sufficient funds
				dispenser.dispense(product);
				output.setText("Purchase successful");
			} else {
				// where there are insufficient funds
				output.setText("Insufficient funds");
			}	
		} else { // cash customer
			// try to charge from inserted cash
			if(cashReceiver.charge(prices.get(product))) {
				// where there are sufficient funds
				dispenser.dispense(product);
				output.setText("Purchase successful");
			} else {
				// where there are insufficient funds
				output.setText("Insufficient funds");
			}
		}
		if(cashReceiver.getCredit().compareTo(BigDecimal.valueOf(0)) <= 0 && !cardCustomer) {
			gui.setSelectorEnabled(false);
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(output.getText().compareTo("Purchase successful") == 0) {
						output.setText(VendingMachineGUI.PLACEHOLDER);
					}
				}
			}, 1500);
		} else if(cardCustomer) {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(output.getText().compareTo("Purchase successful") == 0
							|| output.getText().compareTo("Insufficient funds") == 0) {
						output.setText(VendingMachineGUI.CARD_LOGIN_MESSAGE);
					}
				}
			}, 1500);
		} else {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(output.getText().compareTo("Purchase successful") == 0
							|| output.getText().compareTo("Insufficient funds") == 0) {
						output.setText("Credit: " + gui.formatCurrency(cashReceiver.getCredit().doubleValue()));
					}
				}
			}, 1500);
			
		}
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
			double ejected = cashReceiver.eject();
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
					output.setText(VendingMachineGUI.PLACEHOLDER);
				}
			}
		}, cardCustomer && cashReturned ? 3000 : 1600);
		
		applyAmountDiscount(-discount);
		cardCustomer = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
		case "Purchase":
			if(gui.getSelectedProduct() != null) {
				purchase(gui.getSelectedProduct());
			}
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
