package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.block.ModBlocks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class SFMTweaks
{
	@RecipeRemoval(requiredModids={"appliedenergistics2", "StevesFactoryManager"})
	public static void init()
	{
		if (ConfigurationHandler.tweakSFM)
		{
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.sfmManager, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.sfmCable, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.sfmCableRelay, 8, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.sfmCableInput, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.sfmCableOutput, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.sfmCableIntake, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.sfmCableBreaker, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
		}
	}

	@RecipeAddition(requiredModids={"appliedenergistics2", "StevesFactoryManager"})
	public static void addRecipes()
	{
		if (ConfigurationHandler.tweakSFM)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.sfmManager,
			        "III",
			        "aRf",
			        "SSS",

			        'R', new ItemStack(ModItems.tppiMaterial),
			        'a', new ItemStack(ModItems.aeMaterial, 1, 44),
			        'f', new ItemStack(ModItems.aeMaterial, 1, 43),
			        'I', "ingotIron",
			        'S', "stone"
			).setMirrored(true));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.sfmCable, 8),
					 "GPG",
					 "IRI",
					 "GPG",

					 'R', new ItemStack(ModItems.aeMaterial, 1, 8),
					 'G', "blockGlass",
					 'I', "ingotIron",
					 'P', Blocks.heavy_weighted_pressure_plate
			));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.sfmCableRelay, 1, 8),
					    "fBf",
						"BIB",
						"fBf",

						'B', "blockLapis",
						'I', ModBlocks.sfmCableRelay,
						'f', new ItemStack(ModItems.aeMaterial, 1, 8)
			));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.sfmCableInput),
			            " r ",
						"rIr",
						" r ",

						'r', "dustRedstone",
						'I', ModBlocks.sfmCable
			));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.sfmCableOutput),
						"rtr",
						"rIr",
						"rrr",

						'r', "dustRedstone",
						't', new ItemStack(ModItems.aeMultipart, 1, 280),
						'I', ModBlocks.sfmCable
			));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.sfmCableIntake, 1, 0), ModBlocks.sfmCable, Blocks.hopper, Blocks.dropper, new ItemStack(ModItems.aeMultipart, 1, 440)));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.sfmCableIntake, 1, 8), new ItemStack(ModBlocks.sfmCableIntake, 1, 0), new ItemStack(ModItems.aeMaterial, 1, 23)));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.sfmCableBreaker), ModBlocks.sfmCable, Items.iron_pickaxe, Blocks.dispenser, new ItemStack(ModItems.aeMultipart, 1, 440)));
		}
	}
}

