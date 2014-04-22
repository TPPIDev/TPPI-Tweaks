package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.RecipeRemoval;
import tppitweaks.recipetweaks.TweakingRegistry;
import tppitweaks.recipetweaks.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class SFMTweaks
{
	@RecipeRemoval(requiredModids={"AppliedEnergistics", "StevesFactoryManager"})
	public static void init()
	{
		if (ConfigurationHandler.tweakSFM)
		{
			TweakingRegistry.markItemForRecipeRemoval(((Block) vswe.stevesfactory.blocks.Blocks.blockManager).blockID, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(((Block) vswe.stevesfactory.blocks.Blocks.blockCable).blockID, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(((Block) vswe.stevesfactory.blocks.Blocks.blockCableRelay).blockID, 8, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(((Block) vswe.stevesfactory.blocks.Blocks.blockCableInput).blockID, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(((Block) vswe.stevesfactory.blocks.Blocks.blockCableOutput).blockID, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");

			TweakingRegistry.markItemForRecipeRemoval(((Block) vswe.stevesfactory.blocks.Blocks.blockCableIntake).blockID, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakingRegistry.markItemForRecipeRemoval(((Block) vswe.stevesfactory.blocks.Blocks.blockCableBreaker).blockID, -1, TweakingAction.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
		}
	}

	@RecipeAddition(requiredModids={"AppliedEnergistics", "StevesFactoryManager"})
	public static void addRecipes()
	{
		if (ConfigurationHandler.tweakSFM)
		{
			GameRegistry.addRecipe(new ItemStack(vswe.stevesfactory.blocks.Blocks.blockManager), new Object[] { "III", "IRI", "SPS", Character.valueOf('R'), new ItemStack(ModItems.tppiMaterial),
				Character.valueOf('P'), appeng.api.Materials.matConversionMatrix.copy(), Character.valueOf('I'), Item.ingotIron, Character.valueOf('S'), Block.stone });
			GameRegistry.addRecipe(
					new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCable, 8),
					new Object[] { "GPG", "IRI", "GPG", Character.valueOf('R'), appeng.api.Materials.matFluxDust.copy(), Character.valueOf('G'), Block.glass, Character.valueOf('I'), Item.ingotIron,
						Character.valueOf('P'), Block.pressurePlateIron });
			GameRegistry.addRecipe(
					new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCableRelay, 1, 8), 
					new Object[]{
						"fBf",
						"BIB",
						"fBf",

						'B', Block.blockLapis,
						'I', vswe.stevesfactory.blocks.Blocks.blockCableRelay,
						'f', appeng.api.Materials.matFluxDust.copy()
					});

			GameRegistry.addRecipe(
					new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCableInput),
					new Object[]{
						" r ",
						"rIr",
						" r ",

						'r', Item.redstone,
						'I', vswe.stevesfactory.blocks.Blocks.blockCable
					});

			GameRegistry.addRecipe(
					new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCableOutput),
					new Object[]{
						"rtr",
						"rIr",
						"rrr",

						'r', Item.redstone,
						't', appeng.api.Blocks.blkLevelEmitter.copy(),
						'I', vswe.stevesfactory.blocks.Blocks.blockCable
					});

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCableIntake, 1, 0), vswe.stevesfactory.blocks.Blocks.blockCable, Block.hopperBlock, Block.dropper, appeng.api.Blocks.blkInterface.copy()));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCableIntake, 1, 8), new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCableIntake, 1, 0), appeng.api.Materials.matProcessorBasic.copy()));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCableBreaker), vswe.stevesfactory.blocks.Blocks.blockCable, Item.pickaxeIron, Block.dispenser, appeng.api.Blocks.blkInterface.copy()));
		}
	}
}

