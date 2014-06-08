package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class MagicropsAndTETweaks
{	
	@RecipeAddition(requiredModids={"magicalcrops", "ThermalExpansion"})
	public static void addRecipes()
	{
		ItemStack pulv = thermalexpansion.block.machine.BlockMachine.pulverizer;
		TweakingRegistry.addTweakedTooltip(pulv.itemID, pulv.getItemDamage(), TweakingAction.ADDED, "Recipes for Essence Ore and", "Nether Essence Ore");
		thermalexpansion.util.crafting.PulverizerManager.addRecipe(2400, OreDictionary.getOres("oreMCropsEssence").get(0), new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 8, 0), new ItemStack(magicalcrops.mod_mCrops.CropEssence, 1, 0), 5);
		thermalexpansion.util.crafting.PulverizerManager.addRecipe(2400, OreDictionary.getOres("oreMCropsNetherEssence").get(0), new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 12, 0), new ItemStack(magicalcrops.mod_mCrops.CropEssence, 1, 0), 10);
	}
}
