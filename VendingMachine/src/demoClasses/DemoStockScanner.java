package demoClasses;

import hardwareComponents.StockScanner;

/**
 * A class to mimic a stock scanner within
 * a vending machine
 * 
 * @author Jack
 *
 */
public class DemoStockScanner implements StockScanner {
	
	private int stock; // mimics physical stock that would be on the vending machine shelf
	
	/**
	 * Constructs a DemoStockScanner
	 * 
	 * @param initialStock the initial stock of the product this scanner would scan
	 */
	public DemoStockScanner(int initialStock) {
		this.stock = initialStock;
	}
	
	@Override
	public boolean checkAvailability() {
		return stock > 0;
	}
	
	/**
	 * For demonstration purposes. Allows
	 * the DemoDispenser to access the
	 * stock represented in this class
	 * 
	 * @return quantity of stock on this scanners shelf
	 */
	protected int getStock() {
		return stock;
	}
	
	/**
	 * For demonstration purposes. Allows
	 * the DemoDispenser to alter the
	 * stock represented in this class
	 * 
	 * @return quantity of stock on this scanners shelf
	 */
	protected void setStock(int stock) {
		this.stock = stock;
	}

}
