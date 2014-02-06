package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MagicropsTweaks
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(magicalcrops.mod_mCrops.mSeedsXP.itemID, -1);
	}
	
	public static void registerOres()
	{
		OreDictionary.registerOre("oreMCropsEssence", new ItemStack(magicalcrops.mod_mCrops.BlockOreEssence));
		OreDictionary.registerOre("oreMCropsNetherEssence", new ItemStack(magicalcrops.mod_mCrops.BlockOreEssenceNether));
	}
}
