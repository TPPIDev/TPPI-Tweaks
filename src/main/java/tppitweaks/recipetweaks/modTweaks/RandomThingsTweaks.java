package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class RandomThingsTweaks {

	@RecipeRemoval(requiredModids="RandomThings")
	public static void init() {
		if (ConfigurationHandler.harderActivatorRecipe)
			TweakingRegistry.markItemForRecipeRemoval(GameRegistry.findItem("RandomThings", "advancedItemCollector"), 0, TweakingAction.CHANGED,"Made recipe more costly.");
	}
	
	@RecipeAddition(requiredModids="RandomThings")
	public static void addRecipes() {
		if (ConfigurationHandler.harderActivatorRecipe)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("RandomThings", "advancedItemCollector"),1,0),
					" D ",
					"ECE",
					"   ",
					
					'D', new ItemStack(GameRegistry.findItem("RandomThings", "ingredient"), 1, 6),
					'E', new ItemStack(GameRegistry.findItem("RandomThings", "ingredient"), 1, 3),
					'C', new ItemStack(GameRegistry.findItem("RandomThings", "itemCollector"))
					));
	}
}
