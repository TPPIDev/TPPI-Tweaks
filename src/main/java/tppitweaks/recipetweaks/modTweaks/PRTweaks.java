package tppitweaks.recipetweaks.modTweaks;

import mrtjp.projectred.exploration.BlockSpecialStone;
import net.minecraftforge.oredict.OreDictionary;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.RecipeAddition.EventTime;

public class PRTweaks
{
	@RecipeAddition(requiredModids="ProjRed|Core", time=EventTime.INIT)
	public static void registerMarble()
	{
		OreDictionary.registerOre("blockMarble", BlockSpecialStone.EnumSpecialStone.MARBLE.getItemStack());
	}
}
