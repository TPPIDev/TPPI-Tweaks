package tppitweaks.recipetweaks.modTweaks;

import ic2.core.Ic2Items;
import ic2.core.block.machine.tileentity.TileEntityMacerator;
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
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.iridiumDrill.getItem(), -1);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.mvTransformer.getItem(), 4);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.weedEx.getItem(), -1);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.suBattery.getItem(), -1);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.personalSafe.getItem(), 0);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.nanoHelmet.getItem(), -1);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.electricJetpack.getItem(), -1);
		TweakingRegistry.markItemForRecipeRemoval(Ic2Items.carbonFiber.getItem(), -1);
		
		if (ConfigurationHandler.addIridiumPanelRecipe){
			TweakingRegistry.addTweakedTooltip(Ic2Items.iridiumPlate.getItem(), 0, TweakingAction.ADDED, "Added secondary recipe for","AOBD compatibility.");
		}
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
		
		if(ConfigurationHandler.addIridiumPanelRecipe){
			GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.iridiumPlate,
					"IPI",
					"PDP",
					"IPI",
					'I', "ingotIridium",
					'P', Ic2Items.advancedAlloy,
					'D', "gemDiamond"
			));
		}
	}
	
	@RecipeAddition(requiredModids="IC2", time=EventTime.WORLD_LOAD)
	public static void doPostLoadRecipeAdditions()
	{
		/* copypasta code, ignore horrible formatting */
			       		
		GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.iridiumDrill, " I ", "IdI", " C ", 'I', Ic2Items.iridiumPlate, 'd', StackUtil.copyWithWildCard(Ic2Items.diamondDrill), 'C', StackUtil.copyWithWildCard(Ic2Items.energyCrystal)));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.mvTransformer, "C", "M", "C", 'M', Ic2Items.machine, 'C', Ic2Items.insulatedCopperCableItem));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.weedEx, "R", "G", "C", 'R', Items.redstone, 'G', Ic2Items.grinPowder, 'C', Ic2Items.cell));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 5), "C", "R", "D", 'D', "dustCoal", 'R', Items.redstone, 'C', Ic2Items.insulatedCopperCableItem));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 5), "C", "D", "R", 'D', "dustCoal", 'R', Items.redstone, 'C', Ic2Items.insulatedCopperCableItem));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 8), "c", "C", "R", 'R', Items.redstone, 'C', "dustHydratedCoal", 'c', Ic2Items.insulatedCopperCableItem));
        GameRegistry.addRecipe(new ShapedOreRecipe(StackUtil.copyWithSize(Ic2Items.suBattery, 8), "c", "R", "C", 'R', Items.redstone, 'C', "dustHydratedCoal", 'c', Ic2Items.insulatedCopperCableItem));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.personalSafe, "c", "M", "C", 'c', Ic2Items.electronicCircuit.copy(), 'C', Blocks.chest, 'M', Ic2Items.machine));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.nanoHelmet, "CcC", "CGC", 'C', Ic2Items.carbonPlate, 'c', StackUtil.copyWithWildCard(Ic2Items.energyCrystal), 'G', StackUtil.copyWithWildCard(Ic2Items.nightvisionGoggles)));
        GameRegistry.addRecipe(new ShapedOreRecipe(Ic2Items.electricJetpack, "ICI", "IBI", "G G", 'I', Ic2Items.casingiron, 'C', Ic2Items.advancedCircuit.copy(), 'B', Ic2Items.batBox, 'G', Items.glowstone_dust));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Ic2Items.carbonFiber, "dustCoal", "dustCoal", "dustCoal", "dustCoal"));
	}
	
	public static void addMaceratorRecipe(String ore, int amnt, ItemStack output)
	{
	    TileEntityMacerator.addRecipe(ore, amnt, output);
	}
}
