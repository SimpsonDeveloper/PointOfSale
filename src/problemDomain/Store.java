package problemDomain;

import java.math.BigDecimal;
import java.util.*;
import dataManagement.DataManager;

//TODO: change the edit to use the setters for the passed object instead of removing and adding

/**
 * A store is a place where everything is ultimately stored. Cashiers work there.
 */
public class Store
{
	/**
	 * The data manager for the store
	 */
	private DataManager storeDM = new DataManager();
 
	/**
	 * The store number that identifies the store
	 */
	private String number;
	/**
	 * The name that identifies the store
	 */
	private String name;
	/**
	 * The tax categories for sales
	 */
	private TreeMap<String, TaxCategory> taxCategories = new TreeMap<String, TaxCategory>();
	/**
	 * The items that can be sold
	 */
	private TreeMap<String, Item> items = new TreeMap<String, Item>();
	/**
	 * The cashiers that work at the store
	 */
	private TreeMap<String, Cashier> cashiers = new TreeMap<String, Cashier>();
	/**
	 * The registers that are used in the store
	 */
	private TreeMap<String, Register> registers = new TreeMap<String, Register>();
	/**
	 * The sale sessions in the store
	 */
	private Collection<Session> sessions = new ArrayList<Session>();
	/**
	 * The identifying numbers for all items
	 */
	private TreeMap<String, UPC> upcs;

	/**
	 * Default constructor
	 */
	public Store()
	{
		number = "0";
		name = "null";
	}

	/**
	 * A constructor that initializes the name and number of the store.
	 * @param number The number that identifies the store
	 * @param name The name that identifies the store
	 */
	public Store(String number, String name) 
	{
		this.number = number;
		this.name = name;
	}
	
	/**
	 * Load data from a file
	 */
	public void loadData()
	{
		storeDM.readFile(this);
	}

	/**
	 * Add an item to the store
	 * @param item The item to be added
	 */
	public void addItem(Item item) 
	{
		this.items.put(item.getNumber(), item);
	}

	/**
	 * Remove an item from the store
	 * @param item The item to be removed
	 */
	public void removeItem(Item item)
	{
		items.remove(item.getNumber());
	}

	/**
	 * Add a UPC to the store
	 * @param upc The UPC to add
	 */
	public void addUPC(UPC upc)
	{
		// TODO - implement Store.addUPC
		throw new UnsupportedOperationException();
	}

	/**
	 * Remove a UPC from the store
	 * @param upc The UPC to remove
	 */
	public void removeUPC(UPC upc)
	{
		// TODO - implement Store.removeUPC
		throw new UnsupportedOperationException();
	}

	/**
	 * Add a register to the store
	 * @param register The register to add
	 */
	public void addRegister(Register register)
	{
		this.registers.put(register.getNumber(), register);
	}

	/**
	 * Remove a register from the store
	 * @param register The register to remove
	 */
	public void removeRegister(Register register)
	{
		this.registers.remove(register.getNumber());
	}

	/**
	 * Add a cashier to the store
	 * @param cashier The cashier to add
	 */
	public void addCashier(Cashier cashier)
	{
		this.cashiers.put(cashier.getNumber(), cashier);
	}

	/**
	 * Remove a cashier from the store
	 * @param cashier The cashier to remove
	 */
	public void removeCashier(Cashier cashier)
	{
		this.cashiers.remove(cashier.getNumber());
	}

	/**
	 * Add a sales session in the store
	 * @param session The session to add
	 */
	public void addSession(Session session)
	{
		sessions.add(session);
	}

	/**
	 * Remove a sales session from the store
	 * @param session The session to remove
	 */
	public void removeSession(Session session)
	{
		sessions.remove(session);
	}

	/**
	 * Find an item based on its UPC
	 * @param upc The UPC to search for
	 * @return The item with the specified UPC
	 */
	public Item findItemForUPC(String upc)
	{
		Item item = null;
		for (Item i : items.values())
		{
			for (UPC u : i.getUPCs())
			{
				if (u.getUPC().equals(upc))
				{
					item = i;
				}
			}
		}
		return item;
	}

	/**
	 * Find an item based on its number
	 * @param number The number to search for
	 * @return The item with the specified number
	 */
	public Item findItemForNumber(String number)
	{
		return items.get(number);
	}

	/**
	 * Find a cashier based on his or her number
	 * @param number The number to search for
	 * @return The cashier with the specified number
	 */
	public Cashier findCashierForNumber(String number)
	{	
		return cashiers.get(number);	
	}

	/**
	 * Convert the class to a string
	 * @return The string that represents the class
	 */
	public String toString()
	{
		String output = "";
		//store name
		output += "Name: " + name + "\n";
		//cashiers
		output += "==============\n";
		output += "Cashiers\n";
		output += "==============\n";
		/*for (Cashier c : cashiers)
		{
			output += c.toString() + "\n";
		}*/
		for (Cashier c : cashiers.values())
		{
			output += c.toString() + "\n";
		}
		//registers
		output += "==============\n";
		output += "Registers\n";
		output += "==============\n";
		for (Register r : registers.values())
		{
			output += r.toString() + "\n";
		}
		//Tax Categories
				output += "==============\n";
				output += "Tax Categories\n";
				output += "==============\n";
				for (TaxCategory t : taxCategories.values())
				{
					output += t.toString() + "\n";
				}
		//items
		output += "==============\n";
		output += "Items\n";
		output += "==============\n";
		for (Item i : items.values())
		{
			output += i.toString() + "\n";
		}
		//sessions
		output += "==============\n";
		output += "Sessions\n";
		output += "==============\n";
		for (Session s : sessions)
		{
			output += s.toString() + "\n";
		}
		return output;
	}

	/**
	 * Add a tax category to the store
	 * @param taxCategory The tax category to add
	 */
	public void addTaxCategory(TaxCategory taxCategory)
	{
		this.taxCategories.put(taxCategory.getCategory(),taxCategory);
		
	}

	/**
	 * Remove a tax category from the store
	 * @param taxCategory The tax category to remove
	 */
	public void removeTaxCategory(TaxCategory taxCategory)
	{
		this.taxCategories.remove(taxCategory.getCategory());
	}
	
	
	/**
	 * Find a register based on its number
	 * @param number The number to search for
	 * @return The register with the specified number
	 */
	public Register findRegisterForNumber(String number)
	{
		return registers.get(number);
	}
	
	public TaxCategory findTaxCategory(String category)
	{
		return taxCategories.get(category);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public Collection<Register> getRegisters()
	{
		return registers.values();
	}
	
	public Collection<Cashier> getCashiers()
	{
		return cashiers.values();
	}
	
	public Collection<TaxCategory> getTaxCategories()
	{
		return taxCategories.values();
	}
	
	public Collection<Item> getItems()
	{
		return items.values();
	}
	
	public Collection<UPC> getUPCs()
	{
		return upcs.values();
	}
	
	public Collection<Session> getSessions()
	{
		return sessions;
	}
}