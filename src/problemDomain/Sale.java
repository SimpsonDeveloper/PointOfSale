package problemDomain;

import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * A sale in the store. It calculates all of the information pertaining to money
 * exchange
 */
public class Sale {

	/**
	 * The payments made in the sale
	 */
	private Collection<Payment> payments = new ArrayList<Payment>();
	/**
	 * The sale line items being sold in the sale
	 */
	private Collection<SaleLineItem> saleLineItems = new ArrayList<SaleLineItem>();
	/**
	 * The date and time of the sale
	 */
	private LocalDateTime dateTime;
	/**
	 * Is the sale tax free?
	 */
	private Boolean taxFree;

	/**
	 * The default constructor
	 */
	public Sale() {
		taxFree = false;
		dateTime = LocalDateTime.now();
	}

	/**
	 * The constructor that initializes taxFree
	 * 
	 * @param taxFree Is the sale tax free?
	 */
	public Sale(String taxFree) {
		if (taxFree.equalsIgnoreCase("y")) {
			this.taxFree = true;
		} 
		else 
		{
			this.taxFree = false;
		}
		dateTime = LocalDateTime.now();
	}

	/**
	 * Add a payment to the sale
	 * 
	 * @param payment The payment to be added
	 */
	public void addPayment(Payment payment) {
		payments.add(payment);
	}

	/**
	 * Remove a payment from a sale
	 * 
	 * @param payment The payment to be removed
	 */
	public void removePayment(Payment payment) {
		payments.remove(payment);
	}

	/**
	 * Add a sale line item to the sale
	 * 
	 * @param sli The sale line item to be added
	 */
	public void addSaleLineItem(SaleLineItem sli) {
		saleLineItems.add(sli);
	}

	/**
	 * Remove a sale line item from the sale
	 * 
	 * @param sli The sale line item to be removed
	 */
	public void removeSaleLineItem(SaleLineItem sli) {
		saleLineItems.remove(sli);
	}

	/**
	 * Calculate the total of the sale after taxes.
	 * 
	 * @return The total of the sale after taxes
	 */
	public BigDecimal calcTotal() {
		return calcSubTotal().add(calcTax());
	}

	/**
	 * Calculate the total of the sale before taxes
	 * 
	 * @return The total of the sale before taxes
	 */
	public BigDecimal calcSubTotal() {
		// loop through all the saleLineItems and add up their prices
		BigDecimal result = new BigDecimal("0");
		for (SaleLineItem sli : saleLineItems) {
			result = result.add(sli.calcSubTotal());
		}

		return result;
	}

	/**
	 * Calculate the tax on the sale
	 * 
	 * @return The tax amount on the sale
	 */
	public BigDecimal calcTax() {
		MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
		BigDecimal result = new BigDecimal("0");
		if (!taxFree)
		{
			for (SaleLineItem sli : saleLineItems)
			{
				result = result.add(sli.calcTax());
			}
			result = result.round(mc);
		}

		return result;
	}

	/**
	 * Get the total amounts of the payments
	 * 
	 * @return The total amounts of the payments
	 */
	public BigDecimal getTotalPayments() {
		BigDecimal paymentTotal = new BigDecimal("0");
		for (Payment p : payments)
		{
			//accumulate payment total
			paymentTotal = paymentTotal.add(p.getAmount());
		}
		return paymentTotal;
	}

	/**
	 * Tell whether the total payments enough to pay for the whole sale
	 * 
	 * @return Are the total payments enough to pay for the whole sale?
	 */
	public Boolean isPaymentEnough() {
		boolean result = true;
		if (calcTotal().doubleValue() > getTotalPayments().doubleValue())
		{
			result = false;
		}
		return result;
	}

	/**
	 * Calculate an amount that is going to be kept
	 * 
	 * @param amtTendered The amount tendered to a sale
	 */
	public BigDecimal calcAmount(BigDecimal amtTendered) {
		//David North notes
		//1.Get sum of amounts of previous payments
		//2.Calculate how much I still need: total - sum of previous payments' amtTendered
		//3.Calculate how much do I keep:
		//	If (how much I need > amtTendered)
		//		Amount I keep = amtTendered
		//	Else
		//		Amount I keep = amtTendered - how much I need (that calculation is the change, the way I see it)
		
		//What I figure
		//If (how much I need > amtTendered)
		//	Amount I keep = amt Tendered
		//else
		//	keep = total of sale - sum of previous payments' amount kept
		BigDecimal amount = calcTotal().subtract(getTotalPayments());
		if (amount.compareTo(amtTendered) > 0)
		{
			amount = amtTendered;
		}
		return amount;
	}

	/**
	 * Calculate the change to give the customer after the customer has paid
	 * 
	 * @return The change to give the customer
	 */
	public BigDecimal calcChange() {
		BigDecimal changeTotal = new BigDecimal("0");
		for (Payment p : payments)
		{
			//accumulate change total
			changeTotal = changeTotal.add(p.calcChange());
		}
		return changeTotal;
	}

	/**
	 * Get the amount that the customer paid
	 * 
	 * @return The amount that the customer paid
	 */
	public BigDecimal calcAmtTendered() {
		BigDecimal paymentTotal = new BigDecimal("0");
		for (Payment p : payments)
		{
			//accumulate payment total
			paymentTotal = paymentTotal.add(p.getAmtTendered());
		}
		return paymentTotal;
	}

	/**
	 * Convert the class to a string
	 * @return The string representation of the class
	 */
	public String toString() {
		String output = "";
		output += "Sale : Subtotal = ";
		output += calcSubTotal().toString();
		output += " Tax = ";
		output += calcTax().toString();
		output += " Total = ";
		output += calcTotal().toString();
		output += " Payment = " + calcAmtTendered();
		output += " Change = " + calcChange();
		output += "\n";
		// print out the items
		for (SaleLineItem sli : saleLineItems) {
			output += " " + sli.toString() + "\n";
		}

		return output;
	}
	
	/**
	 * Get the date of the sale
	 * @return The date of the sale
	 */
	public LocalDate getDate()
	{
		return dateTime.toLocalDate();
	}
	
	public LocalDateTime getDateTime()
	{
		return dateTime;
	}
	
	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}
	
	public Collection<SaleLineItem> getSaleLineItems()
	{
		return saleLineItems;
	}
	
	public void clearSaleLineItems()
	{
		saleLineItems.clear();
	}
	
	public Collection<Payment> getPayments()
	{
		return payments;
	}
	
	public void clearPayments()
	{
		payments.clear();
	}
	
	public void setTaxFree(boolean taxFree)
	{
		this.taxFree = taxFree;
	}

}