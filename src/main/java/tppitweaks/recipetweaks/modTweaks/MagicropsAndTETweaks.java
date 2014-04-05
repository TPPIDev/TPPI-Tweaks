package tppitweaks.recipetweaks.modTweaks;

import tppitweaks.recipetweaks.RecipeAddition;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MagicropsAndTETweaks
{	
	@RecipeAddition(requiredModids={"magicalcrops", "ThermalExpansion"})
	public static void addRecipes()
	{
		thermalexpansion.util.crafting.PulverizerManager.addRecipe(2400, OreDictionary.getOres("oreMCropsEssence").get(0), new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 8, 0), new ItemStack(magicalcrops.mod_mCrops.CropEssence, 1, 0), 5);
		thermalexpansion.util.crafting.PulverizerManager.addRecipe(2400, OreDictionary.getOres("oreMCropsNetherEssence").get(0), new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 12, 0), new ItemStack(magicalcrops.mod_mCrops.CropEssence, 1, 0), 10);
	}
}
