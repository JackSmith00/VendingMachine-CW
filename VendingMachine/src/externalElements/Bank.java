package externalElements;

import java.math.BigDecimal;

import paymentMethods.LoyaltyCard;

/**
 * Functionality expected from a linked bank
 * 
 * @author Jack
 *
 */
public interface Bank {

	/**
	 * Validates if a scanned card is linked to
	 * a registered account
	 * 
	 * @param card the card to check for a linked account
	 * @return true when an account is found and any security
	 * checks are passed, false otherwise
	 */
	public boolean validateCard(LoyaltyCard card);
	
	/**
	 * Checks the balance of a provided account
	 * 
	 * @param accountNumber the account to check the balance of
	 * @return the balance held in the account
	 */
	public BigDecimal getAccountBalance(int accountNumber);
	
	/**
	 * Attempts to charge the specified account by a given amount
	 * 
	 * @param accountNumber the account to debit
	 * @param amount the amount to charge the account
	 * @return a boolean indication of the outcome,
	 * true if charged successfully, false otherwise
	 */
	public boolean chargeAccount(int accountNumber, BigDecimal amount);
}
