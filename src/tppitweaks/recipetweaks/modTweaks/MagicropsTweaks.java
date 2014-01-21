package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MagicropsTweaks
{
	public static void registerOres()
	{
		OreDictionary.registerOre("oreMCropsEssence", new ItemStack(magicalcrops.mod_mCrops.BlockOreEssence));
		OreDictionary.registerOre("oreMCropsNetherEssence", new ItemStack(magicalcrops.mod_mCrops.BlockOreEssenceNether));
	}
}
