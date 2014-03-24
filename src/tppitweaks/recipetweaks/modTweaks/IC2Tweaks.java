package tppitweaks.recipetweaks.modTweaks;

import ic2.core.AdvCraftingRecipeManager;
import ic2.core.Ic2Items;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class IC2Tweaks
{
	public static void init()
	{
		if (ConfigurationHandler.removeStupidEnergyCrystalRecipe)
			TweakerBase.markItemForRecipeRemoval(((ItemStack)ic2.core.Ic2Items.energyCrystal).itemID, -1);
		
		TweakerBase.markItemForRecipeRemoval(((ItemStack)Ic2Items.iridiumDrill).itemID, -1);
		TweakerBase.markItemForRecipeRemoval(((ItemStack)Ic2Items.mvTransformer).itemID, -1);
		TweakerBase.markItemForRecipeRemoval(((ItemStack)Ic2Items.weedEx).itemID, -1);
		TweakerBase.markItemForRecipeRemoval(((ItemStack)Ic2Items.suBattery).itemID, -1);
		TweakerBase.markItemForRecipeRemoval(((ItemStack)Ic2Items.personalSafe).itemID, -1);
		TweakerBase.markItemForRecipeRemoval(((ItemStack)Ic2Items.nanoHelmet).itemID, -1);
		TweakerBase.markItemForRecipeRemoval(((ItemStack)Ic2Items.electricJetpack).itemID, -1);
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
	}
	
	public static void doPostLoadRecipeAdditions()
	{
		/* copypasta code, ignore horrible formatting */
		
		AdvCraftingRecipeManager localAdvCraftingRecipeManager = (AdvCraftingRecipeManager) (ic2.api.recipe.Recipes.advRecipes = new AdvCraftingRecipeManager());
	       
        localAdvCraftingRecipeManager.addRecipe(Ic2Items.iridiumDrill, new Object[] { " I ", "IdI", " C ", Character.valueOf('I'), Ic2Items.iridiumPlate, Character.valueOf('d'), StackUtil.copyWithWildCard(Ic2Items.diamondDrill), Character.valueOf('C'), StackUtil.copyWithWildCard(Ic2Items.energyCrystal) });
        localAdvCraftingRecipeManager.addRecipe(Ic2Items.mvTransformer, new Object[] { "C", "M", "C", Character.valueOf('M'), Ic2Items.machine, Character.valueOf('C'), Ic2Items.insulatedCopperCableItem });
        localAdvCraftingRecipeManager.addRecipe(Ic2Items.weedEx, new Object[] { "R", "G", "C", Character.valueOf('R'), Item.redstone, Character.valueOf('G'), Ic2Items.grinPowder, Character.valueOf('C'), Ic2Items.cell });
        localAdvCraftingRecipeManager.addRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 5), new Object[] { "C", "R", "D", Character.valueOf('D'), "dustCoal", Character.valueOf('R'), Item.redstone, Character.valueOf('C'), Ic2Items.insulatedCopperCableItem });
        localAdvCraftingRecipeManager.addRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 5), new Object[] { "C", "D", "R", Character.valueOf('D'), "dustCoal", Character.valueOf('R'), Item.redstone, Character.valueOf('C'), Ic2Items.insulatedCopperCableItem });
        localAdvCraftingRecipeManager.addRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 8), new Object[] { "c", "C", "R", Character.valueOf('R'), Item.redstone, Character.valueOf('C'), "dustHydratedCoal", Character.valueOf('c'), Ic2Items.insulatedCopperCableItem });
        localAdvCraftingRecipeManager.addRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 8), new Object[] { "c", "R", "C", Character.valueOf('R'), Item.redstone, Character.valueOf('C'), "dustHydratedCoal", Character.valueOf('c'), Ic2Items.insulatedCopperCableItem });
        localAdvCraftingRecipeManager.addRecipe(Ic2Items.personalSafe, new Object[] { "c", "M", "C", Character.valueOf('c'), Ic2Items.recipeObjectCircuit, Character.valueOf('C'), Block.chest, Character.valueOf('M'), Ic2Items.machine });
        localAdvCraftingRecipeManager.addRecipe(Ic2Items.nanoHelmet, new Object[] { "CcC", "CGC", Character.valueOf('C'), Ic2Items.carbonPlate, Character.valueOf('c'), StackUtil.copyWithWildCard(Ic2Items.energyCrystal), Character.valueOf('G'), StackUtil.copyWithWildCard(Ic2Items.nightvisionGoggles) });
        localAdvCraftingRecipeManager.addRecipe(Ic2Items.electricJetpack, new Object[] { "ICI", "IBI", "G G", Character.valueOf('I'), Ic2Items.casingiron, Character.valueOf('C'), Ic2Items.recipeObjectAdvCircuit, Character.valueOf('B'), Ic2Items.batBox, Character.valueOf('G'), Item.glowstone });
	}
}
