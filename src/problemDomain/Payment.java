package problemDomain;

import java.math.BigDecimal;
/**
 * The payment for a sale
 */
public abstract class Payment
{

	/**
	 * The amount that the payment has
	 */
	protected BigDecimal amount;
	/**
	 * The cash amount tendered in the payment
	 */
	protected BigDecimal amtTendered;

	/**
	 * Calculate the change to give back
	 * @return The change to give back
	 */
	public BigDecimal calcChange()
	{
		return amtTendered.subtract(amount);
	}

	/**
	 * Tell whether to count the payment as cash
	 * @return Count the payment as cash?
	 */
	public abstract Boolean countAsCash();
	
	public BigDecimal getAmtTendered()
	{
		return amtTendered;
	}
	
	public void setAmtTendered(BigDecimal amtTendered)
	{
		this.amtTendered = amtTendered;
	}
	
	public BigDecimal getAmount()
	{
		return amount;
	}
	
	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}
}