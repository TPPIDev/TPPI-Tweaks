package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import vswe.stevesfactory.blocks.ModBlocks;
import appeng.core.Api;
import cpw.mods.fml.common.registry.GameRegistry;

public class SFMTweaks
{
	@RecipeRemoval(requiredModids={"appliedenergistics2", "StevesFactoryManager"})
	public static void init()
	{
		if (ConfigurationHandler.tweakSFM)
		{
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.blockManager, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.blockCable, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.blockCableRelay, 8, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.blockCableInput, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.blockCableOutput, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");

			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.blockCableIntake, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.blockCableBreaker, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
		}
	}

	@RecipeAddition(requiredModids={"appliedenergistics2", "StevesFactoryManager"})
	public static void addRecipes()
	{
		if (ConfigurationHandler.tweakSFM)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blockManager), 
			        "III", 
			        "aRf", 
			        "SSS", 
			        
			        'R', new ItemStack(ModItems.tppiMaterial),
			        'a', Api.INSTANCE.materials().materialAnnihilationCore.stack(1),
			        'f', Api.INSTANCE.materials().materialFormationCore.stack(1),
			        'I', "ingotIron", 
			        'S', "stone"
			));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blockCable, 8),
					 "GPG", 
					 "IRI", 
					 "GPG", 
					 
					 'R', Api.INSTANCE.materials().materialFluixDust.stack(1), 
					 'G', "blockGlass", 
					 'I', "ingotIron",
					 'P', Blocks.heavy_weighted_pressure_plate 
			));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blockCableRelay, 1, 8), 
					    "fBf",
						"BIB",
						"fBf",

						'B', "blockLapis",
						'I', ModBlocks.blockCableRelay,
						'f', Api.INSTANCE.materials().materialFluixDust.stack(1)
			));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blockCableInput),
			            " r ",
						"rIr",
						" r ",

						'r', "dustRedstone",
						'I', ModBlocks.blockCable
			));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blockCableOutput),
						"rtr",
						"rIr",
						"rrr",

						'r', "dustRedstone",
						't', Api.INSTANCE.parts().partLevelEmitter.stack(1),
						'I', ModBlocks.blockCable
			));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockCableIntake, 1, 0), ModBlocks.blockCable, Blocks.hopper, Blocks.dropper, Api.INSTANCE.parts().partInterface.stack(1)));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockCableIntake, 1, 8), new ItemStack(ModBlocks.blockCableIntake, 1, 0), Api.INSTANCE.materials().materialCalcProcessor.stack(1)));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockCableBreaker), ModBlocks.blockCable, Items.iron_pickaxe, Blocks.dispenser, Api.INSTANCE.parts().partInterface.stack(1)));
		}
	}
}

