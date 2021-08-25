package problemDomain;

/**
 * A person with personal information
 */
public class Person
{

	/**
	 * The name of the person
	 */
	private String name;
	/**
	 * The address of the person
	 */
	private String address;
	/**
	 * The city of the person
	 */
	private String city;
	/**
	 * The state of the person
	 */
	private String state;
	/**
	 * The zip of the address of the person
	 */
	private String zip;
	/**
	 * The phone number of the person
	 */
	private String phone;
	/**
	 * The social security number of the person
	 */
	private String sSN;
	/**
	 * The cashier role that the person has
	 */
	private Cashier cashier;

	/**
	 * The default constructor
	 */
	public Person()
	{
		this.name = "null";
		this.address = "null";
		this.city = "null";
		this.state = "null";
		this.zip = "null";
		this.phone = "0-000-000-0000";
		this.sSN = "000-00-0000";
		this.cashier = null;
	}

	/**
	 * The constructor that initializes the person's name, address, phone number, social security number, and cashier role
	 * @param name The person's name
	 * @param address The person's address
	 * @param phone The person's phone number
	 * @param sSN The person's social security number
	 */
	public Person(String name, String address, String city, String state, String zip, String phone, String sSN) 
	{
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.sSN = sSN;
		this.cashier = null;
	}

	/**
	 * Convert the class to a string
	 * @return The string representation of the class
	 */
	public String toString()
	{
		return name;
	}
	
	/**
	 * Get the person's name
	 * @return The person's name
	 */
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getSSN()
	{
		return sSN;
	}
	
	public void setSSN(String ssn)
	{
		this.sSN = ssn;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getZip()
	{
		return zip;
	}
	
	public void setZip(String zip)
	{
		this.zip = zip;
	}

}