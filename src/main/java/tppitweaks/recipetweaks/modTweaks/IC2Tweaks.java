package tppitweaks.recipetweaks.modTweaks;

import ic2.core.Ic2Items;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeAddition.EventTime;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class IC2Tweaks
{
	@RecipeRemoval(requiredModids="IC2")
	public static void init()
	{
		if (ConfigurationHandler.removeStupidEnergyCrystalRecipe)
			TweakingRegistry.markItemForRecipeRemoval(((ItemStack)ic2.core.Ic2Items.energyCrystal).itemID, -1, TweakingAction.REMOVED, "Recipe from dartcraft because", "it is imbalanced with vanilla IC2");
		
		TweakingRegistry.markItemForRecipeRemoval(((ItemStack)Ic2Items.iridiumDrill).itemID, -1);
		TweakingRegistry.markItemForRecipeRemoval(((ItemStack)Ic2Items.mvTransformer).itemID, 4);
		TweakingRegistry.markItemForRecipeRemoval(((ItemStack)Ic2Items.weedEx).itemID, -1);
		TweakingRegistry.markItemForRecipeRemoval(((ItemStack)Ic2Items.suBattery).itemID, -1);
		TweakingRegistry.markItemForRecipeRemoval(((ItemStack)Ic2Items.personalSafe).itemID, 0);
		TweakingRegistry.markItemForRecipeRemoval(((ItemStack)Ic2Items.nanoHelmet).itemID, -1);
		TweakingRegistry.markItemForRecipeRemoval(((ItemStack)Ic2Items.electricJetpack).itemID, -1);
		TweakingRegistry.markItemForRecipeRemoval(((ItemStack)Ic2Items.carbonFiber).itemID, -1);
	}
	
	@RecipeAddition(requiredModids="IC2", time=EventTime.INIT)
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
		
		OreDictionary.registerOre("resinIC2", Ic2Items.resin);
	}
	
	@RecipeAddition(requiredModids="IC2", time=EventTime.INIT)
	public static void addRecipes() {
		if(ConfigurationHandler.doCharcoalBlockCompression) {
			if(!OreDictionary.getOres("blockCharcoal").isEmpty()) {
				ic2.core.block.machine.tileentity.TileEntityCompressor.addRecipe("blockCharcoal", 1, new ItemStack(Item.coal, 1, 0));
			}
		}
	}
	
	@RecipeAddition(requiredModids="IC2", time=EventTime.WORLD_LOAD)
	public static void doPostLoadRecipeAdditions()
	{
		/* copypasta code, ignore horrible formatting */
			       		
		GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.iridiumDrill, new Object[] { " I ", "IdI", " C ", Character.valueOf('I'), Ic2Items.iridiumPlate, Character.valueOf('d'), StackUtil.copyWithWildCard(Ic2Items.diamondDrill), Character.valueOf('C'), StackUtil.copyWithWildCard(Ic2Items.energyCrystal) }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.mvTransformer, new Object[] { "C", "M", "C", Character.valueOf('M'), Ic2Items.machine, Character.valueOf('C'), Ic2Items.insulatedCopperCableItem }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.weedEx, new Object[] { "R", "G", "C", Character.valueOf('R'), Item.redstone, Character.valueOf('G'), Ic2Items.grinPowder, Character.valueOf('C'), Ic2Items.cell }));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 5), new Object[] { "C", "R", "D", Character.valueOf('D'), "dustCoal", Character.valueOf('R'), Item.redstone, Character.valueOf('C'), Ic2Items.insulatedCopperCableItem }));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 5), new Object[] { "C", "D", "R", Character.valueOf('D'), "dustCoal", Character.valueOf('R'), Item.redstone, Character.valueOf('C'), Ic2Items.insulatedCopperCableItem }));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 8), new Object[] { "c", "C", "R", Character.valueOf('R'), Item.redstone, Character.valueOf('C'), "dustHydratedCoal", Character.valueOf('c'), Ic2Items.insulatedCopperCableItem }));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 8), new Object[] { "c", "R", "C", Character.valueOf('R'), Item.redstone, Character.valueOf('C'), "dustHydratedCoal", Character.valueOf('c'), Ic2Items.insulatedCopperCableItem }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.personalSafe, new Object[] { "c", "M", "C", Character.valueOf('c'), Ic2Items.recipeObjectCircuit, Character.valueOf('C'), Block.chest, Character.valueOf('M'), Ic2Items.machine }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.nanoHelmet, new Object[] { "CcC", "CGC", Character.valueOf('C'), Ic2Items.carbonPlate, Character.valueOf('c'), StackUtil.copyWithWildCard(Ic2Items.energyCrystal), Character.valueOf('G'), StackUtil.copyWithWildCard(Ic2Items.nightvisionGoggles) }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.electricJetpack, new Object[] { "ICI", "IBI", "G G", Character.valueOf('I'), Ic2Items.casingiron, Character.valueOf('C'), Ic2Items.recipeObjectAdvCircuit, Character.valueOf('B'), Ic2Items.batBox, Character.valueOf('G'), Item.glowstone }));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Ic2Items.carbonFiber, "dustCoal", "dustCoal", "dustCoal", "dustCoal"));
	}
}
