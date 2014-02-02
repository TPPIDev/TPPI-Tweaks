package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class MekanismTweaks
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(mekanism.common.Mekanism.AtomicDisassembler.itemID, -1);
	}
	
	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(mekanism.common.Mekanism.AtomicDisassembler), new Object[]{
			"AtA",
			"AsA",
			" s ",
			
			'A', mekanism.common.Mekanism.AtomicCore,
			't', mekanism.common.Mekanism.EnergyTablet,
			's', "ingotSteel"
		}));
	}
}
