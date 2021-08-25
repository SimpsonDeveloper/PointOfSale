package HI;

import problemDomain.Store;

public class POSRunner
{

	public static void main(String[] args)
	{
		Store store = new Store();
		store.loadData();
		POSFrame.run(store);
	}

}
