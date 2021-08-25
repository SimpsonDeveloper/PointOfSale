package problemDomain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The price of an item
 */
public class Price implements Comparable<Price>
{

	/**
	 * The actual price
	 */
	protected BigDecimal price;
	/**
	 * The date that the price is effective for
	 */
	protected LocalDate effectiveDate;
	/**
	 * The item that the price applies to
	 */
	private Item item;

	/**
	 * The default constructor
	 */
	public Price()
	{
		price = new BigDecimal("0");
		setEffectiveDate(LocalDate.now());
	}

	/**
	 * The constructor that initializes the actual price and the effective date
	 * @param price The actual price
	 * @param effectiveDate The effective date that the price is active
	 */
	public Price(String price, String effectiveDate)
	{
		this.price = new BigDecimal(price);
		setEffectiveDate(effectiveDate);
	}

	/**
	 * Find out if the price is effective at a specified date
	 * @param date The date to be tested
	 * @return Is the price effective?
	 */
	public Boolean isEffective(LocalDate date)
	{
		if (effectiveDate.isBefore(date) || effectiveDate.isEqual(date))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Calculate the cost for a specified quantity of prices
	 * @param quantity The number of prices
	 * @return The total cost of all the prices
	 */
	public BigDecimal calcAmountForQty(int quantity)
	{
		// TODO - implement Price.calcAmountForQty
		throw new UnsupportedOperationException();
	}

	/**
	 * See which price is greater out of two prices
	 * @param price The other price
	 * @return 1: The price is greater than the other price
	 * 0: The prices are equal
	 * -1: The price is less than the other price
	 */
	public int compareTo(Price price)
	{
		return this.effectiveDate.compareTo(price.effectiveDate);
	}

	/**
	 * Convert the class to a string
	 * @return The string that represents the class
	 */
	public String toString()
	{
		return price.toString();// + " " + effectiveDate.toString();
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public void setEffectiveDate(String effectiveDate)
	{
		//filter
		String[] token = effectiveDate.split("/");
		String filteredDate;
		for (int i = 0; i <= 1; i++)
		{
			if (token[i].length() == 1)
			{
				token[i] = "0" + token[i];
			}
		}
		filteredDate = token[0] + "/" + token[1] + "/" + token[2];
		//assign
		this.effectiveDate = LocalDate.parse(filteredDate, DateTimeFormatter.ofPattern("MM/dd/yy"));
	}
	
	public void setEffectiveDate(LocalDate effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}
	
	public LocalDate getEffectiveDate()
	{
		return effectiveDate;
	}
	
	public String getDateString()
	{
		String[] token = effectiveDate.toString().split("-");
		String filteredDate;
		//handle the default LocalDate.toString() format
		filteredDate = token[1] + "/" + token[2] + "/" + token[0].substring(2, 4);
		return filteredDate;
	}
}