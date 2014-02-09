package tppitweaks.recipetweaks.modTweaks;

import tppitweaks.config.ConfigurationHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DCTweaks {

	public static void init()
	{
		if (ConfigurationHandler.disableForceShears)
			TweakerBase.markItemForRecipeRemoval(((Item)bluedart.item.DartItem.forceShears).itemID, 0);
	}
}
