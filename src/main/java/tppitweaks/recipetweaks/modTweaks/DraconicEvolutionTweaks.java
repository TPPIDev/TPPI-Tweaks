package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import tppitweaks.item.ModItems;

public class DraconicEvolutionTweaks {
	@RecipeRemoval(requiredModids="DraconicEvolution")
	public static void removeRecipes()
	{
		if (ConfigurationHandler.disableDraconicChest)
			TweakingRegistry.markItemForRecipeRemoval(GameRegistry.findItem("DraconicEvolution", "draconiumChest"), 0, TweakingAction.REMOVED, "Disabled due to performance","concerns.");
		
		if (ConfigurationHandler.harderDraconiumBlendRecipe)
			TweakingRegistry.markItemForRecipeRemoval(GameRegistry.findItem("DraconicEvolution", "draconiumBlend"), 0, TweakingAction.CHANGED, "Increased cost due to power","of items from the mod.");
	}
	
	@RecipeAddition(requiredModids="DraconicEvolution")
	public static void addRecipes()
	{
		if (ConfigurationHandler.harderDraconiumBlendRecipe)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("DraconicEvolution", "draconiumBlend")), 
					" D ",
					"DBD",
					" D ",
					'D', new ItemStack(GameRegistry.findItem("DraconicEvolution", "draconiumDust")),
					'B', new ItemStack(ModItems.tppiMaterial, 1, 4)
			));
	}
}
