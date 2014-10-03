package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;
import erogenousbeef.bigreactors.common.BigReactors;
import erogenousbeef.bigreactors.common.multiblock.block.BlockReactorPart;

public class BigReactorsTweaks {
		
	@RecipeRemoval(requiredModids="BigReactors")
	public static void init()
	{
		if (ConfigurationHandler.steelReactorCasings && !OreDictionary.getOres("ingotSteel").isEmpty())
		{
			TweakingRegistry.markItemForRecipeRemoval((Block) erogenousbeef.bigreactors.common.BigReactors.blockReactorPart, 0, TweakingAction.CHANGED, "Recipe requires steel to", "make the mod later game");
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			TweakingRegistry.markItemForRecipeRemoval((Block) erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod, -1, TweakingAction.CHANGED, "Recipe requires hardened glass", "to make the mod later game");
		}
		if (ConfigurationHandler.twoReactorGlass)
		{
			TweakingRegistry.markItemForRecipeRemoval((Block) erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, 0, TweakingAction.CHANGED, "Recipe output doubled", "to offset the expensive glass");
			TweakingRegistry.markItemForRecipeRemoval((Block) erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, 1, TweakingAction.CHANGED, "Recipe output doubled", "to offset the expensive glass");
		}	
	}
	
	@RecipeAddition(requiredModids="BigReactors")
	public static void addRecipes()
	{
		if (ConfigurationHandler.steelReactorCasings && !OreDictionary.getOres("ingotSteel").isEmpty())
		{
	        /* @formatter:off */
			ItemStack reactorPartStack = ((BlockReactorPart) BigReactors.blockReactorPart).getReactorCasingItemStack();
			reactorPartStack.stackSize = 4;
			
			GameRegistry.addRecipe(new ShapedOreRecipe(reactorPartStack, 
			        "ICI", 
			        "CUC", 
			        "ICI", 
			        
			        'I', "ingotSteel", 
			        'C', "ingotGraphite", 
			        'U', "ingotYellorium" 
			));
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BigReactors.blockYelloriumFuelRod, 1), 
			        "ICI", 
			        "GUG", 
			        "ICI", 
			        
			        'I', BigReactors.blockMultiblockGlass, 
			        'C', "ingotIron", 
			        'U', "ingotYellorium", 
			        'G', "ingotGraphite" 
			));
		}
		if (ConfigurationHandler.twoReactorGlass)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BigReactors.blockMultiblockGlass, 2),
			        "gCg",
			
			        'g', "glassHardened",
			        'C', new ItemStack(BigReactors.blockReactorPart, 1, 0)
			));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BigReactors.blockMultiblockGlass, 2),
			        "gCg",
			
			        'g', "glassReinforced",
			        'C', new ItemStack(BigReactors.blockReactorPart, 1, 0)
			));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BigReactors.blockMultiblockGlass, 2, 1),
			        "gCg",
			
			        'g', "glassHardened",
			        'C', new ItemStack(BigReactors.blockTurbinePart, 1, 0)
			));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BigReactors.blockMultiblockGlass, 2, 1),
			        "gCg",
			
			        'g', "glassReinforced",
			        'C', new ItemStack(BigReactors.blockTurbinePart, 1, 0)
			));
			/* @formatter:on */
		}
	}
}
