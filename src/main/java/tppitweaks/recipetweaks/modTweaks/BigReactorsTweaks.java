package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.TweakingRegistry;
import tppitweaks.recipetweaks.RecipeRemoval;
import cpw.mods.fml.common.registry.GameRegistry;

public class BigReactorsTweaks {
		
	@RecipeRemoval(requiredModids="BigReactors")
	public static void init()
	{
		if (ConfigurationHandler.steelReactorCasings && !OreDictionary.getOres("ingotSteel").isEmpty())
		{
			TweakingRegistry.markItemForRecipeRemoval(((Block) erogenousbeef.bigreactors.common.BigReactors.blockReactorPart).blockID, 0);
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			TweakingRegistry.markItemForRecipeRemoval(((Block) erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod).blockID, -1);
		}		
	}
	
	@RecipeAddition(requiredModids="BigReactors")
	public static void addRecipes()
	{
		if (ConfigurationHandler.steelReactorCasings && !OreDictionary.getOres("ingotSteel").isEmpty())
		{
			ItemStack reactorPartStack = ((erogenousbeef.bigreactors.common.multiblock.block.BlockReactorPart) erogenousbeef.bigreactors.common.BigReactors.blockReactorPart).getReactorCasingItemStack();
			reactorPartStack.stackSize = 4;
			GameRegistry.addRecipe(new ShapedOreRecipe(reactorPartStack, new Object[] { "ICI", "CUC", "ICI", 'I', "ingotSteel", 'C', "ingotGraphite", 'U', "ingotYellorium" }));
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod, 1), new Object[] { "ICI", "GUG", "ICI", Character.valueOf('I'),
				erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, Character.valueOf('C'), "ingotIron", Character.valueOf('U'), "ingotYellorium", Character.valueOf('G'), "ingotGraphite" }));
		}
	}
}
