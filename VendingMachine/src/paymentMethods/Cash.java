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
	private BigDecimal value;
	/*
	 * BigDecimal has been used throughout the program as
	 * it more accurate than floats and doubles that
	 * have small rounding inconsistencies
	 * 
	 * Java Practices, 2018. Representing money [online].
	 * Available from: http://www.javapractices.com/topic/TopicAction.do?Id=13;
	 * [Accessed 11 March 2022]
	 *  
	 */
	private boolean fake;
	
	/**
	 * Creates a new Cash object
	 * 
	 * @param currency the currency of the cash
	 * @param value the value of the cash
	 * @param fake true when cash is fake
	 */
	public Cash(Currency currency, double value, boolean fake) {
		this.currency = currency;
		this.value = BigDecimal.valueOf(value);
		this.fake = fake;
	}
	
	/**
	 * Creates a new Cash object
	 * 
	 * @param currency the currency of the cash
	 * @param value the value of the cash
	 */
	public Cash(Currency currency, double value) {
		// call other constructor with fake set to false
		this(currency, value, false);
	}
	
	/**
	 * @return true when the cash is fake, false otherwise
	 */
	public boolean isFake() {
		return fake;
	}
	
	/**
	 * @return the currency of the cash
	 */
	public Currency getCurrency() {
		return currency;
	}
	
	/**
	 * @return the value of the cash
	 */
	public BigDecimal getValue() {
		return value;
	}

}
