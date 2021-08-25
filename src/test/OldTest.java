/*package test;

import problemDomain.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OldTest
{
	public static void main(String[] args)
	{
		Store store = new Store("11592853", "Conny Con Con's");
		
		//add the cashiers
		Person tempPerson = new Person("David", "Some Address", "123-456-7890", "123-45-6789");
		Cashier tempCashier = new Cashier("1", tempPerson, "IHateMyJob1");
		store.addCashier(tempCashier);
		tempPerson = new Person("Sally", "Some Address", "123-456-7890", "123-45-6789");
		tempCashier = new Cashier("2", tempPerson, "IHateMyJobToo");
		store.addCashier(tempCashier);
		tempPerson = new Person("Joe", "Some Address", "123-456-7890", "123-45-6789");
		tempCashier = new Cashier("3", tempPerson, "IHateMyJobThree");
		store.addCashier(tempCashier);
		
		//add the registers
		CashDrawer tempCashDrawer = new CashDrawer();
		Register tempRegister = new Register("1");
		tempCashDrawer.addCash(new BigDecimal("20"));
		tempRegister.setCashDrawer(tempCashDrawer);
		store.addRegister(tempRegister);
		
		tempCashDrawer = new CashDrawer();
		tempRegister = new Register("2");
		tempCashDrawer.addCash(new BigDecimal("350"));
		tempRegister.setCashDrawer(tempCashDrawer);
		store.addRegister(tempRegister);
		
		//add the items
		Price tempPrice = new Price("2.29", "10/10/2018");
		TaxRate tempTaxRate = new TaxRate(LocalDate.now(), new BigDecimal("0.07"));
		TaxCategory tempTaxCategory = new TaxCategory("Sandwiches");
		UPC tempUPC = new UPC("392947193856");
		tempTaxCategory.addTaxRate(tempTaxRate);
		Item tempItem = new Item("1001", "Turkey Sandwich", tempTaxCategory);
		tempItem.addUPC(tempUPC);
		tempItem.addPrice(tempPrice);
		tempPrice = new Price("2.79", "08/12/2018");
		tempItem.addPrice(tempPrice);
		store.addItem(tempItem);
		
		tempPrice = new Price("2.59", "10/10/2018");
		tempUPC = new UPC("017384017293");
		tempItem = new Item("1002", "HamSandwich", tempTaxCategory);
		tempItem.addUPC(tempUPC);
		tempItem.addPrice(tempPrice);
		tempPrice = new Price("3.19", "08/12/2018");
		tempItem.addPrice(tempPrice);
		store.addItem(tempItem);
		
		tempPrice = new Price("0.97", "10/10/2018");
		tempTaxRate = new TaxRate(LocalDate.now(), new BigDecimal("0"));
		tempTaxCategory = new TaxCategory("Drinks");
		tempTaxCategory.addTaxRate(tempTaxRate);
		tempUPC = new UPC("017384017293");
		tempItem = new Item("1003", "Coke", tempTaxCategory);
		tempItem.addUPC(tempUPC);
		tempItem.addPrice(tempPrice);
		tempPrice = new Price("0.49", "08/12/2018");
		tempItem.addPrice(tempPrice);
		store.addItem(tempItem);
		
		tempPrice = new Price("0.79", "10/10/2018");
		tempUPC = new UPC("280924018239");
		tempItem = new Item("1004", "Dr. Pepper", tempTaxCategory);
		tempItem.addUPC(tempUPC);
		tempItem.addPrice(tempPrice);
		tempPrice = new Price("0.39", "08/12/2018");
		tempItem.addPrice(tempPrice);
		store.addItem(tempItem);
		
		//add the sessions
		
		Session tempSession = new Session(store.findCashierForNumber("1"), store.findRegisterForNumber("2"));
		Sale tempSale = new Sale();
		tempSale.setDateTime(LocalDateTime.now());
		//initialize tempSale's saleLineitems
		SaleLineItem tempSaleLineItem = new SaleLineItem(store.findItemForNumber("1001"), "1");
		tempSaleLineItem.setSale(tempSale);
		tempSale.addSaleLineItem(tempSaleLineItem);
		tempSaleLineItem = new SaleLineItem(store.findItemForNumber("1002"), "2");
		tempSaleLineItem.setSale(tempSale);
		tempSale.addSaleLineItem(tempSaleLineItem);
		tempSession.addSale(tempSale);
		store.addSession(tempSession);
		
		System.out.println(store.toString());
	}

}
*/