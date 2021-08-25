package problemDomain;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * An item in the store. It is not directly sold. Instead it is passed as a SaleLineItem to be sold.
 */
public class Item
{
	/**
	 * The number that the item is identified by
	 */
	private String number;
	/**
	 * The description of the item
	 */
	private String description;
	/**
	 * The items that are being sold
	 */
	private Collection<SaleLineItem> saleLineItems;
	/**
	 * The UPC that the item is identified by
	 */
	private TreeMap<String, UPC> uPCS = new TreeMap<String, UPC>();
	/**
	 * How much the item costs
	 */
	private TreeSet<Price> prices = new TreeSet<Price>();
	/**
	 * The tax category that the item is in
	 */
	private TaxCategory taxCategory;

	/**
	 * The default constructor
	 */
	public Item()
	{
		number = "0";
		description = "No description";
	}

	/**
	 * A constructor that initializes the item number and the item description
	 * @param number The number that identifies the item
	 * @param description The description of the item
	 * @param taxCategory The taxCategory for the item
	 */
	public Item(String number, String description)
	{
		this.number = number;
		this.description = description;
	}

	/**
	 * Add a new cost for the item
	 * @param price The price to be added
	 */
	public void addPrice(Price price)
	{
		prices.add(price);
	}

	/**
	 * Remove a price from the item
	 * @param price The price to be removed
	 */
	public void removePrice(Price price)
	{
		prices.remove(price);
	}
	
	public void clearPrices()
	{
		prices.clear();
	}

	/**
	 * Add a new UPC that the item is identified by
	 * @param upc The UPC to be added
	 */
	public void addUPC(UPC upc)
	{
		uPCS.put(upc.getUPC(), upc);
	}

	/**
	 * Remove a UPC from the item
	 * @param upc The UPC to be removed
	 */
	public void removeUPC(UPC upc)
	{
		uPCS.remove(upc.getUPC(), upc);
	}
	
	public void clearUPCs()
	{
		uPCS.clear();
	}

	/**
	 * The the price of the item at a particular date
	 * @param date The date that the price was active
	 * @return The price at the specified date
	 */
	public BigDecimal getPriceForDate(LocalDate date)
	{
		Iterator<Price> i = prices.iterator();
		Price latestEffective = null;
		Price currentDate = null;
		//loops through all the items until an effective date is found
		while (i.hasNext())
		{
			currentDate = i.next();
			if (currentDate.isEffective(date))
			{
				latestEffective = currentDate;
			}
		}
		
		BigDecimal p = latestEffective.getPrice();
		
		return p;
	}
	
	public Price getPriceObjectForDate(LocalDate date)
	{
		Iterator<Price> i = prices.iterator();
		Price latestEffective = null;
		Price currentDate = null;
		//loops through all the items until an effective date is found
		while (i.hasNext())
		{
			currentDate = i.next();
			if (currentDate.isEffective(date))
			{
				latestEffective = currentDate;
			}
		}
		
		return latestEffective;
	}

	/**
	 * Get the tax rate for the item at a particular date
	 * @param date The date that the tax rate was active
	 * @return The tax rate a the specified date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date)
	{
		return taxCategory.getTaxRateForDate(date);
	}

	/**
	 * Get the total cost of a specified number of items at a particular date
	 * @param date The date that a price was active
	 * @param quantity The number of items' prices to accumulate
	 * @return The total amount of all the items at the specified date
	 */
	public BigDecimal calcAmountForDateQty(LocalDate date, int quantity)
	{
		// TODO - implement Item.calcAmountForDateQty
		throw new UnsupportedOperationException();
	}

	/**
	 * Convert the class to a string
	 * @return The string that represents the class
	 */
	public String toString()
	{
		String output = "";
		
		//print out the item's number
		output += number + " ";
		
		//print out the item's description
		output += description + " ";
		
		//print out the item's price (get the currently effective price)
		
		output += "Price : " + getPriceForDate(LocalDate.now()).toString() + " ";
		
		//call TaxCategory's getTaxRateForDate
		//print the BigDecimal returned
		output += "Tax Rate: " + taxCategory.getTaxRateForDate(LocalDate.now()).toString() + " ";
		
		//print out the item's upc
		output += "UPC: ";
		for (UPC upc : uPCS.values())
		{
			output += upc.toString() + " ";
		}
		
		//print out the item's tax category
		
		output += "Tax Category: " + taxCategory.toString();
		
		return output;
	}
	
	/**
	 * Get the item's number
	 * @return The item's number
	 */
	public String getNumber()
	{
		return number;
	}
	
	public void setNumber(String number)
	{
		this.number = number;
	}
	
	/**
	 * Get the item's description
	 * @return The item's description
	 */
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public TaxCategory getTaxCategory()
	{
		return taxCategory;
	}
	
	public void setTaxCategory(TaxCategory taxCategory)
	{
		this.taxCategory = taxCategory;
	}
	
	public Collection<UPC> getUPCs()
	{
		return uPCS.values();
	}
	
	public Collection<Price> getPrices()
	{
		return prices;
	}
}