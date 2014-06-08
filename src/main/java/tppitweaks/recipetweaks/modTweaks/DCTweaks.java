package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import bluedart.item.DartItem;

public class DCTweaks {

	@RecipeRemoval(requiredModids="DartCraft")
	public static void init()
	{
		if (ConfigurationHandler.disableForceShears && DartItem.forceShears != null)
			TweakingRegistry.markItemForRecipeRemoval(((Item) DartItem.forceShears).itemID, 0, TweakingAction.REMOVED, "Crashes servers");
	}
}
