package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thermalexpansion.item.TEItems;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import codechicken.enderstorage.EnderStorage;
import codechicken.enderstorage.api.EnderStorageManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class EnderStorageTweaks extends TweakingRegistry
{
	@RecipeRemoval(requiredModids = { "EnderStorage", "ThermalExpansion" })
	public static void init()
	{
		if (ConfigurationHandler.enderPouchNerf)
		{
			TweakingRegistry.markItemForRecipeRemoval(((Item) EnderStorage.itemEnderPouch).itemID, -1, TweakingAction.CHANGED, "Recipe requires pyrotheum+ender bucket",
					"so it requires midgame infrastructure");
		}

		for (int i = 0; i < 16; i++)
		{
			if (ConfigurationHandler.enderChestNerf)
			{
				TweakingRegistry.markItemForRecipeRemoval(((Block) EnderStorage.blockEnderChest).blockID, EnderStorageManager.getFreqFromColours(i, i, i), TweakingAction.CHANGED,
						"Recipe requires tesseract frame", "because it is able to teleport things.");
			}

			if (ConfigurationHandler.enderTankNerf)
			{
				TweakingRegistry.markItemForRecipeRemoval(((Block) EnderStorage.blockEnderChest).blockID, EnderStorageManager.getFreqFromColours(i, i, i) + 4096, TweakingAction.CHANGED,
						"Recipe requires resonant tank", "because it is able to teleport things.");
			}
		}
	}

	@RecipeAddition(requiredModids = { "EnderStorage", "ThermalExpansion" })
	public static void addRecipes()
	{
		ItemStack tesseractFrameEmpty = new ItemStack(TEItems.itemComponent, 1, 129);
		ItemStack reinforcedTank = new ItemStack(thermalexpansion.block.TEBlocks.blockTank, 1, 4);

		for (int i = 0; i < 16; i++)
		{
			// @formatter:off
			if (ConfigurationHandler.enderChestNerf)
			{
				GameRegistry.addRecipe(new ItemStack(codechicken.enderstorage.EnderStorage.blockEnderChest, 1, EnderStorageManager.getFreqFromColours(i, i, i)), 
						"bWb",
						"OCO",
						"bOb",

						'b', Item.blazeRod, 
						'C', tesseractFrameEmpty, 
						'O', Block.obsidian, 
						'W', new ItemStack(Block.cloth, 1, i)
				);
			}

			if (ConfigurationHandler.enderTankNerf)
			{
				GameRegistry.addRecipe(new ItemStack(codechicken.enderstorage.EnderStorage.blockEnderChest, 1, EnderStorageManager.getFreqFromColours(i, i, i) + 4096), 
						"OWO",
						"bCb", 
						"bOb",

						'b', Item.blazeRod, 
						'C', reinforcedTank, 
						'O', Block.obsidian, 
						'p', Item.enderPearl, 
						'W', new ItemStack(Block.cloth, 1, i)
				);
			}

			if (ConfigurationHandler.enderPouchNerf)
			{
				GameRegistry.addRecipe(new ItemStack(codechicken.enderstorage.EnderStorage.itemEnderPouch, 1, codechicken.enderstorage.api.EnderStorageManager.getFreqFromColours(i, i, i)), 
						"pep",
						"lWl", 
						"plp",

						'p', thermalexpansion.item.TEItems.dustPyrotheum, 
						'l', Item.leather, 
						'e', Item.enderPearl, 
						'W', new ItemStack(Block.cloth, 1, i)
				);
			}
			// @formatter:on
		}
	}
}
