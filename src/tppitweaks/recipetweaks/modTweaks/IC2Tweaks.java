package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.Loader;

public class IC2Tweaks
{
	public static void registerOres()
	{
		if (ConfigurationHandler.ic2TEGlassInterchangeability)
		{
			if (Loader.isModLoaded("IC2") && OreDictionary.getOreID(ic2.core.Ic2Items.reinforcedGlass) == -1)
			{
				OreDictionary.registerOre("glassReinforced", ic2.core.Ic2Items.reinforcedGlass);
			}
			for (ItemStack stack : OreDictionary.getOres("glassReinforced"))
			{
				OreDictionary.registerOre("glassHardened", stack);
			}
			for (ItemStack stack : OreDictionary.getOres("glassHardened"))
			{
				OreDictionary.registerOre("glassReinforced", stack);
			}
		}
	}
}
