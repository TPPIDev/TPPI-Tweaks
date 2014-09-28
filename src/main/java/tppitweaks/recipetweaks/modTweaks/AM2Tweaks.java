package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import am2.blocks.BlocksCommonProxy;
import cpw.mods.fml.common.registry.GameRegistry;

public class AM2Tweaks
{
	@RecipeAddition(requiredModids="arsmagica2")
	public static void addRecipes()
	{
		ItemStack spawnEgg = new ItemStack(am2.items.ItemsCommonProxy.spawnEgg, 1, 13);
		
		if (ConfigurationHandler.tweakAM2)
		{
			TweakingRegistry.addTweakedTooltip(spawnEgg.itemID, spawnEgg.getItemDamage(), TweakingAction.ADDED, "To allow spawning", "of Nature Guardians", "Without killing dryads");

			GameRegistry.addShapedRecipe(spawnEgg, new Object[] { "CCC", "CPC", "CCC", 'C', new ItemStack(am2.items.ItemsCommonProxy.essence, 1, 5),
					'P', new ItemStack(am2.blocks.BlocksCommonProxy.aum) });
		}
		
		if (ConfigurationHandler.disableDeconstructor)
		{
			TweakingRegistry.markItemForRecipeRemoval(((Block) BlocksCommonProxy.arcaneDeconstructor).blockID, -1, TweakingAction.REMOVED, "Removed due to major", "dupes with witchery and", "other mods");
		}
	}
}
