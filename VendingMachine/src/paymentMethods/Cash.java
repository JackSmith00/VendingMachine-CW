package paymentMethods;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * A class to represent the cash that would
 * be inserted into the vending machine
 * 
 * @author Jack
 *
 */
public class Cash {
	
	private Currency currency;
	private BigDecimal value; // https://softwareengineering.stackexchange.com/questions/228584/why-is-bigdecimal-the-best-data-type-for-currency
	private boolean fake;
	
	public Cash(Currency currency, double value, boolean fake) {
		this.currency = currency;
		this.value = BigDecimal.valueOf(value);
		this.fake = fake;
	}
	
	public Cash(Currency currency, double value) {
		this(currency, value, false);
	}
	
	public boolean isFake() {
		return fake;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public BigDecimal getValue() {
		return value;
	}

}
