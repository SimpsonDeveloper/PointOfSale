package problemDomain;

import java.util.*;

/**
 * Someone who makes sales on the cash register
 */
public class Cashier
{

	/**
	 * The number that identifies the cashier
	 */
	private String number;
	/**
	 * The actual person that acts as the cashier
	 */
	private Person person;
	/**
	 * The sessions that the cashier is a part of
	 */
	private Collection<Session> sessions;
	/**
	 * The cashier's password
	 */
	private String password;

	/**
	 * The default constructor
	 */
	public Cashier()
	{
		this.number = "0";
		this.person = new Person();
		this.password = "null";
	}

	/**
	 * The constructor that initializes the cashier's number, person, and password
	 * @param number The number that identifies the cashier
	 * @param person The actual person that acts as the cashier
	 * @param password The cashier's password
	 */
	public Cashier(String number, Person person, String password)
	{
		this.number = number;
		this.person = person;
		this.password = password;
	}

	/**
	 * Add a session to the cashier
	 * @param session The session to be added
	 */
	public void addSession(Session session)
	{
		// TODO - implement Cashier.addSession
		throw new UnsupportedOperationException();
	}

	/**
	 * Remove a session from the cashier
	 * @param session The session to be removed
	 */
	public void removeSession(Session session)
	{
		// TODO - implement Cashier.removeSession
		throw new UnsupportedOperationException();
	}

	/**
	 * Check if the entered password match the cashier's password
	 * @param password The entered password
	 * @return Does the entered password match the cashier's password?
	 */
	public Boolean isAuthorized(String password)
	{
		// TODO - implement Cashier.isAuthorized
		throw new UnsupportedOperationException();
	}

	/**
	 * Convert the class to a string
	 * @return The string representation of the class
	 */
	public String toString()
	{
		return number + " " + person.toString();
	}
	
	/**
	 * Get the cashier's number
	 * @return The cashier's number
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
	 * Get the cashier's person's name
	 * @return The cashier's person's name
	 */
	public String getName()
	{
		return person.getName();
	}
	
	public Person getPerson()
	{
		return person;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}

}