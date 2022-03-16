package demoClasses;

import java.math.BigDecimal;
import java.util.HashMap;

import externalElements.Bank;
import paymentMethods.LoyaltyCard;

/**
 * A class for demonstration purposes to
 * mimic a bank that controls the loyalty
 * card accounts and balances
 * 
 * @author Jack
 *
 */
public class DemoBank implements Bank {
	
	private HashMap<Integer, BigDecimal> registeredAccounts = new HashMap<Integer, BigDecimal>();
	
	public DemoBank() {
		registeredAccounts.put(123456789, BigDecimal.valueOf(10));
		registeredAccounts.put(987654321, BigDecimal.valueOf(1.5));
	}
	
	
	public boolean validateCard(LoyaltyCard card) {
		return registeredAccounts.containsKey(card.getCardNumber()); // see if the account number is stored
	}
	
	public BigDecimal getAccountBalance(int accountNumber) {
		return registeredAccounts.get(accountNumber);
	}
	
	public boolean chargeAccount(int accountNumber, BigDecimal amount) {
		
		if(registeredAccounts.get(accountNumber).compareTo(amount) >= 0) {
			// where there is enough balance
			registeredAccounts.replace(accountNumber, registeredAccounts.get(accountNumber).subtract(amount));
			return true;
		}
		
		// where there is not enough balance
		return false;
	}

}
