package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thermalexpansion.fluid.TEFluids;
import thermalexpansion.item.TEItems;
import thermalexpansion.util.crafting.FurnaceManager;
import thermalexpansion.util.crafting.PulverizerManager;
import tppitweaks.TPPITweaks;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class TETweaks
{

	@RecipeRemoval(requiredModids = "ThermalExpansion")
	public static void init()
	{
		if (ConfigurationHandler.harderActivatorRecipe)
			TweakingRegistry.markItemForRecipeRemoval(thermalexpansion.block.TEBlocks.blockDevice.blockID, 2, TweakingAction.CHANGED, "Recipe requires steel", "to make this a later game item");

		TweakingRegistry.markItemForRecipeRemoval(TEItems.sawdustCompressed.itemID, TEItems.sawdustCompressed.getItemDamage(), TweakingAction.NOTE, "Recipe edited to be", "ore dictionary.");
		TweakingRegistry.markItemForRecipeRemoval(TEFluids.florb.itemID, TEFluids.florb.getItemDamage(), TweakingAction.NOTE, "Recipe edited to be", "ore dictionary");
		TweakingRegistry.markItemForRecipeRemoval(TEFluids.florbMagmatic.itemID, TEFluids.florbMagmatic.getItemDamage(), TweakingAction.NOTE, "Recipe edited to be", "ore dictionary");
	}

	/* @formatter:off */
	@RecipeAddition(requiredModids="ThermalExpansion")
	public static void addRecipes() 
	{
		try 
		{
			GameRegistry.addShapelessRecipe(new ItemStack(Item.paper, 3), new Object[] {thermalexpansion.item.TEItems.woodchips, thermalexpansion.item.TEItems.woodchips, thermalexpansion.item.TEItems.woodchips});
		}
		catch(Throwable t)
		{
			TPPITweaks.logger.severe("Could not add paper recipe to pulverizer!");
			t.printStackTrace();
		}
		
		if (!OreDictionary.getOres("itemRubber").isEmpty())
		{
			FurnaceManager.addOreDictRecipe("resinIC2", OreDictionary.getOres("itemRubber").get(0).copy());
		}
				
		if (ConfigurationHandler.harderActivatorRecipe)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(thermalexpansion.block.TEBlocks.blockDevice.blockID, 1, 2), new Object[]{
				"scs",
				"tpt",
				"sns",

				's', OreDictionary.getOres("ingotSteel").isEmpty() ? "ingotIron" : "ingotSteel",
				'p', Block.pistonBase,
				't', "ingotTin",
				'c', Block.chest,
				'n', thermalexpansion.item.TEItems.pneumaticServo
			}));
		}
		
		if (OreDictionary.getOres("dustRuby").size() != 0)
			thermalexpansion.util.crafting.PulverizerManager.addIngotNameToDustRecipe(2400, "gemRuby", OreDictionary.getOres("dustRuby").get(0));
		if (OreDictionary.getOres("dustTinyWood").size() != 0)
		{
			ItemStack res = OreDictionary.getOres("dustTinyWood").get(0).copy();
			res.stackSize = 2;
			PulverizerManager.addIngotNameToDustRecipe(1000, "stickWood", res);
		}
		
		OreDictionary.registerOre("dustWood", TEItems.sawdust);
		OreDictionary.registerOre("itemSlag", TEItems.slag);
		OreDictionary.registerOre("magmaCream", Item.magmaCream);
		GameRegistry.addRecipe(new ShapedOreRecipe(TEItems.sawdustCompressed,
			"sss",
			"s s",
			"sss",
				
			's', "dustWood"
		));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(TEFluids.florb, "dustWood", "itemSlag", "slimeball"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(TEFluids.florbMagmatic, "dustWood", "itemSlag", "slimeball", "dustBlaze"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(TEFluids.florbMagmatic, "dustWood", "itemSlag", "magmaCream"));
	}
	/* @formatter:on */

	public static ItemStack getEnderium()
	{
		return thermalexpansion.item.TEItems.ingotEnderium.copy();
	}

	public static ItemStack getResonantCell()
	{
		return thermalexpansion.block.energycell.BlockEnergyCell.cellResonant.copy();
	}
}
