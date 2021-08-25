package problemDomain;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A sales session in which the cashier can make sales with the customer
 */
public class Session
{

	/**
	 * The cashier running the session
	 */
	private Cashier cashier;
	/**
	 * The register taking the money
	 */
	private Register register;
	/**
	 * The sales that are occurring in the session
	 */
	private Collection<Sale> sales = new ArrayList<Sale>();
	/**
	 * The date and time that the session starts
	 */
	private LocalDateTime startDateTime;
	/**
	 * The date and time that the session ends
	 */
	private LocalDateTime endDateTime;

	/**
	 * The constructor that initializes the casher and the register in the session
	 * @param cashier The cashier running the session
	 * @param register The register that is taking the money
	 */
	public Session(Cashier cashier, Register register)
	{
		this.cashier = cashier;
		this.register = register;
		startDateTime = LocalDateTime.now();
	}

	/**
	 * Add a sale to session
	 * @param sale The sale to be added
	 */
	public void addSale(Sale sale)
	{
		sales.add(sale);
	}

	/**
	 * Remove a sale from the session
	 * @param sale The sale to be removed
	 */
	public void removeSale(Sale sale)
	{
		sales.remove(sale);
	}

	/**
	 * Calculate the difference between the actual amount of cash in the session and the counted cash
	 * @param cash The counted cash
	 * @return The difference between the actual amount of cash in the session and counted cash
	 */
	public BigDecimal calcCashCountDiff(BigDecimal cash)
	{
		return cash.subtract(register.getCashDrawer().getCashAmount());
	}

	/**
	 * Convert the class to a string
	 * @return The string that represents the class
	 */
	public String toString()
	{
		String output = "";
		BigDecimal total = new BigDecimal("0");
		output += "Session: Cashier: " + cashier.getName();
		output += " Register: " + register.getNumber();
		output += " Date and Time: " + startDateTime.toString();
		for (Sale s : sales)
		{
			total = total.add(s.calcTotal());
		}
		output += " Total: " + total.toString();
		output += "\n";
		//call the toString on all the sales
		for (Sale s : sales)
		{
			output += s.toString() + "\n";
		}
		
		return output;
	}
	
	public Cashier getCashier()
	{
		return cashier;
	}

	public Register getRegister()
	{
		return register;
	}
	
	public Collection<Sale> getSales()
	{
		return sales;
	}
	
	public LocalDate getStartDate()
	{
		return startDateTime.toLocalDate();
	}
}