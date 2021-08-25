package problemDomain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaxRate implements Comparable<TaxRate>
{

	/**
	 * The actual tax rate
	 */
	private BigDecimal taxRate;
	/**
	 * The effective date that the tax rate takes place
	 */
	private LocalDate effectiveDate;
	
	/**
	 * The default constructor
	 */
	public TaxRate()
	{
		this.taxRate = new BigDecimal("0");
		this.effectiveDate = LocalDate.now();
		setDate(LocalDate.now());
	}
	
	/**
	 * A constructor that initializes the effective date and the rate of the tax rate
	 * @param effectiveDate The effective date of the tax rate
	 * @param rate The rate of the tax rate
	 */
	public TaxRate(String effectiveDate, BigDecimal rate)
	{
		setDate(effectiveDate);
		this.taxRate = rate;
	}

	/**
	 * Tells whether the tax rate is effective a certain date
	 * @param date The date to query about
	 * @return True: The tax rate is effective
	 * False: The tax rate is not effective
	 */
	public boolean isEffective(LocalDate date)
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
	 * Get the tax rate
	 * @return The tax rate
	 */
	public BigDecimal getRate()
	{
		return taxRate;
	}
	
	public void setRate(String rate)
	{
		this.taxRate = new BigDecimal(rate);
	}
	
	public void setRate(BigDecimal rate)
	{
		this.taxRate = rate;
	}

	/**
	 * Compare to another tax rate
	 * @param taxRate The other tax rate to compare to
	 * @return 1: The price is greater than the other price
	 * 0: The prices are equal
	 * -1: The price is less than the other price
	 */
	public int compareTo(TaxRate taxRate)
	{
		return this.effectiveDate.compareTo(taxRate.effectiveDate);
	}

	/**
	 * Convert the class to a string
	 * @return The string that represents the class
	 */
	public String toString()
	{
		return taxRate.toString();
	}
	
	public LocalDate getDate()
	{
		return this.effectiveDate;
	}
	
	public void setDate(String effectiveDate)
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
	
	public void setDate(LocalDate effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	public String getDateString()
	{
		String[] token = effectiveDate.toString().split("-");
		String filteredDate;
		filteredDate = token[1] + "/" + token[2] + "/" + token[0].substring(2, 4);
		return filteredDate;
	}
}