package tppitweaks.recipetweaks.modTweaks;

import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.RecipeRemoval;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class AM2Tweaks
{
	@RecipeAddition(requiredModids="AM2")
	public static void addRecipes()
	{
		if (ConfigurationHandler.tweakAM2)
		{
			GameRegistry.addShapedRecipe(new ItemStack(am2.items.ItemsCommonProxy.spawnEgg, 1, 12), new Object[] { "CCC", "CPC", "CCC", 'C', new ItemStack(am2.items.ItemsCommonProxy.essence, 1, 5),
					'P', new ItemStack(am2.blocks.BlocksCommonProxy.aum) });
		}
	}
}
