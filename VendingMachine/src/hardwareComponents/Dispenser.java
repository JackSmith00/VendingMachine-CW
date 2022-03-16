package hardwareComponents;

import vendingMachine.Product;

/**
 * Represents the dispenser hardware of the Vending Machine
 * 
 * @author Jack
 *
 */
public interface Dispenser {

	/**
	 * Dispenses one item from the vending machine
	 * 
	 * @param product the product to dispense
	 */
	public void dispense(Product product);
}
