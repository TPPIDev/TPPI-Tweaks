package tppitweaks.recipetweaks.modTweaks;

import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
	}

	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(mekanism.common.Mekanism.AtomicDisassembler), new Object[]{
			"AtA",
			"ADA",
			" o ",

			'D', new ItemStack(ModItems.tppiMaterial, 1, 2),
			't', mekanism.common.Mekanism.EnergyTablet,
			'o', "ingotRefinedObsidian",
			'A', mekanism.common.Mekanism.AtomicCore
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(mekanism.common.Mekanism.AtomicCore), new Object[]{
			"aea",
			"ede",
			"aea",

			'a', mekanism.common.Mekanism.EnrichedAlloy,
			'e', mekanism.common.Mekanism.ElectrolyticCore,
			'd', Item.diamond
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 2), new Object[]{
			"tst",
			"eae",
			"tst",

			't', mekanism.common.Mekanism.TeleportationCore,
			's', mekanism.common.Mekanism.SpeedUpgrade,
			'e', mekanism.common.Mekanism.EnergyUpgrade,
			'a', mekanism.common.Mekanism.AtomicCore
		}));
	}
}
