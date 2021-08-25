package problemDomain;

import java.math.BigDecimal;
/**
 * A cash drawer that holds money
 */
public class CashDrawer
{

	/**
	 * The cash in the drawer
	 */
	private BigDecimal cashAmount;
	/**
	 * The position of the drawer
	 */
	private int position;

	/**
	 * The default constructor
	 */
	public CashDrawer()
	{
		cashAmount = new BigDecimal("0");
	}
	
	
	public CashDrawer(String cashAmount)
	{
		this.cashAmount = new BigDecimal(cashAmount);
	}
	
	/**
	 * Add cash to the drawer
	 * @param cash The amount of cash to add
	 */
	public void addCash(BigDecimal cash)
	{
		this.cashAmount = this.cashAmount.add(cash);
	}
	
	public void subtractCash(BigDecimal cash)
	{
		this.cashAmount = this.cashAmount.subtract(cash);
	}

	/**
	 * Remove cash from the drawer
	 * @param cash The amount of cash to remove
	 */
	public void removeCash(BigDecimal cash)
	{
		this.cashAmount = this.cashAmount.subtract(cash);
	}

	/**
	 * Convert the class to a string
	 * @return The string representation of the class
	 */
	public String toString() 
	{
		return cashAmount.toString();
	}
	
	public BigDecimal getCashAmount()
	{
		return this.cashAmount;
	}
	
	public void setCashAmount(String cashAmount)
	{
		this.cashAmount = new BigDecimal(cashAmount);
	}

}