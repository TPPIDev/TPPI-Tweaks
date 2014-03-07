package tppitweaks.recipetweaks.modTweaks;

import tppitweaks.config.ConfigurationHandler;

public class AdvancedGeneticsTweaks {

	public static void init() {
		if(ConfigurationHandler.disableAGAutoOutputter) {
			TweakerBase.markItemForRecipeRemoval(com.advGenetics.AdvGenetics.autoOutputUpgrade.itemID, -1);
		}
	}	
}