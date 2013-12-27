package tppitweaks.recipetweaks;

import ic2.core.Ic2Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thermalexpansion.block.TEBlocks;
import tppitweaks.config.ConfigurationHandler;
import codechicken.enderstorage.EnderStorage;
import codechicken.enderstorage.api.EnderStorageManager;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import erogenousbeef.bigreactors.common.BigReactors;
import erogenousbeef.bigreactors.common.block.BlockReactorPart;

public class RecipeTweaks {

	public static HashMap<Integer, Integer> recipesToRemove;
	private static boolean okayToTweakEnderStorage;
	private static boolean okayToTweakBigReactors;
	
	public static void doRecipeTweaks() {
		
		checkWhatWeCanTweak();
		initRemovableRecipesMap();
		removeSomeRecipes();
		addRevisedRecipes();
		
	}
	
	@SuppressWarnings({ "unchecked" })
	private static void removeSomeRecipes() {
	
		ListIterator<IRecipe> iterator = CraftingManager.getInstance().getRecipeList().listIterator();
		ItemStack recipeResult = null;
		while(iterator.hasNext()) {
			IRecipe r = iterator.next();
			ItemStack out = r.getRecipeOutput();
			if(canRemoveRecipe(out)) {
				iterator.remove();
			}
		}
		
	}
	
	private static void checkWhatWeCanTweak() {
		okayToTweakEnderStorage = Loader.isModLoaded("EnderStorage") && Loader.isModLoaded("ThermalExpansion");
		okayToTweakBigReactors = Loader.isModLoaded("BigReactors") && !OreDictionary.getOres("ingotSteel").isEmpty() && ConfigurationHandler.steelReactorCasings;
	}
	
	private static void initRemovableRecipesMap() {
		
		recipesToRemove = new HashMap<Integer, Integer>();
		
		/*
		 * Value -1 for key means no metadata sensitivity.
		 * Value = metadata for metadata sensitivity.
		 */
		
		if(okayToTweakEnderStorage) {
			recipesToRemove.put(((Block)EnderStorage.blockEnderChest).blockID, -1);
			recipesToRemove.put(((Item)EnderStorage.itemEnderPouch).itemID, -1);
		}
		if(okayToTweakBigReactors) {
			recipesToRemove.put(BigReactors.blockReactorPart.blockID, 0);
		}
		
	}
	
	private static boolean canRemoveRecipe(ItemStack output) {
		try {
			int removeableValueTest = recipesToRemove.get(output.itemID);
			return removeableValueTest == -1 || removeableValueTest == output.getItemDamage();
		}catch(Throwable e) {
			return false;
		}
	}
	
	private static void addRevisedRecipes()
    {	
		doOreDictTweaks();
		addEnderStorageRecipes();
		addBigReactorsRecipes();
    }
	
	private static void addEnderStorageRecipes() {
		if(okayToTweakEnderStorage) {
			
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

	private static void doOreDictTweaks() {
		if(Loader.isModLoaded("IC2") && OreDictionary.getOreID(Ic2Items.reinforcedGlass) == -1) {
			OreDictionary.registerOre("glassReinforced", Ic2Items.reinforcedGlass);
		}
		for(ItemStack stack : OreDictionary.getOres("glassReinforced")) {
			OreDictionary.registerOre("glassHardened", stack);
		}
		for(ItemStack stack : OreDictionary.getOres("glassHardened")) {
			OreDictionary.registerOre("glassReinforced", stack);
		}
	}
	
	private static void addBigReactorsRecipes() {
		
		if(okayToTweakBigReactors) {
			
			ItemStack reactorPartStack = ((BlockReactorPart) BigReactors.blockReactorPart).getReactorCasingItemStack(); 
			reactorPartStack.stackSize = 4;
			GameRegistry.addRecipe(new ShapedOreRecipe(reactorPartStack, new Object[] { "ICI", "CUC", "ICI", 'I', "ingotSteel", 'C', "ingotGraphite", 'U', "ingotYellorium" }));
			
		}
		
	}
	
}