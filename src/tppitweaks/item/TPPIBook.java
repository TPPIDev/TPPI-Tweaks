package tppitweaks.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;
import cpw.mods.fml.common.registry.GameRegistry;

public class TPPIBook {

	public static ItemStack tppiBook;
	
	public TPPIBook() {
		tppiBook = new ItemStack(Item.writtenBook);
		tppiBook.setTagInfo("author", new NBTTagString("author", "The TPPI Team"));
		tppiBook.setTagInfo("title", new NBTTagString("title", "TPPI Worldgen Field Guide"));
		
	}
	
	public void registerRecipe() {
		GameRegistry.addRecipe(tppiBook,
			new Object[] {	"D",
								
							'D', Item.diamond,
						});
	}
	
}