package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.RecipeRemoval;
import tppitweaks.recipetweaks.TweakingRegistry;
import tppitweaks.recipetweaks.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class OpenBlocksTweaks
{
	@RecipeRemoval(requiredModids="OpenBlocks")
	public static void init()
	{
		if (ConfigurationHandler.eloraamBreakersAndDeployers)
		{
			TweakingRegistry.markItemForRecipeRemoval(((Block) openblocks.OpenBlocks.Blocks.blockBreaker).blockID, -1, TweakingAction.CHANGED, "Recipe changed to bring back", "RP2-like recipes");
			TweakingRegistry.markItemForRecipeRemoval(((Block) openblocks.OpenBlocks.Blocks.blockPlacer).blockID, -1, TweakingAction.CHANGED, "Recipe changed to bring back", "RP2-like recipes");
		}
	}

	@RecipeAddition(requiredModids="OpenBlocks")
	public static void addRecipes()
	{
		if (ConfigurationHandler.eloraamBreakersAndDeployers)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((openblocks.OpenBlocks.Blocks.blockBreaker), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Item.pickaxeIron,
					'P', Block.pistonBase, 'R', Item.redstone }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((openblocks.OpenBlocks.Blocks.blockPlacer), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Block.chest, 'P',
					Block.pistonBase, 'R', Item.redstone }));
		}
	}
}
