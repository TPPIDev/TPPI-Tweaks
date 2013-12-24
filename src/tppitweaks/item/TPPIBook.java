package tppitweaks.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class TPPIBook {
	
	public static void registerRecipe() {
		GameRegistry.addShapelessRecipe(getBook(), Item.book, Item.coal);
	}
	
	public static ItemStack getBook() {
		ItemStack tppiBook = new ItemStack(Item.writtenBook);
		tppiBook.setTagInfo("author", new NBTTagString("author", "The TPPI Team"));
		tppiBook.setTagInfo("title", new NBTTagString("title", "TPPI Worldgen Field Guide"));
		return tppiBook;
	}
	
}