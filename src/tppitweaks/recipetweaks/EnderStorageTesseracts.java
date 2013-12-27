package tppitweaks.recipetweaks;

import java.util.ListIterator;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import thermalexpansion.block.TEBlocks;
import tppitweaks.config.ConfigurationHandler;
import codechicken.enderstorage.EnderStorage;
import codechicken.enderstorage.api.EnderStorageManager;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class EnderStorageTesseracts {

	public static void doEnderStorageTweaks() {
		
		if(Loader.isModLoaded("EnderStorage") && Loader.isModLoaded("ThermalExpansion")) {
			removeEnderStorageRecipes();
			addEnderStorageRecipes();
		}
		
	}
	
	private static void removeEnderStorageRecipes() {
	
		ListIterator<IRecipe> iterator = CraftingManager.getInstance().getRecipeList().listIterator();
		ItemStack recipeResult = null;
		while(iterator.hasNext()) {
			IRecipe r = iterator.next();
			ItemStack out = r.getRecipeOutput();
			int outputID = -1;
			if(out != null) {
				outputID = out.itemID;
			}
			if(outputID == EnderStorage.blockEnderChest.blockID || outputID == EnderStorage.itemEnderPouch.itemID) {
				iterator.remove();
			}
		}
		
	}
	
	private static void addEnderStorageRecipes()
    {
		
		Object chestEnderElement = ConfigurationHandler.enderChestTesseract ? TEBlocks.blockTesseract : Item.enderPearl;
		Object pouchEnderElement = ConfigurationHandler.enderPouchTesseract ? TEBlocks.blockTesseract : Item.enderPearl;
		Object tankEnderElement = ConfigurationHandler.enderTankTesseract ? TEBlocks.blockTesseract : Item.enderPearl;
		
		
        for(int i = 0; i < 16; i++)
        {
            GameRegistry.addRecipe(new ItemStack(EnderStorage.blockEnderChest, 1, EnderStorageManager.getFreqFromColours(i, i, i)), new Object[]{
                "bWb",
                "OCO",
                "bpb",
                'b', Item.blazeRod,
                'p', chestEnderElement,
                'O', Block.obsidian,
                'C', Block.chest,
                'W', new ItemStack(Block.cloth, 1, i)});

            GameRegistry.addRecipe(new ItemStack(EnderStorage.itemEnderPouch, 1, EnderStorageManager.getFreqFromColours(i, i, i)), new Object[]{
                "blb",
                "lpl",
                "bWb",
                'b', Item.blazePowder,
                'p', pouchEnderElement,
                'l', Item.leather,
                'W', new ItemStack(Block.cloth, 1, i)});
            
            GameRegistry.addRecipe(new ItemStack(EnderStorage.blockEnderChest, 1, 1<<12 | EnderStorageManager.getFreqFromColours(i, i, i)), new Object[]{
                "bWb",
                "OCO",
                "bpb",
                'b', Item.blazeRod,
                'p', tankEnderElement,
                'O', Block.obsidian,
                'C', Item.cauldron,
                'W', new ItemStack(Block.cloth, 1, i)});
        }
    }
	
}