package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class BigReactorsTweaks {
	
	public static void init()
	{
		if (ConfigurationHandler.steelReactorCasings)
		{
			TweakerBase.markItemForRecipeRemoval(erogenousbeef.bigreactors.common.BigReactors.blockReactorPart.blockID, 0);
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			TweakerBase.markItemForRecipeRemoval(erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod.blockID, -1);
		}		
	}
	
	public static void addRecipes()
	{
		if (ConfigurationHandler.steelReactorCasings)
		{
			ItemStack reactorPartStack = ((erogenousbeef.bigreactors.common.block.BlockReactorPart) erogenousbeef.bigreactors.common.BigReactors.blockReactorPart).getReactorCasingItemStack();
			reactorPartStack.stackSize = 4;
			GameRegistry.addRecipe(new ShapedOreRecipe(reactorPartStack, new Object[] { "ICI", "CUC", "ICI", 'I', "ingotSteel", 'C', "ingotGraphite", 'U', "ingotYellorium" }));
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod, 1), new Object[] { "ICI", "GUG", "ICI", Character.valueOf('I'),
				erogenousbeef.bigreactors.common.BigReactors.blockReactorGlass, Character.valueOf('C'), "ingotIron", Character.valueOf('U'), "ingotYellorium", Character.valueOf('G'), "ingotGraphite" }));
		}
	}
	
}
