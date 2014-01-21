package tppitweaks.recipetweaks;

//import gregtechmod.api.GregTech_API;
//import gregtechmod.api.enums.GT_Items;
//import ic2.core.Ic2Items;
//import ic2.core.block.machine.tileentity.TileEntityOreWashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;


//import bluedart.core.recipes.ShapedDartCrafting;
//import magicalcrops.mod_mCrops;
//import mods.immibis.chunkloader.DimensionalAnchors;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
//import openblocks.OpenBlocks;
//import thermalexpansion.block.TEBlocks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
//import am2.blocks.BlocksCommonProxy;
//import am2.items.ItemsCommonProxy;
//import appeng.api.Materials;
//import codechicken.enderstorage.EnderStorage;
//import codechicken.enderstorage.api.EnderStorageManager;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
//import erogenousbeef.bigreactors.common.BigReactors;
//import erogenousbeef.bigreactors.common.block.BlockReactorPart;
//import gregtechmod.api.util.GT_ModHandler.ThermalExpansion;

public class RecipeTweaks
{

	private static HashMap<Integer, Integer> recipesToRemove;
	private static boolean okayToTweakIC2;
	private static boolean okayToTweakGT;
	private static boolean okayToTweakEnderStorage;
	private static boolean okayToTweakBigReactors;
	private static boolean okayToTweakDA;
	private static boolean okayToTweakSFM;
	private static boolean okayToTweakOpenBlocks;
	private static boolean okayToTweakAM2;
	private static boolean okayToTweakMagicalCrops;
	private static boolean okayToTweakDartCraft;
	private static boolean okayToTweakExU;

	public static void doRecipeTweaks()
	{

		checkWhatWeCanTweak();
		initRemovableRecipesMap();
		removeSomeRecipes();
		addRevisedRecipes();
		
		if (okayToTweakGT)
			registerAdditionalRecipes();
		
		if (okayToTweakExU)
			fixExURecipes();
	}

