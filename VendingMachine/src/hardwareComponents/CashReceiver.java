package hardwareComponents;

import java.math.BigDecimal;

/**
 * An interface of a cash receiver that accepts and debits
 * money and keeps track of the current credit
 * 
 * @author Jack
 *
 */
public interface CashReceiver {

	/**
	 * Ejects a specified amount from the CashReceiver.
	 * To be used to dispense change or when a
	 * sale is cancelled
	 */
	public void eject(BigDecimal amount);
}
