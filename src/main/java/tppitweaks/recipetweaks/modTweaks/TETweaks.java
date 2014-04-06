package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.TPPITweaks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.RecipeRemoval;
import tppitweaks.recipetweaks.TweakingRegistry;
import tppitweaks.recipetweaks.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class TETweaks {

	@RecipeRemoval(requiredModids="ThermalExpansion")
	public static void init()
	{
		if (ConfigurationHandler.harderActivatorRecipe)
			TweakingRegistry.markItemForRecipeRemoval(thermalexpansion.block.TEBlocks.blockDevice.blockID, 2, TweakingAction.CHANGED, "Recipe requires steel", "to make this a later game item");
	}
	
	@RecipeAddition(requiredModids="ThermalExpansion")
	public static void addRecipes() {
		try {
			GameRegistry.addShapelessRecipe(new ItemStack(Item.paper, 3), new Object[] {thermalexpansion.item.TEItems.woodchips, thermalexpansion.item.TEItems.woodchips, thermalexpansion.item.TEItems.woodchips});
		}catch(Throwable t){
			TPPITweaks.logger.severe("Could not add paper recipe to pulverizer!");
			t.printStackTrace();
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
	}
	
	public static ItemStack getEnderium()
	{
		return thermalexpansion.item.TEItems.ingotEnderium.copy();
	}

	public static ItemStack getResonantCell()
	{
		return thermalexpansion.block.energycell.BlockEnergyCell.cellResonant.copy();
	}
}
