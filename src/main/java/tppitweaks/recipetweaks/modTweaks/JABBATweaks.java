package tppitweaks.recipetweaks.modTweaks;

import mcp.mobius.betterbarrels.BetterBarrels;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import powercrystals.minefactoryreloaded.setup.Machine;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;
import factorization.shared.Core;

public class JABBATweaks
{
	@RecipeRemoval(requiredModids={"JABBA", "factorization", "MineFactoryReloaded"})
	public static void init()
	{
		if (ConfigurationHandler.tweakJABBA)
			TweakingRegistry.markItemForRecipeRemoval(BetterBarrels.barrelID, -1, TweakingAction.CHANGED, "Recipe changed to reflect", "the title of 'better barrel'");
	}

	@RecipeAddition(requiredModids={"JABBA", "factorization", "MineFactoryReloaded"})
	public static void addRecipes()
	{
		if (ConfigurationHandler.tweakJABBA)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(BetterBarrels.blockBarrel, 
					"wBw",
					"wUw",
					"www",

					'w', "plankWood",
					'B', new ItemStack(Core.registry.daybarrel, 1, OreDictionary.WILDCARD_VALUE),
					'U', Machine.Unifier.getItemStack()
					));
		}
	}
}
