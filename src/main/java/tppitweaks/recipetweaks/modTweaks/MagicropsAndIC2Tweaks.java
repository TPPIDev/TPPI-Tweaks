package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.TweakingRegistry;
import tppitweaks.recipetweaks.TweakingRegistry.TweakingAction;

public class MagicropsAndIC2Tweaks
{
	@RecipeAddition(requiredModids={"magicalcrops", "IC2"})
	public static void addRecipes()
	{
		ItemStack macerator = ic2.core.Ic2Items.macerator;
		TweakingRegistry.addTweakedTooltip(macerator.itemID, macerator.getItemDamage(), TweakingAction.ADDED, "Recipe for Essence Ore and", "Nether Essence Ore");
		ic2.core.block.machine.tileentity.TileEntityMacerator.addRecipe("oreMCropsEssence", 1, new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 8, 0));
		ic2.core.block.machine.tileentity.TileEntityMacerator.addRecipe("oreMCropsNetherEssence", 1, new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 12, 0));
	}
}
