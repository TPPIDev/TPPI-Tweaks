package tppitweaks.recipetweaks.modTweaks;

import tppitweaks.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SFMTweaks
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(((Block) vswe.stevesfactory.blocks.Blocks.blockManager).blockID, -1);
		TweakerBase.markItemForRecipeRemoval(((Block) vswe.stevesfactory.blocks.Blocks.blockCable).blockID, -1);	
	}
	
	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(vswe.stevesfactory.blocks.Blocks.blockManager), new Object[] { "III", "IRI", "SPS", Character.valueOf('R'), new ItemStack(ModItems.tppiMaterial),
				Character.valueOf('P'), appeng.api.Materials.matConversionMatrix.copy(), Character.valueOf('I'), Item.ingotIron, Character.valueOf('S'), Block.stone });
		GameRegistry.addRecipe(
				new ItemStack(vswe.stevesfactory.blocks.Blocks.blockCable, 8),
				new Object[] { "GPG", "IRI", "GPG", Character.valueOf('R'), appeng.api.Materials.matFluxDust.copy(), Character.valueOf('G'), Block.glass, Character.valueOf('I'), Item.ingotIron,
						Character.valueOf('P'), Block.pressurePlateIron });
	}
}
