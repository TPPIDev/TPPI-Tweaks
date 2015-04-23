package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class JABBATweaks
{
	@RecipeRemoval(requiredModids="JABBA")
	public static void init()
	{
		if (ConfigurationHandler.tweakJABBA)
			TweakingRegistry.markItemForRecipeRemoval(GameRegistry.findItem("JABBA", "moverDiamond"), 0, TweakingAction.CHANGED, "Recipe changed to balance", "against other ways to","move spawners.");
	}

	@RecipeAddition(requiredModids="JABBA")
	public static void addRecipes()
	{
		if (ConfigurationHandler.tweakJABBA)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("JABBA", "moverDiamond")), 
					" N ",
					"dWd",
					" d ",

					'W', new ItemStack(GameRegistry.findItem("JABBA", "mover")),
					'd', new ItemStack(Items.diamond),
					'N', new ItemStack(Items.nether_star)
					));
		}
	}
}
