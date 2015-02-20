package tppitweaks.recipetweaks.modTweaks;

import mekanism.common.Mekanism;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thermalexpansion.block.TEBlocks;
import thermalexpansion.block.cell.BlockCell;
import thermalexpansion.item.TEFlorbs;
import thermalexpansion.item.TEItems;
import thermalexpansion.util.crafting.FurnaceManager;
import thermalexpansion.util.crafting.PulverizerManager;
import thermalfoundation.item.TFItems;
import tppitweaks.TPPITweaks;
import tppitweaks.config.ConfigurationHandler;
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
			TweakingRegistry.markItemForRecipeRemoval(TEBlocks.blockDevice, 2, TweakingAction.CHANGED, "Recipe requires steel", "to make this a later game item");

		if (ConfigurationHandler.nerfTECaches)
		{
			TweakingRegistry.markItemForRecipeRemoval(TEBlocks.blockCache, 1, TweakingAction.CHANGED, "Added Bin to basic recipes.");
			TweakingRegistry.markItemForRecipeRemoval(TEBlocks.blockCache, 2, TweakingAction.CHANGED, "Added Bin to basic recipes.");
		}
		
		TweakingRegistry.markItemForRecipeRemoval(TEItems.sawdustCompressed.getItem(), TEItems.sawdustCompressed.getItemDamage(), TweakingAction.NOTE, "Recipe edited to be", "ore dictionary.");
		TweakingRegistry.markItemForRecipeRemoval(TEFlorbs.florb.getItem(), TEFlorbs.florb.getItemDamage(), TweakingAction.NOTE, "Recipe edited to be", "ore dictionary");
		TweakingRegistry.markItemForRecipeRemoval(TEFlorbs.florbMagmatic.getItem(), TEFlorbs.florbMagmatic.getItemDamage(), TweakingAction.NOTE, "Recipe edited to be", "ore dictionary");
	}

	/* @formatter:off */
	@RecipeAddition(requiredModids="ThermalExpansion")
	public static void addRecipes() 
	{
		try 
		{
			GameRegistry.addShapelessRecipe(new ItemStack(Items.paper, 3), new Object[] {TEItems.sawdust.copy(), TEItems.sawdust.copy(), TEItems.sawdust.copy()});
		}
		catch(Throwable t)
		{
			TPPITweaks.logger.error("Could not add paper recipe to pulverizer!");
			t.printStackTrace();
		}
		
		if (!OreDictionary.getOres("itemRubber").isEmpty())
		{
			FurnaceManager.addOreDictRecipe("resinIC2", OreDictionary.getOres("itemRubber").get(0).copy());
		}
				
		if (ConfigurationHandler.harderActivatorRecipe)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(TEBlocks.blockDevice, 1, 2), new Object[]{
				"scs",
				"tpt",
				"sns",

				's', OreDictionary.getOres("ingotSteel").isEmpty() ? "ingotIron" : "ingotSteel",
				'p', Blocks.piston,
				't', "ingotTin",
				'c', Blocks.chest,
				'n', thermalexpansion.item.TEItems.pneumaticServo.copy()
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
		
		if (ConfigurationHandler.nerfTECaches)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(TEBlocks.blockCache, 1, 1), new Object[]{
				" t ",
				"tbt",
				" t ",
				't', "ingotTin",
				'b', Loader.isModLoaded("Mekanism") ? new ItemStack(Mekanism.BasicBlock, 1, 6) : "logWood"
			}));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(TEBlocks.blockCache, 1, 2), new Object[]{
				" i ",
				"ici",
				" i ",
				'i', "ingotInvar",
				'c', new ItemStack(TEBlocks.blockCache, 1, 1)
			}));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(TEBlocks.blockCache, 1, 2), new Object[]{
				"iti",
				"tbt",
				"iti",
				'i', "ingotInvar",
				't', "ingotTin",
				'b', Loader.isModLoaded("Mekanism") ? new ItemStack(Mekanism.BasicBlock, 1, 6) : "logWood"
			}));
			
		}
		
		OreDictionary.registerOre("dustWood", TEItems.sawdust.copy());
		OreDictionary.registerOre("itemSlag", TEItems.slag.copy());
		OreDictionary.registerOre("magmaCream", Items.magma_cream);
		GameRegistry.addRecipe(new ShapedOreRecipe(TEItems.sawdustCompressed.copy(),
			"sss",
			"s s",
			"sss",
				
			's', "dustWood"
		));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(TEFlorbs.florb.copy(), "dustWood", "itemSlag", "slimeball"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(TEFlorbs.florbMagmatic.copy(), "dustWood", "itemSlag", "slimeball", "dustBlaze"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(TEFlorbs.florbMagmatic.copy(), "dustWood", "itemSlag", "magmaCream"));
	}
	/* @formatter:on */

	public static ItemStack getEnderium()
	{
		return TFItems.ingotEnderium.copy();
	}

	public static ItemStack getResonantCell()
	{
		return BlockCell.cellResonant.copy();
	}

    public static void addPulverizerRecipe(int power, ItemStack in, ItemStack out, ItemStack secondary, int chance)
    {
        PulverizerManager.addRecipe(power, in, out, secondary, chance);        
    }
}
