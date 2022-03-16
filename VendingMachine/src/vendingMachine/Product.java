package vendingMachine;

/**
 * Represents all products that are sold
 * by the vending machine
 * 
 * @author Jack
 *
 */
public enum Product {

	// Products
	COKE,
	LEMONADE,
	TANGO,
	WATER,
	PEPSI,
	SPRITE;
	
	@Override
	public String toString() {
		switch(this) {
		case COKE:
			return "Coke";
		case LEMONADE:
			return "Lemonade";
		case TANGO:
			return "Tango";
		case WATER:
			return "Water";
		case PEPSI:
			return "Pepsi";
		case SPRITE:
			return "Sprite";
		default:
			return null;
		
		}
	}
}
