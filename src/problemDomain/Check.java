package problemDomain;

import java.math.BigDecimal;

/**
 * A check to pay with
 */
public class Check extends AuthorizedPayment
{

	/**
	 * The routing number of the account of the check
	 */
	private String routingNumber;
	/**
	 * The account number of the account of the check
	 */
	private String accountNumber;
	/**
	 * The check number of the check
	 */
	private String checkNumber;

	/**
	 * The default constructor
	 */
	public Check()
	{
	}

	/**
	 * The constructor that initializes the amount, account number, routing number, and check number of the check
	 * @param amount The amount of the check
	 * @param accountNumber The account number of the account that the money is coming from
	 * @param routingNumber The routing number of the account that the money is coming from
	 * @param checkNumber The check number of the check
	 */
	public Check(String amount, String accountNumber, String routingNumber, String checkNumber)
	{
		this.amount = new BigDecimal(amount);
		this.accountNumber = accountNumber;
		this.routingNumber = routingNumber;
		this.checkNumber = checkNumber;
	}

	/**
	 * Tell whether the check is authorized
	 * @return Is the check authorized?
	 */
	public Boolean isAuthorized()
	{
		return true;
	}

	/**
	 * Calculate the amount of change to give back
	 * @return the amount of change to give back
	 */
	public BigDecimal calcChange()
	{
		return amtTendered.subtract(amount);
	}
	
	/**
	 * Convert the class to a string
	 * @return The string representation of the class
	 */
	public String toString()
	{
		// TODO - implement Check.toString
		throw new UnsupportedOperationException();
	}

}