package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.Config;
import crazypants.enderio.EnderIO;

public class EnderIOTweaks
{
	public static void init()
	{
		if (ConfigurationHandler.makeEIOHardModeEasier && Config.useHardRecipes)
		{
			TweakerBase.markItemForRecipeRemoval(EnderIO.blockReservoir.blockID, -1);
			TweakerBase.markItemForRecipeRemoval(EnderIO.itemBasicCapacitor.itemID, 0);
		}
	}

	public static void addRecipes()
	{
		if (ConfigurationHandler.makeEIOHardModeEasier && Config.useHardRecipes)
		{
			GameRegistry.addRecipe(new ItemStack(EnderIO.blockReservoir), 
					"GGG",
					"QcQ",
					"GGG",
					
					'G', new ItemStack(EnderIO.blockFusedQuartz, 1, 1),
					'Q', new ItemStack(EnderIO.blockFusedQuartz),
					'c', Item.cauldron
			);
			
			GameRegistry.addRecipe(new ShapedOreRecipe(EnderIO.itemBasicCapacitor,
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
