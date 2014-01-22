package tppitweaks.recipetweaks;

import java.util.ListIterator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.modTweaks.*;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeTweaks
{
	private static boolean okayToTweakIC2;
	private static boolean okayToTweakGT;
	private static boolean okayToTweakEnderStorage;
	private static boolean okayToTweakBigReactors;
	public static boolean okayToTweakDA;
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
		
		if (okayToTweakGT && okayToTweakIC2)
			GregtechTweaks.doStuff();
		
		if (okayToTweakExU)
			ExUTweaks.fixRecipes();
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
		
		if (okayToTweakDartCraft && okayToTweakIC2)
		{
			DCTweaks.removeRecipe(CraftingManager.getInstance().getRecipeList().listIterator());
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
		okayToTweakDartCraft = Loader.isModLoaded("DartCraft") && Loader.isModLoaded("IC2") && ConfigurationHandler.removeStupidEnergyCrystalRecipe;
		okayToTweakExU = Loader.isModLoaded("ExtraUtilities");
	}

	private static void initRemovableRecipesMap()
	{
		/*
		 * Value -1 for key means no metadata sensitivity. Value = metadata for
		 * metadata sensitivity.
		 */

		if (okayToTweakEnderStorage)
		{
			EnderStorageTweaks.init();
		}
		if (okayToTweakBigReactors)
		{
			initBigReactors();
		}
		if (okayToTweakDA)
		{
			DATweaks.init();
		}
		if (okayToTweakSFM)
		{
			SFMTweaks.init();
		}
		if (okayToTweakOpenBlocks)
		{
			OpenBlocksTweaks.init();
		}
		if (okayToTweakDartCraft && okayToTweakIC2)
		{
			DCTweaks.init();
		}
	}

	private static void initBigReactors()
	{
		if (ConfigurationHandler.steelReactorCasings)
		{
			TweakerBase.recipesToRemove.put(erogenousbeef.bigreactors.common.BigReactors.blockReactorPart.blockID, 0);
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			TweakerBase.recipesToRemove.put(erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod.blockID, -1);
		}		
	}

	private static boolean canRemoveRecipe(IRecipe r)
	{
		try
		{
			ItemStack output = r.getRecipeOutput();
			int removeableValueTest = TweakerBase.recipesToRemove.get(output.itemID);
			return removeableValueTest == -1 || removeableValueTest == output.getItemDamage();
		}
		catch (Throwable e)
		{
			return false;
		}
	}

	private static void addRevisedRecipes()
	{
		doOreDictTweaks();

		if (okayToTweakIC2)
			IC2Tweaks.registerOres();
		
		if (okayToTweakMagicalCrops)
			MagicropsTweaks.registerOres();
		
		if (okayToTweakEnderStorage)
			EnderStorageTweaks.addRecipes();
		
		if (okayToTweakBigReactors)
			addBigReactorsRecipes();
		
		if (okayToTweakDA)
			DATweaks.addRecipe();
		
		if (okayToTweakSFM)
			SFMTweaks.addRecipes();
		
		if (okayToTweakOpenBlocks)
			OpenBlocksTweaks.addRecipes();
		
		if (okayToTweakAM2)
			AM2Tweaks.addRecipes();
	}

	/**
	 * Adds aluminIUM oreDict registries to aluminUM as well
	 */
	private static void doOreDictTweaks()
	{
		for (ItemStack s : OreDictionary.getOres("dustAluminium"))
		{
			OreDictionary.registerOre("dustAluminum", s);
		}
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
}
