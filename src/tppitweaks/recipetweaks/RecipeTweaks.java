package tppitweaks.recipetweaks;

import gregtechmod.api.GregTech_API;
import ic2.core.Ic2Items;

import java.util.HashMap;
import java.util.ListIterator;

import mods.immibis.chunkloader.DimensionalAnchors;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import openblocks.OpenBlocks;
import thermalexpansion.block.TEBlocks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import appeng.api.Materials;
import codechicken.enderstorage.EnderStorage;
import codechicken.enderstorage.api.EnderStorageManager;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import erogenousbeef.bigreactors.common.BigReactors;
import erogenousbeef.bigreactors.common.block.BlockReactorPart;

public class RecipeTweaks {

	private static HashMap<Integer, Integer> recipesToRemove;
	private static boolean okayToTweakEnderStorage;
	private static boolean okayToTweakBigReactors;
	private static boolean okayToTweakDA;
	private static boolean okayToTweakSFM;
	private static boolean okayToTweakOpenBlocks;
	
	public static void doRecipeTweaks() {
		
		checkWhatWeCanTweak();
		initRemovableRecipesMap();
		removeSomeRecipes();
		addRevisedRecipes();
		registerAdditionalRecipes();
		
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
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
		okayToTweakBigReactors = Loader.isModLoaded("BigReactors") && !OreDictionary.getOres("ingotSteel").isEmpty() && (ConfigurationHandler.steelReactorCasings || ConfigurationHandler.glassFuelRods);
		okayToTweakDA = Loader.isModLoaded("DimensionalAnchors") && ConfigurationHandler.tweakDA;
		okayToTweakSFM = Loader.isModLoaded("AppliedEnergistics") && Loader.isModLoaded("StevesFactoryManager") && ConfigurationHandler.tweakSFM;
		okayToTweakOpenBlocks = Loader.isModLoaded("OpenBlocks") && ConfigurationHandler.eloraamBreakersAndDeployers;
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
			if(ConfigurationHandler.steelReactorCasings) {
				recipesToRemove.put(BigReactors.blockReactorPart.blockID, 0);
			}
			if(ConfigurationHandler.glassFuelRods) {
				recipesToRemove.put(BigReactors.blockYelloriumFuelRod.blockID, -1);
			}
		}
		if(okayToTweakDA) {
			recipesToRemove.put(((Block)DimensionalAnchors.instance.block).blockID, -1);
		}
		if(okayToTweakSFM) {
			recipesToRemove.put(((Block)vswe.stevesfactory.blocks.Blocks.blockManager).blockID, -1);
			recipesToRemove.put(((Block)vswe.stevesfactory.blocks.Blocks.blockCable).blockID, -1);
		}
		if(okayToTweakOpenBlocks) {
			recipesToRemove.put(((Block)OpenBlocks.Blocks.blockBreaker).blockID, -1);
			recipesToRemove.put(((Block)OpenBlocks.Blocks.blockPlacer).blockID, -1);
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
		addDARecipe();
		addSFMRecipes();
		addOpenBlocksRecipes();
    }
	
	private static void addOpenBlocksRecipes() {
		if(okayToTweakOpenBlocks) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((OpenBlocks.Blocks.blockBreaker), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Item.pickaxeIron, 'P', Block.pistonBase, 'R', Item.redstone }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((OpenBlocks.Blocks.blockPlacer), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Block.chest, 'P', Block.pistonBase, 'R', Item.redstone }));
		}
	}

	private static void addSFMRecipes() {
		if(okayToTweakSFM) {
			GameRegistry.addRecipe(new ItemStack(vswe.stevesfactory.blocks.Blocks.blockManager), new Object[] { "III", "IRI", "SPS", Character.valueOf('R'), new ItemStack(ModItems.tppiMaterial), Character.valueOf('P'), Materials.matConversionMatrix.copy(), Character.valueOf('I'), Item.ingotIron, Character.valueOf('S'), Block.stone });
			GameRegistry.addRecipe(new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCable, 8), new Object[] { "GPG", "IRI", "GPG", Character.valueOf('R'), Materials.matFluxDust.copy(), Character.valueOf('G'), Block.glass, Character.valueOf('I'), Item.ingotIron, Character.valueOf('P'), Block.pressurePlateIron });
		}
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
		
		if(ConfigurationHandler.ic2TEGlassInterchangeability) {
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
		
	}
	
	private static void addBigReactorsRecipes() {
		
		if(okayToTweakBigReactors) {
			
			if(ConfigurationHandler.steelReactorCasings) {
				ItemStack reactorPartStack = ((BlockReactorPart) BigReactors.blockReactorPart).getReactorCasingItemStack(); 
				reactorPartStack.stackSize = 4;
				GameRegistry.addRecipe(new ShapedOreRecipe(reactorPartStack, new Object[] { "ICI", "CUC", "ICI", 'I', "ingotSteel", 'C', "ingotGraphite", 'U', "ingotYellorium" }));
			}
			if(ConfigurationHandler.glassFuelRods) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BigReactors.blockYelloriumFuelRod, 1), new Object[] { "ICI", "GUG", "ICI", Character.valueOf('I'), BigReactors.blockReactorGlass, Character.valueOf('C'), "ingotIron", Character.valueOf('U'), "ingotYellorium" , Character.valueOf('G'), "ingotGraphite"}));
			}
			
		}
		
	}
	
	private static void addDARecipe()
	{
		if (okayToTweakDA)
		{
		 GameRegistry.addRecipe(new ItemStack(DimensionalAnchors.instance.block, 1, 0),
					"ded",
					"oIo",
					"gog",
					'd', Item.diamond,
					'e', Item.enderPearl,
					'o', Block.obsidian,
					'I', Block.blockIron,
					'g', Item.ingotGold
					);
		}
	}
	
	private static void registerAdditionalRecipes() {
		if(Loader.isModLoaded("gregtech_addon") && ConfigurationHandler.doPlatinumInCentrifuge) {
			GregTech_API.sRecipeAdder.addCentrifugeRecipe(OreDictionary.getOres("dustPlatinum").get(0), 0, OreDictionary.getOres("nuggetIridium").get(0), OreDictionary.getOres("dustSmallNickel").get(0), null, null, 3000);
		}
	}
		
	
}