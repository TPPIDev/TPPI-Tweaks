package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import powercrystals.minefactoryreloaded.MineFactoryReloadedCore;
import powercrystals.minefactoryreloaded.setup.Machine;
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
		if (ConfigurationHandler.buffUnifierRecipe)
			TweakingRegistry.markItemForRecipeRemoval(Machine.Unifier.getBlock(), 8, TweakingAction.CHANGED, "Cheapened for use", "in JABBA barrels");
		if (ConfigurationHandler.disablePlasticCups)
			TweakingRegistry.markItemForRecipeRemoval(MineFactoryReloadedCore.plasticCupItem, -1, TweakingAction.REMOVED, "Has liquid dupes.");
	}
	
	@RecipeAddition(requiredModids="MineFactoryReloaded")
	public static void addRecipes()
	{
		if (ConfigurationHandler.buffUnifierRecipe)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MineFactoryReloadedCore.machineBaseItem, 3), 
					"RRR",
					"SSS",

					'R', "sheetPlastic",
					'S', "stone"
					));

			GameRegistry.addRecipe(new ShapedOreRecipe(Machine.Unifier.getItemStack(), 
					"RRR",
					"rCr",
					" M ", 

					'R', "sheetPlastic",
					'r', Items.redstone,
					'C', Items.comparator,
					'M', MineFactoryReloadedCore.machineBaseItem
					));
		}
	}
}
