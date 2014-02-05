package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class TETweaks {

	public static void addPaperRecipe() {
		try {
			GameRegistry.addShapelessRecipe(new ItemStack(Item.paper, 3), new Object[] {thermalexpansion.item.TEItems.woodchips, thermalexpansion.item.TEItems.woodchips, thermalexpansion.item.TEItems.woodchips});
		}catch(Throwable t) {}
	}
	
}
