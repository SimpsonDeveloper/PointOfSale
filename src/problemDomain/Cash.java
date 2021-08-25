package problemDomain;

import java.math.BigDecimal;
/**
 * A cash payment
 */
public class Cash extends Payment
{

	/**
	 * The default constructor
	 */
	public Cash()
	{
		amount = new BigDecimal("0");
		amtTendered = new BigDecimal("0");
	}

	/**
	 * The constructor that takes the payment amount and the amount tendered
	 * @param amount The amount that the payment has
	 * @param amtTendered The amount of cash tendered
	 */
	public Cash(String amount, String amtTendered)
	{
		this.amount = new BigDecimal(amount);
		this.amtTendered = new BigDecimal(amtTendered);
	}

	/**
	 * Calculate the amount of change to give back
	 * @return The amount of change to give back
	 */
	public BigDecimal calcChange()
	{
		return amtTendered.subtract(amount);
	}

	/**
	 * Tell whether the payment is to be counted as cash
	 * @return Is the payment to be counted as cash?
	 */
	public Boolean countAsCash()
	{
		return true;
	}

	/**
	 * Convert the class to a string
	 * @return The string representation of the class
	 */
	public String toString()
	{
		// TODO - implement Cash.toString
		throw new UnsupportedOperationException();
	}

}