package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.TweakingRegistry;
import tppitweaks.recipetweaks.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class AM2Tweaks
{
	@RecipeAddition(requiredModids="AM2")
	public static void addRecipes()
	{
		ItemStack spawnEgg = new ItemStack(am2.items.ItemsCommonProxy.spawnEgg, 1, 12);
		TweakingRegistry.addToolTipOnly(spawnEgg.itemID, spawnEgg.getItemDamage(), TweakingAction.ADDED, "To allow spawning", "of Nature Guardians", "Without killing dryads");
		
		if (ConfigurationHandler.tweakAM2)
		{
			GameRegistry.addShapedRecipe(spawnEgg, new Object[] { "CCC", "CPC", "CCC", 'C', new ItemStack(am2.items.ItemsCommonProxy.essence, 1, 5),
					'P', new ItemStack(am2.blocks.BlocksCommonProxy.aum) });
		}
	}
}
