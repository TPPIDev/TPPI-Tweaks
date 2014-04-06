package tppitweaks.recipetweaks.modTweaks;

import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.TweakingRegistry;
import tppitweaks.recipetweaks.RecipeRemoval;
<<<<<<< HEAD
=======
import tppitweaks.recipetweaks.TweakingRegistry.TweakingAction;
>>>>>>> origin/TweakerOverhaul

public class AdvancedGeneticsTweaks {

	@RecipeRemoval(requiredModids="advancedgenetics")
	public static void init() {
		if(ConfigurationHandler.disableAGAutoOutputter) {
<<<<<<< HEAD
			TweakingRegistry.markItemForRecipeRemoval(com.advGenetics.AdvGenetics.autoOutputUpgrade.itemID, -1);
=======
			TweakingRegistry.markItemForRecipeRemoval(com.advGenetics.AdvGenetics.autoOutputUpgrade.itemID, -1, TweakingAction.REMOVED, "Crashes the game");
>>>>>>> origin/TweakerOverhaul
		}
	}	
}