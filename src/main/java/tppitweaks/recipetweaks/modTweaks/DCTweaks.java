package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import tppitweaks.config.ConfigurationHandler;
import bluedart.item.DartItem;

public class DCTweaks {

	public static void init()
	{
		if (ConfigurationHandler.disableForceShears && DartItem.forceShears != null)
			TweakerBase.markItemForRecipeRemoval(((Item) DartItem.forceShears).itemID, 0);
	}
}
