package tppitweaks.item;

import tppitweaks.config.ConfigurationHandler;
import net.minecraft.item.Item;

public class ModItems {
	
	public static TPPIBook tppiBook;
	
	public static void initItems() {
		tppiBook = new TPPIBook(ConfigurationHandler.bookID);
	}

}
