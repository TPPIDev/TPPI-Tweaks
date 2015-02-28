package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class RefinedRelocationTweaks {
	@RecipeRemoval(requiredModids = "RefinedRelocation")
    public static void removeRecipes()
    {
		if (ConfigurationHandler.disableUncraftingRRChests)
		{
			TweakingRegistry.addTweakedTooltip(GameRegistry.findItem("RefinedRelocation", "sortingChest"), 0, TweakingAction.CHANGED, "Removed ability to turn back","into normal chest, due","to essentia exploit.");
			TweakingRegistry.markItemForRecipeRemoval(Blocks.chest, 0);
		}
    }

	@RecipeAddition(requiredModids = "RefinedRelocation")
    public static void addRecipes()
    {
		if (ConfigurationHandler.disableUncraftingRRChests)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.chest),
					"ppp",
					"p p",
					"ppp",
					'p',"plankWood"
					));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.chest, 4, 0),
					"lll",
					"l l",
					"lll",
					'l',"logWood"
					));
			
		}
    }
}
