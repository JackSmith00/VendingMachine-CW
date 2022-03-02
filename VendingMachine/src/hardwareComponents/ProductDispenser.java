package hardwareComponents;

import java.util.EnumMap;

import vendingMachine.Product;

/**
 * Represents the dispenser hardware of the
 * Vending Machine. Simplified implementation for
 * demo purposes. Used to dispense products from the
 * vending machine.
 * 
 * @author Jack
 *
 */
public class ProductDispenser {

	private EnumMap<Product, StockScanner> shelf = new EnumMap<Product, StockScanner>(Product.class); // holds a link between each product and its associated scanner
	
	/**
	 * Creates a new product dispenser, with a reference
	 * to each StockScanner to be able to access the
	 * stock that is represented within them
	 * 
	 * @param coke the StockScanner for Coke products
	 * @param lemonade the StockScanner for Lemonade products
	 * @param tango the StockScanner for Tango products
	 * @param water the StockScanner for Water products
	 * @param pepsi the StockScanner for Pepsi products
	 * @param sprite the StockScanner for Sprite products
	 */
	public ProductDispenser(StockScanner coke, StockScanner lemonade, StockScanner tango, StockScanner water, StockScanner pepsi, StockScanner sprite) {
		shelf.put(Product.COKE, coke);
		shelf.put(Product.LEMONADE, lemonade);
		shelf.put(Product.TANGO, tango);
		shelf.put(Product.WATER, water);
		shelf.put(Product.PEPSI, pepsi);
		shelf.put(Product.SPRITE, sprite);
	}
	
	/**
	 * Dispenses one item from the vending machine
	 * 
	 * @param product the product to dispense
	 */
	public void dispense(Product product) {
		shelf.get(product).setStock(shelf.get(product).getStock() - 1); // decrease the stock of the product by 1
	}
	
}
