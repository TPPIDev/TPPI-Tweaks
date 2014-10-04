package tppitweaks.recipetweaks.modTweaks;

import java.lang.reflect.Field;
import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.TPPITweaks;
import tppitweaks.block.ModBlocks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeAddition.EventTime;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

import com.rwtema.extrautils.ExtraUtils;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ExUTweaks
{
	@RecipeRemoval(requiredModids="ExtraUtilities")
	public static void init() {
		if(ConfigurationHandler.nerfEnderQuarry) {
			TweakingRegistry.markItemForRecipeRemoval(ExtraUtils.enderQuarry, -1, TweakingAction.CHANGED, "Recipe changed to better", "balance with BC quarry recipe");
			TweakingRegistry.markItemForRecipeRemoval(ExtraUtils.decorative1, 11, TweakingAction.CHANGED, "Recipe changed for ender quarry");
		}
		if(ConfigurationHandler.nerfRedstoneGen) {
			TweakingRegistry.markItemForRecipeRemoval(ExtraUtils.generator, 4, TweakingAction.CHANGED, "Recipe requires 2xcompressed redstone", "because it produces tonnes of RF");
		}
		if(ConfigurationHandler.nerfEnderGen && !OreDictionary.getOres("blockEnderium").isEmpty()) {
			TweakingRegistry.markItemForRecipeRemoval(ExtraUtils.generator, 3, TweakingAction.CHANGED, "Recipe requires enderium", "because it produces tonnes of RF");
		}
	}
	
	@SuppressWarnings("unchecked")
	@RecipeAddition(requiredModids="ExtraUtilities")
	public static void addRecipes() {
		if(ConfigurationHandler.nerfEnderQuarry) {
				
			ItemStack portal = new ItemStack(ExtraUtils.portal);
			ItemStack quadDirt = new ItemStack(ExtraUtils.cobblestoneCompr, 1, 11);
			ItemStack enderObs = new ItemStack(ExtraUtils.decorative1, 1, 1);
			ItemStack magicalWood = new ItemStack(ExtraUtils.decorative1, 1, 8);
			ItemStack enderCore = new ItemStack(ExtraUtils.decorative1, 1, 11);
			ItemStack DECM = new ItemStack(ExtraUtils.decorative1, 1, 12);

		    
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 3), 
		    	"AWS", 
		    	"PBP", 
		    	"GEG",
		    	
		    	'A', ExtraUtils.destructionPickaxe, 
		    	'W', ExtraUtils.buildersWand, 
		    	'S', ExtraUtils.erosionShovel, 
		    	'P', portal,
		    	'B', Blocks.iron_bars,
		    	'G', enderCore,
		    	'E', Loader.isModLoaded("ThermalExpansion") ? TETweaks.getResonantCell() : Items.ender_pearl
		    ));
		    
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 3), 
		    	"SWA", 
		    	"PBP", 
		    	"GEG",
		    	
		    	'A', ExtraUtils.destructionPickaxe, 
		    	'W', ExtraUtils.buildersWand, 
		    	'S', ExtraUtils.erosionShovel, 
		    	'P', portal, 
		    	'B', Blocks.iron_bars,
		    	'G', enderCore,
		    	'E', Loader.isModLoaded("ThermalExpansion") ? TETweaks.getResonantCell() : Items.ender_pearl
		    ));
		    
		    GameRegistry.addRecipe(new ShapedOreRecipe(ExtraUtils.enderQuarry,
		    	"EQE", 
		    	"CDC", 
		    	"pPp", 
		    	
		    	'E', enderObs,
		    	'Q', quadDirt, 
		    	'M', magicalWood,
		    	'C', enderCore, 
		    	'D', DECM, 
		    	'P', new ItemStack(ModItems.tppiMaterial, 1, 3), 
		    	'p', ExtraUtils.enderThermicPump == null ? new ItemStack(ExtraUtils.decorative1, 1, 12) : ExtraUtils.enderThermicPump 
		    ));
		    
		    GameRegistry.addRecipe(enderCore, 
		    	"ABA",
		    	"BEB",
		    	"ABA",
		    	
		    	'A', magicalWood,
		    	'B', DECM,
		    	'E', Items.ender_eye
		    );
		}
		
		if(ConfigurationHandler.nerfRedstoneGen) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ExtraUtils.generator, 1, 4),
			    	"RRR", 
			    	"RGR", 
			    	"DFD", 
			    	
			    	'R', ModBlocks.tppiBlock, 
			    	'G', new ItemStack(ExtraUtils.generator, 1, 2), 
			    	'D', Items.redstone, 
			    	'F', Blocks.furnace
			    ));
		}
		if(ConfigurationHandler.nerfEnderGen && !OreDictionary.getOres("blockEnderium").isEmpty()) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ExtraUtils.generator, 1, 3),
			    	"PPP", 
			    	"EBE", 
			    	"DFD", 
			    	
			    	'P', Items.ender_pearl,
			    	'E', Items.ender_eye, 
			    	'B', "blockEnderium", 
			    	'D', Items.redstone, 
			    	'F', Blocks.furnace
			    ));
		}

		try
		{
			Field f = OreDictionary.class.getDeclaredField("oreIDs");
			f.setAccessible(true);
			HashMap<String, Integer> temp = (HashMap<String, Integer>) f.get(null);
			temp.remove("nuggetUnstable");
			f.set(null, temp);
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}

		if (ConfigurationHandler.fixExURecipes)
		{
			try
			{
				TPPITweaks.logger.info("You made me do this, RwTema, know that ;_;");
				TPPITweaks.logger.info("Fixing ExtraUtils OreDict registrations by hacky reflection");
				Field f = OreDictionary.class.getDeclaredField("oreIDs");
				f.setAccessible(true);
				HashMap<String, Integer> temp1 = (HashMap<String, Integer>) f.get(null);
				temp1.remove("blockUnstable");
				temp1.remove("burntquartz");
				temp1.remove("icestone");
				f.set(null, temp1);
			}
			catch (Throwable t)
			{
				t.printStackTrace();
			}

			OreDictionary.registerOre("blockUnstable", new ItemStack(ExtraUtils.decorative1, 1, 5));
			OreDictionary.registerOre("burntquartz", new ItemStack(ExtraUtils.decorative1, 1, 2));
			OreDictionary.registerOre("icestone", new ItemStack(ExtraUtils.decorative1, 1, 3));
		}	
		
		if (Loader.isModLoaded("gregtech_addon"))
			TPPITweaks.logger.info("Stahp, greg, I know. Blame Tema.");
	}
	
	@RecipeAddition(requiredModids="ExtraUtilities", time=EventTime.WORLD_LOAD)
	public static void doPostLoadRecipeAdditions()
	{
		ItemStack stableIngot = new ItemStack(ExtraUtils.unstableIngot);
		stableIngot.stackTagCompound = new NBTTagCompound();
		stableIngot.stackTagCompound.setBoolean("stable", true);
		
	    GameRegistry.addRecipe(new ItemStack(ExtraUtils.decorative1, 1, 5), "iii", "iii", "iii", 'i', ExtraUtils.unstableIngot);
	    GameRegistry.addRecipe(stableIngot, "nnn", "nnn", "nnn", 'n', new ItemStack(ExtraUtils.unstableIngot, 1, 1));
	}
}
