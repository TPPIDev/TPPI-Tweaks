package tppitweaks.recipetweaks.modTweaks;

import mrtjp.projectred.exploration.BlockSpecialStone;
import net.minecraftforge.oredict.OreDictionary;
import tterrag.rtc.RecipeAddition;

public class PRTweaks
{
	@RecipeAddition(requiredModids="ProjRed|Core")
	public static void registerMarble()
	{
		OreDictionary.registerOre("blockMarble", BlockSpecialStone.EnumSpecialStone.MARBLE.getItemStack());
	}
}
