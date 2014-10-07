package tppitweaks.recipetweaks.modTweaks;

import java.util.ArrayList;

import mekanism.common.Mekanism;
import mekanism.common.recipe.MekanismRecipe;
import mekanism.common.recipe.RecipeHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class MekanismTweaks
{
	@RecipeRemoval(requiredModids="Mekanism")
	public static void init()
	{
		if (ConfigurationHandler.harderDisassemblerRecipe)
		{
			TweakingRegistry.markItemForRecipeRemoval(Mekanism.AtomicDisassembler, -1, TweakingAction.CHANGED, "Changed to ensure", "balance with all other tools");
			TweakingRegistry.markItemForRecipeRemoval(Mekanism.AtomicAlloy, -1, TweakingAction.CHANGED, "Changed to further", "balance all of Mekanism");
		}
		if (ConfigurationHandler.disableCardboardBox)
		{
			TweakingRegistry.markItemForRecipeRemoval(Mekanism.CardboardBox, -1);
		}
		if (ConfigurationHandler.disableUniversalCables)
		{
			for(int i = 0; i < 4; i++) {
				TweakingRegistry.markItemForRecipeRemoval(Mekanism.PartTransmitter, i, TweakingAction.REMOVED, "Crashes");
			}
		}
		
		if (ConfigurationHandler.disableMiner || ConfigurationHandler.nerfMiner)
		{
			TweakingRegistry.markItemForRecipeRemoval(Mekanism.MachineBlock, 4, TweakingAction.CHANGED, "Changed to balance better", "with other quarry-like blocks");
		}
	}

	@RecipeAddition(requiredModids="Mekanism")
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
				RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("oreVinteum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustVinteum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("oreYellorite")).isEmpty() && !(dustOut = OreDictionary.getOres("dustYellorium")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("oreRuby")).isEmpty() && !(dustOut = OreDictionary.getOres("dustRuby")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("oreSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustSapphire")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("oreOlivine")).isEmpty() && !(dustOut = OreDictionary.getOres("dustOlivine")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			out.stackSize++;
			for (ItemStack i : oreIn)
				RecipeHandler.addEnrichmentChamberRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotYellorium")).isEmpty() && !(dustOut = OreDictionary.getOres("dustYellorium")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("gemRuby")).isEmpty() && !(dustOut = OreDictionary.getOres("dustRuby")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("gemSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustSapphire")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("gemGreenSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustGreenSapphire")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("gemOlivine")).isEmpty() && !(dustOut = OreDictionary.getOres("dustOlivine")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotPlatinum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustPlatinum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotElectrum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustElectrum")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		if (!(oreIn = OreDictionary.getOres("ingotInvar")).isEmpty() && !(dustOut = OreDictionary.getOres("dustInvar")).isEmpty())
		{
			ItemStack out = dustOut.get(0).copy();
			for (ItemStack i : oreIn)
				RecipeHandler.addCrusherRecipe(i.copy(), out);
		}

		RecipeHandler.addCrusherRecipe(new ItemStack(Items.bone), new ItemStack(Items.dye, 5, 15));
		RecipeHandler.addCrusherRecipe(new ItemStack(Blocks.red_flower), new ItemStack(Items.dye, 2, 1));
		RecipeHandler.addCrusherRecipe(new ItemStack(Blocks.yellow_flower), new ItemStack(Items.dye, 2, 11));
	}

	private static void doMaterialNerfs()
	{
		GameRegistry.addRecipe(new MekanismRecipe(new ItemStack(Mekanism.AtomicDisassembler), new Object[]{
			"AtA",
			"ADA",
			" o ",

			'D', new ItemStack(ModItems.tppiMaterial, 1, 1),
			't', new ItemStack(Mekanism.EnergyTablet, 1, 1),
			'o', "ingotRefinedObsidian",
			'A', Mekanism.AtomicAlloy
		}));

		GameRegistry.addRecipe(new MekanismRecipe(new ItemStack(Mekanism.AtomicAlloy), new Object[]{
			"aea",
			"ede",
			"aea",

			'a', Mekanism.EnrichedAlloy,
			'e', Mekanism.ElectrolyticCore,
			'd', Items.diamond
		}));

		GameRegistry.addRecipe(new MekanismRecipe(new ItemStack(ModItems.tppiMaterial, 1, 1), new Object[]{
			"tst",
			"eae",
			"tst",

			't', Mekanism.TeleportationCore,
			's', Mekanism.SpeedUpgrade,
			'e', Mekanism.EnergyUpgrade,
			'a', Mekanism.AtomicAlloy
		}));
	}

	private static void doMinerNerf()
	{
		ItemStack clump = new ItemStack(Mekanism.Clump);
		GameRegistry.addRecipe(new MekanismRecipe(new ItemStack(ModItems.tppiMaterial, 1, 3), 
			"012",
			"3c4",
			"567",
			
			'c', Mekanism.ControlCircuit,
			'0', setDamage(clump, 0),
			'1', setDamage(clump, 1),
			'2', setDamage(clump, 2),
			'3', setDamage(clump, 3),
			'4', setDamage(clump, 4),
			'5', setDamage(clump, 5),
			'6', setDamage(clump, 6),
			'7', setDamage(clump, 7)
		));
		
		GameRegistry.addRecipe(new MekanismRecipe(new ItemStack(Mekanism.MachineBlock, 1, 4), new Object[]{
			"AcA",
			"LRL",
			"MCM",

			'A', Mekanism.AtomicAlloy,
			'c', new ItemStack(ModItems.tppiMaterial, 1, 3),
			'L', new ItemStack(Mekanism.MachineBlock, 1, 15),
			'R', Mekanism.Robit,
			'M', new ItemStack(Mekanism.BasicBlock, 1, 8),
			'C', Mekanism.AtomicDisassembler
		}));
	}
	
	private static ItemStack setDamage(ItemStack stack, int dmg)
	{
		ItemStack newStack = stack.copy();
		newStack.setItemDamage(dmg);
		return newStack;
	}

	public static IIcon getCircuitIcon()
	{
		return Mekanism.ControlCircuit.getIconFromDamage(0);
	}
}
