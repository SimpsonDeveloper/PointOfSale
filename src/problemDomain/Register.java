package problemDomain;

import java.util.ArrayList;

/**
 * A register that the cashiers can put money in
 */
public class Register
{

	/**
	 * The number that the register is identified by
	 */
	private String number;
	/**
	 * The cash drawer inside the register
	 */
	private CashDrawer cashDrawer;
	
	private ArrayList<Session> sessions = new ArrayList<Session>();

	/**
	 * The default constructor
	 */
	public Register()
	{
		number = "null";
		cashDrawer = new CashDrawer();
	}

	/**
	 * The constructor that initializes the register's number
	 * @param number The number of the register
	 */
	public Register(String number)
	{
		this.number = number;
		cashDrawer = new CashDrawer();
	}

	/**
	 * Convert the class to a string
	 * @return The string that represents the class
	 */
	public String toString()
	{
		return number;// + " Cash amount: " + cashDrawer.toString() + " dollars";
	}
	
	/**
	 * Get the register's number
	 * @return The register's number
	 */
	public String getNumber()
	{
		return number;
	}
	
	/**
	 * Set the cash drawer
	 * @param cashDrawer The CashDrawer that the cash drawer is being set to
	 */
	public void setCashDrawer(CashDrawer cashDrawer)
	{
		this.cashDrawer = cashDrawer;
	}
	
	public CashDrawer getCashDrawer()
	{
		return cashDrawer;
	}
	
	public ArrayList getSessions()
	{
		return sessions;
	}

	public void addSession(Session session)
	{
		sessions.add(session);
	}
	
	public void removeSession(Session session)
	{
		sessions.remove(session);
	}
	
	public Boolean isUsed()
	{
		Boolean result = false;
		if (!sessions.isEmpty())
		{
			result = true;
		}
		return result;
	}
	
	public void setNumber(String number)
	{
		this.number = number;
	}
}