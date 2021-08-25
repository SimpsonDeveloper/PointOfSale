package problemDomain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A credit card payment
 */
public class Credit extends AuthorizedPayment
{

	/**
	 * The type of the card. (Visa, Mastercard, etc.)
	 */
	private String cardType;
	/**
	 * The account number for the card
	 */
	private String acctNumber;
	/**
	 * The expire date of the card
	 */
	private LocalDate expireDate;

	/**
	 * The default constructor
	 */
	public Credit()
	{
		expireDate = LocalDate.now();
	}

	/**
	 * The constructor that takes the card type, account number, and expiration date of the card
	 * @param cardType The type of the card (e.g. VISA, Mastercard, etc)
	 * @param acctNumber The account number of the account that the card is bound to
	 * @param expireDate The expiration date of the card
	 */
	public Credit(String cardType, String acctNumber, String expireDate)
	{
		this.cardType = cardType;
		this.acctNumber = acctNumber;
		setExpireDate(expireDate);
		}

	/**
	 * Tell whether the card is authorized
	 * @return Is the card authorized?
	 */
	public Boolean isAuthorized()
	{
		return true;
	}

	/**
	 * Calculate the change to give back
	 * @return The change to give back
	 */
	public BigDecimal calcChange()
	{
		return amtTendered.subtract(amount);
	}

	/**
	 * Convert the class to a string
	 * @return The string representation of the class
	 */
	public String toString()
	{
		// TODO - implement Credit.toString
		throw new UnsupportedOperationException();
	}
	
	public void setExpireDate(String expireDate)
	{
		String[] token = expireDate.split("/");
		String filteredDate;
		if (token.length == 3 && token[2].length() == 2)
		{
			//filter
			for (int i = 0; i <= 1; i++)
			{
				if (token[i].length() == 1)
				{
					token[i] = "0" + token[i];
				}
			}
			filteredDate = token[0] + "/" + token[1] + "/" + token[2];
			//assign
			this.expireDate = LocalDate.parse(filteredDate, DateTimeFormatter.ofPattern("MM/dd/yy"));
		}
		else if (token.length == 2 && token[1].length() == 2)
		{
			if (token[0].length() == 1)
			{
				token[0] = "0" + token[0];
			}
			filteredDate = token[0] + "/" + LocalDate.now().getDayOfMonth() + "/" + token[1];
			this.expireDate = LocalDate.parse(filteredDate, DateTimeFormatter.ofPattern("MM/dd/yy"));
		}
		else if (token.length == 2 && token[1].length() == 4)
		{
			if (token[0].length() == 1)
			{
				token[0] = "0" + token[0];
			}
			//filteredDate = token[0] + "/" + token[1];
			filteredDate = token[0] + "/" + LocalDate.now().getDayOfMonth() + "/" + token[1];
			this.expireDate = LocalDate.parse(filteredDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		else
		{
			//filter
			for (int i = 0; i <= 1; i++)
			{
				if (token[i].length() == 1)
				{
					token[i] = "0" + token[i];
				}
			}
			filteredDate = token[0] + "/" + token[1] + "/" + token[2];
			//assign
			this.expireDate = LocalDate.parse(filteredDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
	}

}