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

public class BigReactorsTweaks {
		
	@RecipeRemoval(requiredModids="BigReactors")
	public static void init()
	{

        Block reactorPart = GameRegistry.findBlock("BigReactors", "BRReactorPart");
        Block reactorGlass = GameRegistry.findBlock("BigReactors", "BRMultiblockGlass");
        Block reactorFuelRod = GameRegistry.findBlock("BigReactors", "YelloriumFuelRod");

		if (ConfigurationHandler.steelReactorCasings && !OreDictionary.getOres("ingotSteel").isEmpty())
		{
			TweakingRegistry.markItemForRecipeRemoval(reactorPart, 0, TweakingAction.CHANGED, "Recipe requires steel to", "make the mod later game");
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			TweakingRegistry.markItemForRecipeRemoval(reactorFuelRod, -1, TweakingAction.CHANGED, "Recipe requires hardened glass", "to make the mod later game");
		}
		if (ConfigurationHandler.twoReactorGlass)
		{
			TweakingRegistry.markItemForRecipeRemoval(reactorGlass, 0, TweakingAction.CHANGED, "Recipe output doubled", "to offset the expensive glass");
			TweakingRegistry.markItemForRecipeRemoval(reactorGlass, 1, TweakingAction.CHANGED, "Recipe output doubled", "to offset the expensive glass");
		}	
	}
	
	@RecipeAddition(requiredModids="BigReactors")
	public static void addRecipes()
	{

        Block reactorPart = GameRegistry.findBlock("BigReactors", "BRReactorPart");
        Block turbinePart = GameRegistry.findBlock("BigReactors", "BRTurbinePart");
        Block reactorGlass = GameRegistry.findBlock("BigReactors", "BRMultiblockGlass");
        Block reactorFuelRod = GameRegistry.findBlock("BigReactors", "YelloriumFuelRod");

		if (ConfigurationHandler.steelReactorCasings && !OreDictionary.getOres("ingotSteel").isEmpty())
		{
	        /* @formatter:off */
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reactorPart, 4, 0), 
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
			GameRegistry.addRecipe(new ShapedOreRecipe(reactorFuelRod, 
			        "ICI", 
			        "GUG", 
			        "ICI", 
			        
			        'I', reactorGlass, 
			        'C', "ingotIron", 
			        'U', "ingotYellorium", 
			        'G', "ingotGraphite" 
			));
		}
		if (ConfigurationHandler.twoReactorGlass)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reactorGlass, 2),
			        "gCg",
			
			        'g', "blockGlassHardened",
			        'C', reactorPart
			));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reactorGlass, 2),
			        "gCg",
			
			        'g', "glassReinforced",
			        'C', reactorPart
			));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reactorGlass, 2, 1),
			        "gCg",
			
			        'g', "blockGlassHardened",
			        'C', turbinePart
			));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reactorGlass, 2, 1),
			        "gCg",
			
			        'g', "glassReinforced",
			        'C', turbinePart
			));
			/* @formatter:on */
		}
	}
}
