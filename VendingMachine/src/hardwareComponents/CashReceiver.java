package hardwareComponents;

import java.math.BigDecimal;
import java.util.Currency;

import paymentMethods.Cash;

/**
 * A class to mimic the functionality of the 
 * cash receiver hardware in the vending machine
 * 
 * @author Jack
 *
 */
public class CashReceiver {
	
	private final Currency acceptedCurrency = Currency.getInstance("GBP");
	private BigDecimal credit = BigDecimal.valueOf(0);
	
	public boolean insert(Cash cash) {
		// do not accept fake or foreign cash
		if(cash.isFake() || cash.getCurrency() != acceptedCurrency) {
			return false; // cash rejected
		}
		
		// increment credit by correct amount
		credit.add(cash.getValue());
		
		return true; // cash accepted
	}
	
	public void ejectAll() {
		credit = BigDecimal.valueOf(0);
	}
	
	public BigDecimal getCredit() {
		return credit;
	}
	
}
