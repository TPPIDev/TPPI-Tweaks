package tppitweaks.recipetweaks.modTweaks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DATweaks
{
	public static void init()
	{
		TweakerBase.recipesToRemove.put(((Block) mods.immibis.chunkloader.DimensionalAnchors.instance.block).blockID, -1);
	}
	
	public static void addRecipe()
	{
			GameRegistry.addRecipe(new ItemStack(mods.immibis.chunkloader.DimensionalAnchors.instance.block, 1, 0), "ded", "oIo", "gog", 'd', Item.diamond, 'e', Item.enderPearl, 'o', Block.obsidian, 'I', Block.blockIron,
					'g', Item.ingotGold);
	}
}
