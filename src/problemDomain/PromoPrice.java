package problemDomain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;

/**
 * A promotional price only available for a short time
 */
public class PromoPrice extends Price
{

	/**
	 * The date that the promotional price ends
	 */
	private LocalDate endDate;
	//private String endDateString;

	/**
	 * The default constructor
	 */
	public PromoPrice()
	{}

	/**
	 * The constructor that initializes the price, effective date, and the end date of the price
	 * @param price The actual price
	 * @param effectiveDate The date the the price is effective
	 * @param endDate The date that the price is no longer effective
	 */
	public PromoPrice(String price, String effectiveDate, String endDate)
	{
		this.price = new BigDecimal(price);
		/*
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
		this.effectiveDate = LocalDate.parse(filteredDate, DateTimeFormatter.ofPattern("MM/dd/yy"));*/
		setEffectiveDate(effectiveDate);
		
		/*
		//filter
		token = endDate.split("/");
		for (int i = 0; i <= 1; i++)
		{
			if (token[i].length() == 1)
			{
				token[i] = "0" + token[i];
			}
		}
		filteredDate = token[0] + "/" + token[1] + "/" + token[2];
		//assign
		this.endDate = LocalDate.parse(filteredDate, DateTimeFormatter.ofPattern("MM/dd/yy"));*/
		setEndDate(endDate);
	}

	/**
	 * Tell whether the promo price is currently effective at a specified date
	 * @param date The date to test for
	 * @return Is the price currently effective?
	 */
	public Boolean isEffective(LocalDate date)
	{
		if ((this.effectiveDate.isBefore(date) && this.endDate.isAfter(date)) || this.effectiveDate.isEqual(date))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Compare the promo price to another price
	 * @param price The other price to be compared to
	 * @return 1: The promo price is greater than the other price
	 * 0: The prices are equal
	 * -1: The promo price is less than the other price
	 */
	public int compareTo(Price price)
	{
		return this.effectiveDate.compareTo(price.effectiveDate);
	}

	/**
	 * Convert the class to a string
	 * @return The string representation of the class
	 */
	public String toString()
	{
		return price.toString();// + " " + effectiveDate.toString() + " - " + endDate.toString();
	}
	
	public void setEndDate(String endDate)
	{
		//filter
		String[] token = endDate.split("/");
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
		this.endDate = LocalDate.parse(filteredDate, DateTimeFormatter.ofPattern("MM/dd/yy"));
	}
	
	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate;
	}
	
	public LocalDate getEndDate()
	{
		return endDate;
	}
	
	public String getEndDateString()
	{
		String[] token = endDate.toString().split("-");
		String filteredDate;
		//handle the default LocalDate.toString() format
		filteredDate = token[1] + "/" + token[2] + "/" + token[0].substring(2, 4);
		return filteredDate;
	}

}