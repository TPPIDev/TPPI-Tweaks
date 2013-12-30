package tppitweaks.item;

import tppitweaks.config.ConfigurationHandler;

public class ModItems {
	
	public static TPPIBook tppiBook;
	
	public static void initItems() {
		tppiBook = new TPPIBook(ConfigurationHandler.bookID);
	}

}
