package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.block.ModBlocks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.config.Config;

public class EnderIOTweaks
{
	@RecipeRemoval(requiredModids="EnderIO")
	public static void init()
	{
		if (ConfigurationHandler.makeEIOHardModeEasier && Config.useHardRecipes)
		{
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.eioReservoir, -1, TweakingAction.CHANGED, "Easy 'hardmode' recipe");
			TweakingRegistry.markItemForRecipeRemoval(ModItems.eioCapacitor, 0, TweakingAction.CHANGED, "Easy 'hardmode' recipe");
		}

	}

	@RecipeAddition(requiredModids="EnderIO")
	public static void addRecipes()
	{
		if (ConfigurationHandler.makeEIOHardModeEasier && Config.useHardRecipes)
		{
			GameRegistry.addRecipe(new ItemStack(ModBlocks.eioReservoir),
					"GGG",
					"QcQ",
					"GGG",

					'G', new ItemStack(ModBlocks.eioFusedQuartz, 1, 1),
					'Q', new ItemStack(ModBlocks.eioFusedQuartz),
					'c', Items.cauldron
			);

			GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.eioCapacitor,
					" CR",
					"CGC",
					"RC ",

					'C', "ingotCopper",
					'R', "dustRedstone",
					'G', "ingotGold"
			));
		}
	}
}
