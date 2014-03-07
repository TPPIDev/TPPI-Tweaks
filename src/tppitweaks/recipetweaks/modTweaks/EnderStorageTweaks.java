package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class EnderStorageTweaks extends TweakerBase
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(((Block) codechicken.enderstorage.EnderStorage.blockEnderChest).blockID, -1);
		TweakerBase.markItemForRecipeRemoval(((Item) codechicken.enderstorage.EnderStorage.itemEnderPouch).itemID, -1);		
	}
	
	public static void addRecipes()
	{
		Object chestEnderElement = ConfigurationHandler.enderChestTesseract ? thermalexpansion.block.TEBlocks.blockTesseract : Item.enderPearl;
		Object pouchEnderElement = ConfigurationHandler.enderPouchPyrotheum ? thermalexpansion.item.TEItems.dustPyrotheum : Item.enderPearl;
		Object tankEnderElement = ConfigurationHandler.enderTankTesseract ? thermalexpansion.block.TEBlocks.blockTesseract : Item.enderPearl;

		for (int i = 0; i < 16; i++)
		{
			GameRegistry.addRecipe(new ItemStack(codechicken.enderstorage.EnderStorage.blockEnderChest, 1, codechicken.enderstorage.api.EnderStorageManager.getFreqFromColours(i, i, i)), new Object[] { "bWb", "OCO", "bpb", 'b', Item.blazeRod, 'p',
					chestEnderElement, 'O', Block.obsidian, 'C', Block.chest, 'W', new ItemStack(Block.cloth, 1, i) });

			GameRegistry.addRecipe(new ItemStack(codechicken.enderstorage.EnderStorage.itemEnderPouch, 1, codechicken.enderstorage.api.EnderStorageManager.getFreqFromColours(i, i, i)), new Object[] { "blb", "lpl", "bWb", 'b', Item.blazePowder, 'p',
					pouchEnderElement, 'l', Item.leather, 'W', new ItemStack(Block.cloth, 1, i) });

			GameRegistry.addRecipe(new ItemStack(codechicken.enderstorage.EnderStorage.blockEnderChest, 1, 1 << 12 | codechicken.enderstorage.api.EnderStorageManager.getFreqFromColours(i, i, i)), new Object[] { "bWb", "OCO", "bpb", 'b',
					Item.blazeRod, 'p', tankEnderElement, 'O', Block.obsidian, 'C', Item.cauldron, 'W', new ItemStack(Block.cloth, 1, i) });
		}
	}
}
