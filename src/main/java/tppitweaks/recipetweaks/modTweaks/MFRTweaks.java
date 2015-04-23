package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class MFRTweaks
{
	@RecipeRemoval(requiredModids="MineFactoryReloaded")
	public static void init()
	{
        Block unifier = GameRegistry.findBlock("MineFactoryReloaded", "machine.1");
        Item plasticCup = GameRegistry.findItem("MineFactoryReloaded", "plastic.cup");

		if (ConfigurationHandler.buffUnifierRecipe)
			TweakingRegistry.markItemForRecipeRemoval(unifier, 8, TweakingAction.CHANGED, "Cheapened for use", "in JABBA barrels");
		if (ConfigurationHandler.disablePlasticCups)
			TweakingRegistry.markItemForRecipeRemoval(plasticCup, -1, TweakingAction.REMOVED, "Has liquid dupes.");
	}
	
	@RecipeAddition(requiredModids="MineFactoryReloaded")
	public static void addRecipes()
	{
        Block unifier = GameRegistry.findBlock("MineFactoryReloaded", "machine.1");
        Item machineBase = GameRegistry.findItem("MineFactoryReloaded", "machineblock");

		if (ConfigurationHandler.buffUnifierRecipe)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machineBase, 3),
					"RRR",
					"SSS",

					'R', "sheetPlastic",
					'S', "stone"
			));

			GameRegistry.addRecipe(new ShapedOreRecipe(unifier,
					"RRR",
					"rCr",
					" M ", 

					'R', "sheetPlastic",
					'r', Items.redstone,
					'C', Items.comparator,
					'M', machineBase
					));
		}
	}
}