	@SuppressWarnings("unchecked")
	private static void fixExURecipes()
	{
		if (Loader.isModLoaded("ExtraUtilities"))
		{
			Iterator<IRecipe> iter = CraftingManager.getInstance().getRecipeList().listIterator();
			while (iter.hasNext())
			{
				IRecipe recipe = iter.next();

				ItemStack stack = recipe.getRecipeOutput();
				if (stack != null && stack.getItem() == extrautils.ExtraUtils.unstableIngot && stack.stackSize == 9)
				{
					iter.remove();
				}
				if (recipe != null && stack != null && stack.getItem().itemID == extrautils.ExtraUtils.decorative1Id && stack.getItemDamage() == 1)
				{
					ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;
					if (shapedRecipe.recipeItems.length > 0 && shapedRecipe.recipeItems[0] != null && recipe instanceof ShapedRecipes
							&& ((ItemStack) shapedRecipe.recipeItems[0]).getItem() == extrautils.ExtraUtils.unstableIngot)
					{
						iter.remove();
					}
				}
			}

			GameRegistry.addShapelessRecipe(new ItemStack(extrautils.ExtraUtils.unstableIngot, 9), new ItemStack(extrautils.ExtraUtils.decorative1, 1, 5));
			GameRegistry.addRecipe(new ItemStack(extrautils.ExtraUtils.decorative1, 1, 5), new Object[] { "iii", "iii", "iii",

			'i', new ItemStack(extrautils.ExtraUtils.unstableIngot) });
		}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private static void removeSomeRecipes()
	{

		ListIterator<IRecipe> iterator = CraftingManager.getInstance().getRecipeList().listIterator();
		ItemStack recipeResult = null;
		while (iterator.hasNext())
		{
			IRecipe r = iterator.next();
			if (canRemoveRecipe(r))
			{
				iterator.remove();
			}
		}

	}

	private static void checkWhatWeCanTweak()
	{
		okayToTweakIC2 = Loader.isModLoaded("IC2");
		okayToTweakGT = Loader.isModLoaded("gregtech_addon");
		okayToTweakEnderStorage = Loader.isModLoaded("EnderStorage") && Loader.isModLoaded("ThermalExpansion");
		okayToTweakBigReactors = Loader.isModLoaded("BigReactors") && !OreDictionary.getOres("ingotSteel").isEmpty()
				&& (ConfigurationHandler.steelReactorCasings || ConfigurationHandler.glassFuelRods);
		okayToTweakDA = Loader.isModLoaded("DimensionalAnchors") && ConfigurationHandler.tweakDA;
		okayToTweakSFM = Loader.isModLoaded("AppliedEnergistics") && Loader.isModLoaded("StevesFactoryManager") && ConfigurationHandler.tweakSFM;
		okayToTweakOpenBlocks = Loader.isModLoaded("OpenBlocks") && ConfigurationHandler.eloraamBreakersAndDeployers;
		okayToTweakAM2 = Loader.isModLoaded("arsmagica2") && ConfigurationHandler.tweakAM2;
		okayToTweakMagicalCrops = Loader.isModLoaded("magicalcrops") && ConfigurationHandler.registerMagicalCropsOre;
		okayToTweakDartCraft = Loader.isModLoaded("DartCraft") && ConfigurationHandler.removeStupidEnergyCrystalRecipe;
		okayToTweakExU = Loader.isModLoaded("ExtraUtilities");
	}

	private static void initRemovableRecipesMap()
	{

		recipesToRemove = new HashMap<Integer, Integer>();

		/*
		 * Value -1 for key means no metadata sensitivity. Value = metadata for
		 * metadata sensitivity.
		 */

		if (okayToTweakEnderStorage)
		{
			initEnderStorage();
		}
		if (okayToTweakBigReactors)
		{
			initBigReactors();
		}
		if (okayToTweakDA)
		{
			initDA();
		}
		if (okayToTweakSFM)
		{
			initSFM();
		}
		if (okayToTweakOpenBlocks)
		{
			initOB();
		}
	}

	private static void initEnderStorage()
	{
		recipesToRemove.put(((Block) codechicken.enderstorage.EnderStorage.blockEnderChest).blockID, -1);
		recipesToRemove.put(((Item) codechicken.enderstorage.EnderStorage.itemEnderPouch).itemID, -1);		
	}

	private static void initOB()
	{
		recipesToRemove.put(((Block) openblocks.OpenBlocks.Blocks.blockBreaker).blockID, -1);
		recipesToRemove.put(((Block) openblocks.OpenBlocks.Blocks.blockPlacer).blockID, -1);
	}

	private static void initSFM()
	{
		recipesToRemove.put(((Block) vswe.stevesfactory.blocks.Blocks.blockManager).blockID, -1);
		recipesToRemove.put(((Block) vswe.stevesfactory.blocks.Blocks.blockCable).blockID, -1);	
	}

	private static void initDA()
	{
		recipesToRemove.put(((Block) mods.immibis.chunkloader.DimensionalAnchors.instance.block).blockID, -1);
	}

	private static void initBigReactors()
	{
		if (ConfigurationHandler.steelReactorCasings)
		{
			recipesToRemove.put(erogenousbeef.bigreactors.common.BigReactors.blockReactorPart.blockID, 0);
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			recipesToRemove.put(erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod.blockID, -1);
		}		
	}

	private static boolean canRemoveRecipe(IRecipe r)
	{
		try
		{
			ItemStack output = r.getRecipeOutput();
			if (r instanceof bluedart.core.recipes.ShapedDartCrafting && output.itemID == ic2.core.Ic2Items.energyCrystal.itemID)
			{
				return okayToTweakDartCraft;
			}
			else if (output.itemID != ic2.core.Ic2Items.energyCrystal.itemID)
			{
				int removeableValueTest = recipesToRemove.get(output.itemID);
				return removeableValueTest == -1 || removeableValueTest == output.getItemDamage();
			}
		}
		catch (Throwable e)
		{
			return false;
		}
		return false;
	}

	private static void addRevisedRecipes()
	{
		if (okayToTweakIC2)
			doOreDictTweaks();
		
		if (okayToTweakMagicalCrops)
			doMagicropsRegistration();
		
		if (okayToTweakEnderStorage)
			addEnderStorageRecipes();
		
		if (okayToTweakBigReactors)
			addBigReactorsRecipes();
		
		if (okayToTweakDA)
			addDARecipe();
		
		if (okayToTweakSFM)
			addSFMRecipes();
		
		if (okayToTweakOpenBlocks)
			addOpenBlocksRecipes();
		
		if (okayToTweakAM2)
			addAM2Recipes();
	}

	private static void addAM2Recipes()
	{
		if (okayToTweakAM2)
		{
			GameRegistry.addShapedRecipe(new ItemStack(am2.items.ItemsCommonProxy.spawnEgg, 1, 12), new Object[] { "CCC", "CPC", "CCC", 'C', new ItemStack(am2.items.ItemsCommonProxy.essence, 1, 5), 'P',
					new ItemStack(am2.blocks.BlocksCommonProxy.aum) });
		}
	}

	private static void addOpenBlocksRecipes()
	{
		if (okayToTweakOpenBlocks)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((openblocks.OpenBlocks.Blocks.blockBreaker), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Item.pickaxeIron, 'P',
					Block.pistonBase, 'R', Item.redstone }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((openblocks.OpenBlocks.Blocks.blockPlacer), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Block.chest, 'P',
					Block.pistonBase, 'R', Item.redstone }));
		}
	}

	private static void addSFMRecipes()
	{
		if (okayToTweakSFM)
		{
			GameRegistry.addRecipe(new ItemStack(vswe.stevesfactory.blocks.Blocks.blockManager), new Object[] { "III", "IRI", "SPS", Character.valueOf('R'), new ItemStack(ModItems.tppiMaterial),
					Character.valueOf('P'), appeng.api.Materials.matConversionMatrix.copy(), Character.valueOf('I'), Item.ingotIron, Character.valueOf('S'), Block.stone });
			GameRegistry.addRecipe(
					new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCable, 8),
					new Object[] { "GPG", "IRI", "GPG", Character.valueOf('R'), appeng.api.Materials.matFluxDust.copy(), Character.valueOf('G'), Block.glass, Character.valueOf('I'), Item.ingotIron,
							Character.valueOf('P'), Block.pressurePlateIron });
		}
	}

	private static void addEnderStorageRecipes()
	{
		if (okayToTweakEnderStorage)
		{

			Object chestEnderElement = ConfigurationHandler.enderChestTesseract ? thermalexpansion.block.TEBlocks.blockTesseract : Item.enderPearl;
			Object pouchEnderElement = ConfigurationHandler.enderPouchTesseract ? thermalexpansion.block.TEBlocks.blockTesseract : Item.enderPearl;
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

	private static void doOreDictTweaks()
	{

		if (ConfigurationHandler.ic2TEGlassInterchangeability)
		{
			if (Loader.isModLoaded("IC2") && OreDictionary.getOreID(ic2.core.Ic2Items.reinforcedGlass) == -1)
			{
				OreDictionary.registerOre("glassReinforced", ic2.core.Ic2Items.reinforcedGlass);
			}
			for (ItemStack stack : OreDictionary.getOres("glassReinforced"))
			{
				OreDictionary.registerOre("glassHardened", stack);
			}
			for (ItemStack stack : OreDictionary.getOres("glassHardened"))
			{
				OreDictionary.registerOre("glassReinforced", stack);
			}
		}

		// British fix
		for (ItemStack s : OreDictionary.getOres("dustAluminium"))
		{
			OreDictionary.registerOre("dustAluminum", s);
		}
	}
	
	private static void doMagicropsRegistration()
	{
		OreDictionary.registerOre("oreMCropsEssence", new ItemStack(magicalcrops.mod_mCrops.BlockOreEssence));
		OreDictionary.registerOre("oreMCropsNetherEssence", new ItemStack(magicalcrops.mod_mCrops.BlockOreEssenceNether));
	}

	private static void addBigReactorsRecipes()
	{

		if (okayToTweakBigReactors)
		{

			if (ConfigurationHandler.steelReactorCasings)
			{
				ItemStack reactorPartStack = ((erogenousbeef.bigreactors.common.block.BlockReactorPart) erogenousbeef.bigreactors.common.BigReactors.blockReactorPart).getReactorCasingItemStack();
				reactorPartStack.stackSize = 4;
				GameRegistry.addRecipe(new ShapedOreRecipe(reactorPartStack, new Object[] { "ICI", "CUC", "ICI", 'I', "ingotSteel", 'C', "ingotGraphite", 'U', "ingotYellorium" }));
			}
			if (ConfigurationHandler.glassFuelRods)
			{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod, 1), new Object[] { "ICI", "GUG", "ICI", Character.valueOf('I'),
					erogenousbeef.bigreactors.common.BigReactors.blockReactorGlass, Character.valueOf('C'), "ingotIron", Character.valueOf('U'), "ingotYellorium", Character.valueOf('G'), "ingotGraphite" }));
			}

		}

	}

	private static void addDARecipe()
	{
		if (okayToTweakDA)
		{
			GameRegistry.addRecipe(new ItemStack(mods.immibis.chunkloader.DimensionalAnchors.instance.block, 1, 0), "ded", "oIo", "gog", 'd', Item.diamond, 'e', Item.enderPearl, 'o', Block.obsidian, 'I', Block.blockIron,
					'g', Item.ingotGold);
		}
	}

	private static void registerAdditionalRecipes()
	{

		if (ConfigurationHandler.addOsmiumToOreWasher && Loader.isModLoaded("IC2") && !OreDictionary.getOres("dustImpureOsmium").isEmpty() && !OreDictionary.getOres("dustOsmium").isEmpty())
		{
			ic2.core.block.machine.tileentity.TileEntityOreWashing.addRecipe("dustImpureOsmium", 1, 1000, new ItemStack[] { OreDictionary.getOres("dustOsmium").get(0), ic2.core.Ic2Items.stoneDust });
		}

		if (Loader.isModLoaded("gregtech_addon") && ConfigurationHandler.doPlatinumInCentrifuge)
		{
			gregtechmod.api.GregTech_API.sRecipeAdder.addCentrifugeRecipe(OreDictionary.getOres("dustPlatinum").get(0), 0, OreDictionary.getOres("nuggetIridium").get(0),
					OreDictionary.getOres("dustSmallNickel").get(0), null, null, 3000);
		}
		if (Loader.isModLoaded("gregtech_addon") && ConfigurationHandler.addLapisDustMortarRecipes)
		{
			for (ItemStack s : OreDictionary.getOres("dustLapis"))
			{
				GameRegistry.addRecipe(new ShapelessOreRecipe(s, new Object[] { gregtechmod.api.enums.GT_Items.Tool_Mortar_Iron.getWildcard(1L, new Object[0]), new ItemStack(Item.dyePowder, 1, 4) }));
				GameRegistry.addRecipe(new ShapelessOreRecipe(s, new Object[] { gregtechmod.api.enums.GT_Items.Tool_Mortar_Wood.getWildcard(1L, new Object[0]), new ItemStack(Item.dyePowder, 1, 4) }));
			}

		}

		if (Loader.isModLoaded("gregtech_addon") && Loader.isModLoaded("TConstruct") && ConfigurationHandler.tinkersAluminumPlates)
		{
			int id = OreDictionary.getOres("ingotCobalt").get(0).itemID;
			for (ItemStack s : OreDictionary.getOres("ingotAluminum"))
			{
				if (s.itemID == id)
				{
					gregtechmod.api.GregTech_API.sRecipeAdder.addBenderRecipe(s, OreDictionary.getOres("plateAluminium").get(0), 52, 24);
				}
			}
		}
		if (Loader.isModLoaded("gregtech_addon") && Loader.isModLoaded("TConstruct") && ConfigurationHandler.tinkersAluminumOreInGTMachines)
		{
			HashSet<Integer> okIds = new HashSet<Integer>();
			for (ItemStack s : OreDictionary.getOres("oreCobalt"))
			{
				okIds.add(s.itemID);
			}

			for (ItemStack s : OreDictionary.getOres("oreAluminum"))
			{
				if (okIds.contains(s.itemID))
				{
					ItemStack dust = OreDictionary.getOres("dustAluminium").get(0).copy();
					dust.stackSize = 2;
					gregtechmod.api.GregTech_API.sRecipeAdder.addGrinderRecipe(s, ic2.core.Ic2Items.waterCell, dust, OreDictionary.getOres("dustSmallBauxite").get(0), OreDictionary.getOres("dustSmallBauxite").get(0),
							ic2.core.Ic2Items.cell);
				}
			}
		}
	}

}
