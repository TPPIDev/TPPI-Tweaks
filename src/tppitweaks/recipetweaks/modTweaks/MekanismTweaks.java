package tppitweaks.recipetweaks.modTweaks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class MekanismTweaks
{
	public static void init()
	{
		if (ConfigurationHandler.harderDisassemblerRecipe)
		{
			TweakerBase.markItemForRecipeRemoval(((Item) mekanism.common.Mekanism.AtomicDisassembler).itemID, -1);
			TweakerBase.markItemForRecipeRemoval(((Item) mekanism.common.Mekanism.AtomicCore).itemID, -1);
		}
		if (ConfigurationHandler.disableCardboardBox)
		{
			TweakerBase.markItemForRecipeRemoval(mekanism.common.Mekanism.cardboardBoxID, -1);
		}
		if (ConfigurationHandler.disableMiner || ConfigurationHandler.nerfMiner)
		{
			TweakerBase.markItemForRecipeRemoval(mekanism.common.Mekanism.machineBlockID, 4);
		}
	}

	public static void addRecipes()
	{
		if (ConfigurationHandler.harderDisassemblerRecipe)
			doMaterialNerfs();

		if (ConfigurationHandler.nerfMiner)
			doMinerNerf();

		ArrayList<ItemStack> oreIn = null, dustOut = null;

		if (!(oreIn = OreDictionary.getOres("oreAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("oreVinteum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustVinteum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("oreYellorite")).isEmpty() && !(dustOut = OreDictionary.getOres("dustYellorium")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("oreRuby")).isEmpty() && !(dustOut = OreDictionary.getOres("dustRuby")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("oreSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustSapphire")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("oreOlivine")).isEmpty() && !(dustOut = OreDictionary.getOres("dustOlivine")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotYellorium")).isEmpty() && !(dustOut = OreDictionary.getOres("dustYellorium")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("gemRuby")).isEmpty() && !(dustOut = OreDictionary.getOres("dustRuby")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("gemSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustSapphire")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("gemGreenSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustGreenSapphire")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("gemOlivine")).isEmpty() && !(dustOut = OreDictionary.getOres("dustOlivine")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotPlatinum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustPlatinum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotElectrum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustElectrum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotInvar")).isEmpty() && !(dustOut = OreDictionary.getOres("dustInvar")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				mekanism.common.recipe.RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		mekanism.common.recipe.RecipeHandler.addCrusherRecipe(new ItemStack(Item.bone), new ItemStack(Item.dyePowder, 5, 15));
		mekanism.common.recipe.RecipeHandler.addCrusherRecipe(new ItemStack(Block.plantRed), new ItemStack(Item.dyePowder, 2, 1));
		mekanism.common.recipe.RecipeHandler.addCrusherRecipe(new ItemStack(Block.plantYellow), new ItemStack(Item.dyePowder, 2, 11));
	}

	private static void doMaterialNerfs()
	{
		GameRegistry.addRecipe(new mekanism.common.recipe.MekanismRecipe(new ItemStack(mekanism.common.Mekanism.AtomicDisassembler), new Object[]{
			"AtA",
			"ADA",
			" o ",

			'D', new ItemStack(ModItems.tppiMaterial, 1, 2),
			't', new ItemStack(mekanism.common.Mekanism.EnergyTablet, 1, 1),
			'o', "ingotRefinedObsidian",
			'A', mekanism.common.Mekanism.AtomicCore
		}));

		GameRegistry.addRecipe(new mekanism.common.recipe.MekanismRecipe(new ItemStack(mekanism.common.Mekanism.AtomicCore), new Object[]{
			"aea",
			"ede",
			"aea",

			'a', mekanism.common.Mekanism.EnrichedAlloy,
			'e', mekanism.common.Mekanism.ElectrolyticCore,
			'd', Item.diamond
		}));

		GameRegistry.addRecipe(new mekanism.common.recipe.MekanismRecipe(new ItemStack(ModItems.tppiMaterial, 1, 2), new Object[]{
			"tst",
			"eae",
			"tst",

			't', mekanism.common.Mekanism.TeleportationCore,
			's', mekanism.common.Mekanism.SpeedUpgrade,
			'e', mekanism.common.Mekanism.EnergyUpgrade,
			'a', mekanism.common.Mekanism.AtomicCore
		}));
	}

	private static void doMinerNerf()
	{
		GameRegistry.addRecipe(new mekanism.common.recipe.MekanismRecipe(new ItemStack(mekanism.common.Mekanism.MachineBlock, 1, 4), new Object[]{
			"AcA",
			"LRL",
			"MCM",

			'A', mekanism.common.Mekanism.AtomicCore,
			'c', mekanism.common.Mekanism.ControlCircuit,
			'L', new ItemStack(mekanism.common.Mekanism.MachineBlock, 1, 15),
			'R', mekanism.common.Mekanism.Robit,
			'M', new ItemStack(mekanism.common.Mekanism.BasicBlock, 1, 8),
			'C', mekanism.common.Mekanism.AtomicDisassembler
		}));
	}
}
