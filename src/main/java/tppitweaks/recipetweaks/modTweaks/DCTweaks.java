package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.RecipeRemoval;
import tppitweaks.recipetweaks.TweakingRegistry;
import bluedart.item.DartItem;

public class DCTweaks {

	@RecipeRemoval(requiredModids="DartCraft")
	public static void init()
	{
		if (ConfigurationHandler.disableForceShears && DartItem.forceShears != null)
			TweakingRegistry.markItemForRecipeRemoval(((Item) DartItem.forceShears).itemID, 0);
	}
}
