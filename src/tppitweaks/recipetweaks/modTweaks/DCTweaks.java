package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import tppitweaks.config.ConfigurationHandler;

public class DCTweaks {

	public static void init()
	{
		if (ConfigurationHandler.disableForceShears)
			TweakerBase.markItemForRecipeRemoval(((Item)bluedart.item.DartItem.forceShears).itemID, 0);
	}
}
