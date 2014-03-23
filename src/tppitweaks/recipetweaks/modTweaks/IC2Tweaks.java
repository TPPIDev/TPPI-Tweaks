package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class IC2Tweaks
{
	private static ItemStack mvTransformer, copperCable, machineBlock;
	public static void init()
	{
		mvTransformer = ic2.core.Ic2Items.mvTransformer;
		copperCable = ic2.core.Ic2Items.copperCableItem;
		machineBlock = ic2.core.Ic2Items.machine;

		if (ConfigurationHandler.removeStupidEnergyCrystalRecipe)
			TweakerBase.markItemForRecipeRemoval(((ItemStack)ic2.core.Ic2Items.energyCrystal).itemID, -1);
			
		TweakerBase.markItemForRecipeRemoval(mvTransformer.itemID, -1);
	}
	
	public static void registerOres()
	{
		if (ConfigurationHandler.ic2TEGlassInterchangeability)
		{
			if (Loader.isModLoaded("IC2") && OreDictionary.getOreID(ic2.core.Ic2Items.reinforcedGlass) == -1)
			{
				OreDictionary.registerOre("glassReinforced", ic2.core.Ic2Items.reinforcedGlass);
			}
			for (ItemStack stack : OreDictionary.getOres("glassReinforced"))
			{
				OreDictionary.registerOre("glassHardened", stack);
			}
			for (ItemStack stack : OreDictionary.getOres("glassHardened"))
			{
				OreDictionary.registerOre("glassReinforced", stack);
			}
		}
	}
	
	public static void addRecipes() {
		if(ConfigurationHandler.doCharcoalBlockCompression) {
			if(!OreDictionary.getOres("blockCharcoal").isEmpty()) {
				ic2.core.block.machine.tileentity.TileEntityCompressor.addRecipe("blockCharcoal", 1, new ItemStack(Item.coal, 1, 0));
			}
		}
		
		GameRegistry.addRecipe(mvTransformer.copy(), 
				"C",
				"M",
				"C",
				
				'C', copperCable.copy(),
				'M', machineBlock.copy()
		);
	}
}
