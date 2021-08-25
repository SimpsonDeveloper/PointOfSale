package problemDomain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * An item that is used in a sale
 */
public class SaleLineItem
{

	/**
	 * The actual item
	 */
	private Item item;
	/**
	 * The amount of the actual item
	 */
	private int quantity;
	/**
	 * The sale
	 */
	private Sale sale;

	/**
	 * The default constructor
	 */
	public SaleLineItem()
	{
		item = null;
		quantity = 0;
		this.sale = new Sale();
	}

	/**
	 * The constructor that initializes the item number and quantity
	 * @param item The item's identifying number
	 * @param quantity The number of items
	 */
	public SaleLineItem(Item item, String quantity)
	{
		this.item = item;
		this.quantity = Integer.parseInt(quantity);
		this.sale = new Sale();
	}

	/**
	 * Calculate the total cost of all the items in the SaleLineItem
	 * @return The total cost of all the items in the SaleLineItem
	 */
	public BigDecimal calcSubTotal()
	{
		BigDecimal result = new BigDecimal("0");
		for (int i = 0; i < quantity; i++)
		{
			result = result.add(item.getPriceForDate(sale.getDate()));
		}
		
		return result;
	}

	/**
	 * Calculate the tax of the item
	 * @return The tax of the item
	 */
	public BigDecimal calcTax()
	{
		BigDecimal result = new BigDecimal(calcSubTotal().toString());
		//make the result equal the subTotal * the tax rate
		result = result.multiply(item.getTaxRateForDate(LocalDate.now()));
		
		return result;
	}

	/**
	 * Convert the class to a string
	 * @return The string representation of the class
	 */
	public String toString()
	{
		MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
		String output = "";
		
		output += item.getNumber() + " ";
		output += item.getDescription() + " ";
		output += Integer.toString(quantity) + "@";
		output += "$" + item.getPriceObjectForDate(sale.getDate()).toString() + " ";
		//output += "$" + calcSubTotal().toString() + " ";
		
		return output;
	}
	
	/**
	 * Set the sale to a specified Sale
	 * @param sale The specified Sale
	 */
	public void setSale(Sale sale)
	{
		this.sale = sale;
	}
	
	public boolean equals(SaleLineItem sli)
	{
		boolean result = false;
		if (this.item.getNumber().equals(sli.item.getNumber()))
		{
			result = true;
		}
		return result;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public Item getItem()
	{
		return item;
	}
	
}