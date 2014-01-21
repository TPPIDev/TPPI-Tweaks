package tppitweaks.recipetweaks.modTweaks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class OpenBlocksTweaks
{
	public static void init()
	{
		TweakerBase.recipesToRemove.put(((Block) openblocks.OpenBlocks.Blocks.blockBreaker).blockID, -1);
		TweakerBase.recipesToRemove.put(((Block) openblocks.OpenBlocks.Blocks.blockPlacer).blockID, -1);
	}

	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((openblocks.OpenBlocks.Blocks.blockBreaker), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Item.pickaxeIron, 'P',
				Block.pistonBase, 'R', Item.redstone }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((openblocks.OpenBlocks.Blocks.blockPlacer), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Block.chest, 'P',
				Block.pistonBase, 'R', Item.redstone }));
	}
}
