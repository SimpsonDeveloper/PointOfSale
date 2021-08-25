package dataManagement;

import problemDomain.*;
import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataManager
{
	TaxRate tempTaxRate;
	TaxCategory tempTaxCategory;
	Person tempPerson;
	Cashier tempCashier;
	UPC tempUPC;
	Price tempPrice;
	Item tempItem;
	Register tempRegister;
	SaleLineItem tempSaleLineItem;
	Payment tempPayment;
	
	Session tempSession;
	private boolean sessionExists = false;
	
	Sale tempSale;
	ArrayList<Sale>currentSales = new ArrayList<Sale>();
	private int saleIdx = -1;
	
	public DataManager()
	{
	}
	
	public void readFile(Store store)
	{
		String fileName = "StoreData_v2018.csv";
		String buffer = "";
		try
		{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((buffer = bufferedReader.readLine()) != null)
			{
				String[] token = buffer.split(",");
				
				switch (token[0])
				{
				case "Store":
					store.setName(token[1]);
					break;
					
				case "TaxCategory":
					tempTaxCategory = new TaxCategory(token[1]);
					tempTaxRate = new TaxRate(token[3], new BigDecimal(token[2]));
					tempTaxCategory.addTaxRate(tempTaxRate);
					store.addTaxCategory(tempTaxCategory);
					break;
					
				case "Cashier":
					//String address = token[4] + ", " + token[5] + ", " + token[6] + ", " + token[7];
					tempPerson = new Person(token[2], token[4], token[5], token[6], token[7], token[8], token[3]);
					tempCashier = new Cashier(token[1], tempPerson, token[9]);
					store.addCashier(tempCashier);
					break;
					
				case "Item":
					tempItem = new Item(token[1], token[3]);
					tempUPC = new UPC(token[2]);
					tempItem.addUPC(tempUPC);
					tempItem.setTaxCategory(store.findTaxCategory(token[4]));
					tempPrice = new Price(token[5], token[6]);
					tempItem.addPrice(tempPrice);
					if (token.length > 7)
					{
						//add the PromoPrice
						tempPrice = new PromoPrice(token[7], token[8], token[9]);
						tempItem.addPrice(tempPrice);
					}
					store.addItem(tempItem);
					break;
					
				case "Register":
					tempRegister = new Register(token[1]);
					store.addRegister(tempRegister);
					break;
					
				case "Session":
					if (sessionExists)
					{
						for (int i = 0; i < currentSales.size(); i++)
						{
							tempSession.addSale(currentSales.get(i));
						}
						currentSales.clear();
						saleIdx = -1;
						store.addSession(tempSession);
					}
					sessionExists = true;
					//start a new session
					tempSession = new Session(store.findCashierForNumber(token[1]), store.findRegisterForNumber(token[2]));
					break;
					
				case "Sale":
					saleIdx++;
					tempSale = new Sale(token[1]);
					currentSales.add(tempSale);
					break;
					
				case "SaleLineItem":
					tempSaleLineItem = new SaleLineItem(store.findItemForNumber(token[1]), token[2]);
					tempSaleLineItem.setSale(currentSales.get(saleIdx));
					currentSales.get(saleIdx).addSaleLineItem(tempSaleLineItem);
					break;
					
				case "Payment":
					switch (token[1])
					{
					case "Cash":
						tempPayment = new Cash(token[2], token[3]);
						currentSales.get(saleIdx).addPayment(tempPayment);
						break;
					case "Check":
						tempPayment = new Check(token[2], token[5], token[4], token[6]);
						tempPayment.setAmtTendered(new BigDecimal(token[3]));
						currentSales.get(saleIdx).addPayment(tempPayment);
						break;
					case "Credit":
						tempPayment = new Credit(token[4], token[5], token[6]);
						tempPayment.setAmount(new BigDecimal(token[2]));
						tempPayment.setAmtTendered(new BigDecimal(token[3]));
						currentSales.get(saleIdx).addPayment(tempPayment);
						break;
					}
					break;
				}
			}
			for (int i = 0; i < currentSales.size(); i++)
			{
				tempSession.addSale(currentSales.get(i));
			}
			store.addSession(tempSession);
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Error: Could not open file");
		}
		catch (IOException e)
		{
			System.out.println("Error: Could not read line");
		}
	}
}
