package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class MagicropsTweaks
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(magicalcrops.mod_mCrops.mSeedsXP.itemID, -1);
	}

	public static void registerOres()
	{
		if (ConfigurationHandler.registerMagicalCropsOre)
		{
			OreDictionary.registerOre("oreMCropsEssence", new ItemStack(magicalcrops.mod_mCrops.BlockOreEssence));
			OreDictionary.registerOre("oreMCropsNetherEssence", new ItemStack(magicalcrops.mod_mCrops.BlockOreEssenceNether));
		}
	}

	public static void addRecipes()
	{
		if (ConfigurationHandler.addEssenceSeedRecipe)
		{
			GameRegistry.addRecipe(new ItemStack(magicalcrops.mod_mCrops.mSeedsEssence), new Object[]{
				"wsw",
				"sSs",
				"wsw",

				's', new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 1, 2),
				'w', new ItemStack(magicalcrops.mod_mCrops.MagicEssence),
				'S', Item.seeds
			});
		}
	}
}
