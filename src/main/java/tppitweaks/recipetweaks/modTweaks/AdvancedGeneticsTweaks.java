package tppitweaks.recipetweaks.modTweaks;

import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.TweakingRegistry;
import tppitweaks.recipetweaks.RecipeRemoval;
import tppitweaks.recipetweaks.TweakingRegistry.TweakingAction;

public class AdvancedGeneticsTweaks {

	@RecipeRemoval(requiredModids="advancedgenetics")
	public static void init() {
		if(ConfigurationHandler.disableAGAutoOutputter) {
			TweakingRegistry.markItemForRecipeRemoval(com.advGenetics.AdvGenetics.autoOutputUpgrade.itemID, -1, TweakingAction.REMOVED, "Crashes the game");
		}
	}	
}