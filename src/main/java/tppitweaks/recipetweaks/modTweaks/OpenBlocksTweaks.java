package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class OpenBlocksTweaks
{
	@RecipeRemoval(requiredModids="OpenBlocks")
	public static void init()
	{
		if (ConfigurationHandler.eloraamBreakersAndDeployers)
		{
			if (openblocks.OpenBlocks.Blocks.blockBreaker != null)
				TweakingRegistry.markItemForRecipeRemoval(((Block) openblocks.OpenBlocks.Blocks.blockBreaker).blockID, -1, TweakingAction.CHANGED, "Recipe changed to bring back", "RP2-like recipes");

			if (openblocks.OpenBlocks.Blocks.blockPlacer != null)
				TweakingRegistry.markItemForRecipeRemoval(((Block) openblocks.OpenBlocks.Blocks.blockPlacer).blockID, -1, TweakingAction.CHANGED, "Recipe changed to bring back", "RP2-like recipes");
		}
	}

	@RecipeAddition(requiredModids="OpenBlocks")
	public static void addRecipes()
	{
		if (ConfigurationHandler.eloraamBreakersAndDeployers)
		{
			if (openblocks.OpenBlocks.Blocks.blockBreaker != null)
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((openblocks.OpenBlocks.Blocks.blockBreaker), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Item.pickaxeIron,
						'P', Block.pistonBase, 'R', Item.redstone }));
			
			if (openblocks.OpenBlocks.Blocks.blockPlacer != null)
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((openblocks.OpenBlocks.Blocks.blockPlacer), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Block.chest, 'P',
						Block.pistonBase, 'R', Item.redstone }));
		}
	}
}
