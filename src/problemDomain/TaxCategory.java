package problemDomain;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TaxCategory
{

	/**
	 * The tax category's name
	 */
	private String category;
	/**
	 * The tax rates in the tax category
	 */
	private TreeSet<TaxRate> taxRates = new TreeSet<TaxRate>();

	/**
	 * The default constructor
	 */
	public TaxCategory()
	{
		category = "null";
	}

	/**
	 * The constructor that initializes the tax category's name
	 * @param category The category's name
	 */
	public TaxCategory(String category)
	{
		this.category = category;
	}

	/**
	 * Get a tax rate for a particular date
	 * @param date The date for the tax rate
	 * @return The tax rate for the specified date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date)
	{
		BigDecimal rate = new BigDecimal("0");
		for (TaxRate t : taxRates)
		{
			if (t.isEffective(date))
			rate = t.getRate();
		}
		return rate;
	}

	/**
	 * Add a tax rate
	 * @param taxRate The tax rate to be added
	 */
	public void addTaxRate(TaxRate taxRate)
	{
		taxRates.add(taxRate);
	}

	/**
	 * Remove a tax rate
	 * @param taxRate The tax rate to be removed
	 */
	public void removeTaxRate(TaxRate taxRate)
	{
		taxRates.remove(taxRate);
	}
	
	public void clearTaxRates()
	{
		taxRates.clear();
	}

	/**
	 * Convert the class to a string
	 * @return The string that represents the class
	 */
	public String toString()
	{
		return category;
	}
	
	/**
	 * Get the tax category name
	 * @return The tax category name
	 */
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public Collection<TaxRate> getTaxRates()
	{
		return taxRates;
	}

}