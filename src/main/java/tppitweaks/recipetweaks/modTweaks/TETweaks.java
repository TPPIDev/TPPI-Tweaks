package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cofh.thermalexpansion.util.crafting.FurnaceManager;
import cofh.thermalexpansion.util.crafting.PulverizerManager;
import tppitweaks.block.ModBlocks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class TETweaks
{

	@RecipeRemoval(requiredModids = "ThermalExpansion")
	public static void init()
	{
		if (ConfigurationHandler.harderActivatorRecipe)
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.teDevice, 2, TweakingAction.CHANGED, "Recipe requires steel", "to make this a later game item");

		if (ConfigurationHandler.nerfTECaches)
		{
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.teCache, 1, TweakingAction.CHANGED, "Added Bin to basic recipes.");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.teCache, 2, TweakingAction.CHANGED, "Added Bin to basic recipes.");
		}

		TweakingRegistry.markItemForRecipeRemoval(ModItems.teMaterial, 513, TweakingAction.NOTE, "Recipe edited to be", "ore dictionary.");
		TweakingRegistry.markItemForRecipeRemoval(ModItems.teFlorb, 0, TweakingAction.NOTE, "Recipe edited to be", "ore dictionary");
		TweakingRegistry.markItemForRecipeRemoval(ModItems.teFlorb, 1, TweakingAction.NOTE, "Recipe edited to be", "ore dictionary");
	}

	/* @formatter:off */
	@RecipeAddition(requiredModids="ThermalExpansion")
	public static void addRecipes()
	{
        OreDictionary.registerOre("dustWood", new ItemStack(ModItems.teMaterial, 1, 512));
        OreDictionary.registerOre("itemSlag", new ItemStack(ModItems.teMaterial, 1, 514));
        OreDictionary.registerOre("magmaCream", Items.magma_cream);

        GameRegistry.addShapelessRecipe(new ItemStack(Items.paper, 3), "dustWood", "dustWood", "dustWood");

		if (!OreDictionary.getOres("itemRubber").isEmpty())
		{
			FurnaceManager.addOreDictRecipe("resinIC2", OreDictionary.getOres("itemRubber").get(0).copy());
		}

		if (ConfigurationHandler.harderActivatorRecipe)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.teDevice, 1, 2),
				"scs",
				"tpt",
				"sns",

				's', OreDictionary.getOres("ingotSteel").isEmpty() ? "ingotIron" : "ingotSteel",
				'p', Blocks.piston,
				't', "ingotTin",
				'c', Blocks.chest,
				'n', ModItems.teMaterial
			));
		}

		if (OreDictionary.getOres("dustRuby").size() != 0)
			PulverizerManager.addIngotNameToDustRecipe(2400, "gemRuby", OreDictionary.getOres("dustRuby").get(0));
		if (OreDictionary.getOres("dustTinyWood").size() != 0)
		{
			ItemStack res = OreDictionary.getOres("dustTinyWood").get(0).copy();
			res.stackSize = 2;
			PulverizerManager.addIngotNameToDustRecipe(1000, "stickWood", res);
		}

		if (ConfigurationHandler.nerfTECaches)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.teCache, 1, 1),
				" t ",
				"tbt",
				" t ",
				't', "ingotTin",
				'b', Loader.isModLoaded("Mekanism") ? new ItemStack(ModBlocks.mekBasicBlock, 1, 6) : "logWood"
			));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.teCache, 1, 2),
				" i ",
				"ici",
				" i ",
				'i', "ingotInvar",
				'c', new ItemStack(ModBlocks.teCache, 1, 1)
			));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.teCache, 1, 2),
				"iti",
				"tbt",
				"iti",
				'i', "ingotInvar",
				't', "ingotTin",
				'b', Loader.isModLoaded("Mekanism") ? new ItemStack(ModBlocks.mekBasicBlock, 1, 6) : "logWood"
			));

		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.teMaterial, 1, 513),
			"sss",
			"s s",
			"sss",

			's', "dustWood"
		));

		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.teFlorb, "dustWood", "itemSlag", "slimeball"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.teFlorb, 1, 1), "dustWood", "itemSlag", "slimeball", "dustBlaze"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.teFlorb, 1, 1), "dustWood", "itemSlag", "magmaCream"));
	}
	/* @formatter:on */
}
