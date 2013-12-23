package tppitweaks.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SpecialRecipes {
	
	public static ItemStack stack;
	
	public static void registerSpecialRecipes()
	{
		ItemStack stack = new ItemStack(Item.writtenBook);
		
		stack.setTagCompound(new NBTTagCompound("pages"));
		
		NBTTagCompound pages = new NBTTagCompound();
		pages.setString("page1", "Test Page Please Ignore");
		pages.setString("page2", "Test Page Please Ignore Again");
		
		stack.stackTagCompound.setTag("pages", pages);
	}

}
