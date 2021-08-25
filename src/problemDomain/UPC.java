package problemDomain;

/**
 * A UPC that identifies a specific item
 */
public class UPC
{

	/**
	 * The string of characters that the UPC is made up of
	 */
	private String uPC;
	/**
	 * The item that the UPC is attributed to
	 */
	private Item item;

	/**
	 * The default constructor
	 */
	public UPC()
	{
		uPC = "";
	}

	/**
	 * The constructor that initializes the string of characters for the UPC
	 * @param upc The string of characters for the UPC
	 */
	public UPC(String upc)
	{
		uPC = upc;
	}

	/**
	 * Convert the class to a string
	 * @return The string representation of the class
	 */
	public String toString()
	{
		return uPC;
	}
	/**
	 * Get the actual number of the UPC
	 * @return The actual number of the UPC
	 */
	public String getUPC()
	{
		return uPC;
	}
	
	public void setUPC(String upc)
	{
		this.uPC = upc;
	}

}