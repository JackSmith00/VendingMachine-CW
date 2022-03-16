package demoClasses;

import java.util.EnumMap;

import hardwareComponents.Dispenser;
import vendingMachine.Product;

/**
 * Represents the dispenser hardware of the
 * Vending Machine. Simplified implementation for
 * demo purposes.
 * 
 * @author Jack
 *
 */
public class DemoDispenser implements Dispenser {

	private EnumMap<Product, DemoStockScanner> shelf = new EnumMap<Product, DemoStockScanner>(Product.class);
	// holds a link between each product and its associated scanner
	
	/**
	 * Creates a new product dispenser, with a reference
	 * to each StockScanner to be able mimic a dispense
	 * of the stock that is represented within them
	 * 
	 * @param coke the DemoStockScanner for Coke products
	 * @param lemonade the DemoStockScanner for Lemonade products
	 * @param tango the DemoStockScanner for Tango products
	 * @param water the DemoStockScanner for Water products
	 * @param pepsi the DemoStockScanner for Pepsi products
	 * @param sprite the DemoStockScanner for Sprite products
	 */
	public DemoDispenser(DemoStockScanner coke, DemoStockScanner lemonade, DemoStockScanner tango, DemoStockScanner water, DemoStockScanner pepsi, DemoStockScanner sprite) {
		// populate the shelf EnumMap
		shelf.put(Product.COKE, coke);
		shelf.put(Product.LEMONADE, lemonade);
		shelf.put(Product.TANGO, tango);
		shelf.put(Product.WATER, water);
		shelf.put(Product.PEPSI, pepsi);
		shelf.put(Product.SPRITE, sprite);
	}
	
	@Override
	public void dispense(Product product) {
		shelf.get(product).setStock(shelf.get(product).getStock() - 1); // decrease the stock of the product by 1
		System.out.println(product + " dispensed"); // mimic a product dispensed in the console
	}
	
}
