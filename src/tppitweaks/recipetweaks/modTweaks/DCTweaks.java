package tppitweaks.recipetweaks.modTweaks;

import java.util.ListIterator;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;

public class DCTweaks {

	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(ic2.core.Ic2Items.energyCrystal.itemID, -1);
	}
}
