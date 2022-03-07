package vendingMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.EnumMap;

import javax.swing.JFrame;
import javax.swing.JLabel;

import gui.VendingMachineGUI;
import hardwareComponents.CardScanner;
import hardwareComponents.CashReceiver;
import hardwareComponents.ProductDispenser;
import hardwareComponents.StockScanner;

public class VendingMachineController implements ActionListener {

	private EnumMap<Product, BigDecimal> prices = new EnumMap<Product, BigDecimal>(Product.class);
	private StockScanner cokeScanner, lemonadeScanner, tangoScanner, waterScanner, pepsiScanner, spriteScanner;
	private ProductDispenser dispenser;
	private CashReceiver cashReceiver;
	private CardScanner cardScanner;
	
	private VendingMachineGUI gui;
	private JLabel output;
	
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
		cardScanner = new CardScanner();
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
	
	public double getPrice(Product product) {
		return prices.get(product).doubleValue();
	}
	
	public void creditUpdated() {
		output.setText("Credit: " + gui.formatCurrency(cashReceiver.getCredit().doubleValue()));
		gui.setSelectorEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
