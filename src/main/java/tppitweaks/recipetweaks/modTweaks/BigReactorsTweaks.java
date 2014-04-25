package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.RecipeRemoval;
import tppitweaks.recipetweaks.TweakingRegistry;
import tppitweaks.recipetweaks.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class BigReactorsTweaks {
		
	@RecipeRemoval(requiredModids="BigReactors")
	public static void init()
	{
		if (ConfigurationHandler.steelReactorCasings && !OreDictionary.getOres("ingotSteel").isEmpty())
		{
			TweakingRegistry.markItemForRecipeRemoval(((Block) erogenousbeef.bigreactors.common.BigReactors.blockReactorPart).blockID, 0, TweakingAction.CHANGED, "Recipe requires steel to", "make the mod later game");
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			TweakingRegistry.markItemForRecipeRemoval(((Block) erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod).blockID, -1, TweakingAction.CHANGED, "Recipe requires hardened glass", "to make the mod later game");
		}
		if (ConfigurationHandler.twoReactorGlass)
		{
			TweakingRegistry.markItemForRecipeRemoval(((Block) erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass).blockID, 0, TweakingAction.CHANGED, "Recipe output doubled", "to offset the expensive glass");
			TweakingRegistry.markItemForRecipeRemoval(((Block) erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass).blockID, 1, TweakingAction.CHANGED, "Recipe output doubled", "to offset the expensive glass");
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
		if (ConfigurationHandler.twoReactorGlass)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, 2),
			"gCg",
			
			'g', "glassHardened",
			'C', new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockReactorPart, 1, 0)));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, 2),
			"gCg",
			
			'g', "glassReinforced",
			'C', new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockReactorPart, 1, 0)));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, 2, 1),
			"gCg",
			
			'g', "glassHardened",
			'C', new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockTurbinePart, 1, 0)));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, 2, 1),
			"gCg",
			
			'g', "glassReinforced",
			'C', new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockTurbinePart, 1, 0)));
		}
	}
}
