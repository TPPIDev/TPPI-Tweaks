package tppitweaks.recipetweaks.modTweaks;

import java.lang.reflect.Field;

import tppitweaks.config.ConfigurationHandler;

public class AdvancedGeneticsTweaks {

	public static void init() {
		if(ConfigurationHandler.disableAGAutoOutputter) {
			TweakerBase.markItemForRecipeRemoval(com.advGenetics.AdvGenetics.autoOutputUpgrade.itemID, -1);
		}
	}
	
}