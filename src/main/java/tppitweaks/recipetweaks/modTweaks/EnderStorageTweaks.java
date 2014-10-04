package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thermalexpansion.block.TEBlocks;
import thermalexpansion.block.simple.BlockFrame;
import thermalfoundation.item.TFItems;
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
            TweakingRegistry.markItemForRecipeRemoval((Item) EnderStorage.itemEnderPouch, -1, TweakingAction.CHANGED, "Recipe requires pyrotheum+ender bucket",
                    "so it requires midgame infrastructure");
        }

        for (int i = 0; i < 16; i++)
        {
            if (ConfigurationHandler.enderChestNerf)
            {
                TweakingRegistry.markItemForRecipeRemoval(EnderStorage.blockEnderChest, EnderStorageManager.getFreqFromColours(i, i, i), TweakingAction.CHANGED,
                        "Recipe requires tesseract frame", "because it is able to teleport things.");
            }

            if (ConfigurationHandler.enderTankNerf)
            {
                TweakingRegistry.markItemForRecipeRemoval(EnderStorage.blockEnderChest, EnderStorageManager.getFreqFromColours(i, i, i) + 4096, TweakingAction.CHANGED,
                        "Recipe requires resonant tank", "because it is able to teleport things.");
            }
        }
    }

    @RecipeAddition(requiredModids = { "EnderStorage", "ThermalExpansion" })
    public static void addRecipes()
    {
        ItemStack tesseractFrameEmpty = BlockFrame.frameTesseractEmpty.copy();
        ItemStack reinforcedTank = new ItemStack(TEBlocks.blockTank, 1, 4);

        for (int i = 0; i < 16; i++)
        {
            /* @formatter:off */
			if (ConfigurationHandler.enderChestNerf)
			{
				GameRegistry.addRecipe(new ItemStack(EnderStorage.blockEnderChest, 2, EnderStorageManager.getFreqFromColours(i, i, i)), 
						"bWb",
						"OCO",
						"bOb",

						'b', Items.blaze_rod, 
						'C', tesseractFrameEmpty, 
						'O', Blocks.obsidian, 
						'W', new ItemStack(Blocks.wool, 1, i)
				);
			}

			if (ConfigurationHandler.enderTankNerf)
			{
				GameRegistry.addRecipe(new ItemStack(EnderStorage.blockEnderChest, 2, EnderStorageManager.getFreqFromColours(i, i, i) + 4096), 
						"OWO",
						"bCb", 
						"bOb",

						'b', Items.blaze_rod, 
						'C', reinforcedTank, 
						'O', Blocks.obsidian, 
						'p', Items.ender_pearl, 
						'W', new ItemStack(Blocks.wool, 1, i)
				);
			}

			if (ConfigurationHandler.enderPouchNerf)
			{
				GameRegistry.addRecipe(new ItemStack(EnderStorage.itemEnderPouch, 1, EnderStorageManager.getFreqFromColours(i, i, i)), 
						"pep",
						"lWl", 
						"plp",

						'p', TFItems.dustPyrotheum.copy(), 
						'l', Items.leather, 
						'e', Items.ender_pearl, 
						'W', new ItemStack(Blocks.wool, 1, i)
				);
			}
			/* @formatter:on */
        }
    }
}
