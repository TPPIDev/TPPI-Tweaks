package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.ShapedOreRecipe;
import powercrystals.minefactoryreloaded.MineFactoryReloadedCore;
import powercrystals.minefactoryreloaded.setup.Machine;
import cpw.mods.fml.common.registry.GameRegistry;

public class MFRTweaks
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(Machine.Unifier.getBlockId(), 8);
	}
	
	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(Machine.Unifier.getItemStack(), 
				"RRR",
				"rCr",
				" M ", 
				
				'R', "sheetPlastic",
				'r', Item.redstone,
				'C', Item.comparator,
				'M', MineFactoryReloadedCore.machineBaseItem
		));
	}
}
