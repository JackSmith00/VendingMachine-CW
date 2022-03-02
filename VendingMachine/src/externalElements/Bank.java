package externalElements;

import java.math.BigDecimal;
import java.util.HashMap;

import paymentMethods.LoyaltyCard;

/**
 * A class for demonstration purposes to
 * mimic a bank that controls the loyalty
 * card accounts and balances
 * 
 * @author Jack
 *
 */
public final class Bank {
	
	@SuppressWarnings("serial")
	private static final HashMap<Integer, BigDecimal> registeredAccounts = new HashMap<Integer, BigDecimal>(){{
		put(123456789, BigDecimal.valueOf(10));
		put(987654321, BigDecimal.valueOf(1));
		/*
		 * https://www.baeldung.com/java-initialize-hashmap
		 */
	}};
	
	
	public static boolean validateCard(LoyaltyCard card) {
		return registeredAccounts.containsKey(card.getCardNumber());
	}
	
	public static BigDecimal getAccountBalance(int accountNumber) {
		return registeredAccounts.get(accountNumber);
	}
	
	public static boolean chargeAccount(int accountNumber, BigDecimal amount) {
		
		if(registeredAccounts.get(accountNumber).compareTo(amount) >= 0) { // where there is enough balance
			registeredAccounts.get(accountNumber).subtract(amount);
			return true;
		}
		
		return false; // where there is not enough balance
	}

}
