package tppitweaks.recipetweaks.modTweaks;

import ic2.core.Ic2Items;
import ic2.core.util.StackUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
			TweakingRegistry.markItemForRecipeRemoval(Ic2Items.energyCrystal.getItem(), -1, TweakingAction.REMOVED, "Recipe from dartcraft because", "it is imbalanced with vanilla IC2");
		
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.iridiumDrill.getItem(), -1);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.mvTransformer.getItem(), 4);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.weedEx.getItem(), -1);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.suBattery.getItem(), -1);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.personalSafe.getItem(), 0);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.nanoHelmet.getItem(), -1);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.electricJetpack.getItem(), -1);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.carbonFiber.getItem(), -1);
	}
	
	@RecipeAddition(requiredModids="IC2", time=EventTime.INIT)
	public static void registerOres()
	{
		if (ConfigurationHandler.ic2TEGlassInterchangeability)
		{
			if (Loader.isModLoaded("IC2") && OreDictionary.getOreIDs(Ic2Items.reinforcedGlass).length > 0)
			{
				OreDictionary.registerOre("glassReinforced", Ic2Items.reinforcedGlass);
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
				ic2.core.block.machine.tileentity.TileEntityCompressor.addRecipe("blockCharcoal", 1, new ItemStack(Items.coal, 1, 0));
			}
		}
	}
	
	@RecipeAddition(requiredModids="IC2", time=EventTime.WORLD_LOAD)
	public static void doPostLoadRecipeAdditions()
	{
		/* copypasta code, ignore horrible formatting */
			       		
		GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.iridiumDrill, new Object[] { " I ", "IdI", " C ", Character.valueOf('I'), Ic2Items.iridiumPlate, Character.valueOf('d'), StackUtil.copyWithWildCard(Ic2Items.diamondDrill), Character.valueOf('C'), StackUtil.copyWithWildCard(Ic2Items.energyCrystal) }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.mvTransformer, new Object[] { "C", "M", "C", Character.valueOf('M'), Ic2Items.machine, Character.valueOf('C'), Ic2Items.insulatedCopperCableItem }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.weedEx, new Object[] { "R", "G", "C", Character.valueOf('R'), Items.redstone, Character.valueOf('G'), Ic2Items.grinPowder, Character.valueOf('C'), Ic2Items.cell }));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 5), new Object[] { "C", "R", "D", Character.valueOf('D'), "dustCoal", Character.valueOf('R'), Items.redstone, Character.valueOf('C'), Ic2Items.insulatedCopperCableItem }));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 5), new Object[] { "C", "D", "R", Character.valueOf('D'), "dustCoal", Character.valueOf('R'), Items.redstone, Character.valueOf('C'), Ic2Items.insulatedCopperCableItem }));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 8), new Object[] { "c", "C", "R", Character.valueOf('R'), Items.redstone, Character.valueOf('C'), "dustHydratedCoal", Character.valueOf('c'), Ic2Items.insulatedCopperCableItem }));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 8), new Object[] { "c", "R", "C", Character.valueOf('R'), Items.redstone, Character.valueOf('C'), "dustHydratedCoal", Character.valueOf('c'), Ic2Items.insulatedCopperCableItem }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.personalSafe, new Object[] { "c", "M", "C", Character.valueOf('c'), Ic2Items.electronicCircuit.copy(), Character.valueOf('C'), Blocks.chest, Character.valueOf('M'), Ic2Items.machine }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.nanoHelmet, new Object[] { "CcC", "CGC", Character.valueOf('C'), Ic2Items.carbonPlate, Character.valueOf('c'), StackUtil.copyWithWildCard(Ic2Items.energyCrystal), Character.valueOf('G'), StackUtil.copyWithWildCard(Ic2Items.nightvisionGoggles) }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.electricJetpack, new Object[] { "ICI", "IBI", "G G", Character.valueOf('I'), Ic2Items.casingiron, Character.valueOf('C'), Ic2Items.advancedCircuit.copy(), Character.valueOf('B'), Ic2Items.batBox, Character.valueOf('G'), Items.glowstone_dust }));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Ic2Items.carbonFiber, "dustCoal", "dustCoal", "dustCoal", "dustCoal"));
	}
}
