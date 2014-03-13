package tppitweaks.recipetweaks.modTweaks;

import java.lang.reflect.Field;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.TPPITweaks;
import tppitweaks.block.ModBlocks;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.item.ModItems;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ExUTweaks
{
	
	public static void init() {
		if(ConfigurationHandler.nerfEnderQuarry) {
			TweakerBase.markItemForRecipeRemoval(extrautils.ExtraUtils.enderQuarry.blockID, -1);
			TweakerBase.markItemForRecipeRemoval(extrautils.ExtraUtils.decorative1.blockID, 1);
		}
		if(ConfigurationHandler.nerfRedstoneGen) {
			TweakerBase.markItemForRecipeRemoval(extrautils.ExtraUtils.generatorId, 4);
		}
		if(ConfigurationHandler.nerfEnderGen && !OreDictionary.getOres("blockEnderium").isEmpty()) {
			TweakerBase.markItemForRecipeRemoval(extrautils.ExtraUtils.generatorId, 3);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void fixRecipes()
	{
		try
		{
			TPPITweaks.logger.info("You made me do this, RwTema, know that ;_;");
			TPPITweaks.logger.info("Fixing ExtraUtils OreDict registrations by hacky reflection");
			Field f = OreDictionary.class.getDeclaredField("oreIDs");
			f.setAccessible(true);
			HashMap<String, Integer> temp = (HashMap<String, Integer>) f.get(null);
			temp.remove("blockUnstable");
			temp.remove("burntquartz");
			temp.remove("icestone");
			f.set(null, temp);
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
		
		OreDictionary.registerOre("blockUnstable", new ItemStack(extrautils.ExtraUtils.decorative1, 1, 5));
		OreDictionary.registerOre("burntquartz", new ItemStack(extrautils.ExtraUtils.decorative1, 1, 2));
		OreDictionary.registerOre("icestone", new ItemStack(extrautils.ExtraUtils.decorative1, 1, 3));
		
		if (Loader.isModLoaded("gregtech_addon"))
			TPPITweaks.logger.info("Stahp, greg, I know. Blame Tema.");
	}

	public static void addRecipes() {
		if(ConfigurationHandler.nerfEnderQuarry) {
				
			ItemStack portal = new ItemStack(extrautils.ExtraUtils.portal);
			ItemStack quadDirt = new ItemStack(extrautils.ExtraUtils.cobblestoneCompr, 1, 11);
			ItemStack enderObs = new ItemStack(extrautils.ExtraUtils.decorative1, 1, 1);
		    
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 3), 
		    	"AWS", 
		    	"PBP", 
		    	"GEG", 
		    	
		    	'A', extrautils.ExtraUtils.destructionPickaxe, 
		    	'W', extrautils.ExtraUtils.buildersWand, 
		    	'S', extrautils.ExtraUtils.erosionShovel, 
		    	'P', quadDirt, 
		    	'E', portal, 
		    	'G', extrautils.ExtraUtils.cursedEarth,
		    	'B', Block.fenceIron 
		    ));
		    
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 3), 
		    	"SWA", 
		    	"PBP", 
		    	"GEG",
		    	
		    	'A', extrautils.ExtraUtils.destructionPickaxe, 
		    	'W', extrautils.ExtraUtils.buildersWand, 
		    	'S', extrautils.ExtraUtils.erosionShovel, 
		    	'P', quadDirt, 
		    	'E', portal, 
		    	'G', extrautils.ExtraUtils.cursedEarth,
		    	'B', Block.fenceIron 
		    ));
		    
		    GameRegistry.addRecipe(new ShapedOreRecipe(extrautils.ExtraUtils.enderQuarry,
		    	"EsE", 
		    	"CDC", 
		    	"pPp", 
		    	
		    	'E', enderObs,
		    	's', "treeSapling", 
		    	'M', new ItemStack(extrautils.ExtraUtils.decorative1, 1, 8), 
		    	'C', new ItemStack(extrautils.ExtraUtils.decorative1, 1, 11), 
		    	'D', new ItemStack(extrautils.ExtraUtils.decorative1, 1, 12), 
		    	'P', new ItemStack(ModItems.tppiMaterial, 1, 3), 
		    	'p', extrautils.ExtraUtils.enderThermicPump == null ? new ItemStack(extrautils.ExtraUtils.decorative1, 1, 12) : extrautils.ExtraUtils.enderThermicPump 
		    ));
		    
		    if (Loader.isModLoaded("ThermalExpansion"))
		    {
		    	enderObs.stackSize = 4;
		    	GameRegistry.addRecipe(new ShapedOreRecipe(enderObs, 
		    		" e ",
		    		"eOe",
		    		" e ",
		    	
		    		'e', TETweaks.getEnderium(),
		    		'O', Block.obsidian
		    	));
		    }
		    else
		    {
		    	enderObs.stackSize = 2;
		    	GameRegistry.addRecipe(new ShapedOreRecipe(enderObs, 
			    		" e ",
			    		"eOe",
			    		" e ",
			    	
			    		'e', Item.enderPearl,
			    		'O', Block.obsidian
			    ));
		    }
		}
		
		if(ConfigurationHandler.nerfRedstoneGen) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(extrautils.ExtraUtils.gen, 1, 4),
			    	"RRR", 
			    	"RGR", 
			    	"DFD", 
			    	
			    	'R', ModBlocks.tppiBlock, 
			    	'G', new ItemStack(extrautils.ExtraUtils.gen, 1, 2), 
			    	'D', Item.redstone, 
			    	'F', Block.furnaceIdle
			    ));
		}
		if(ConfigurationHandler.nerfEnderGen && !OreDictionary.getOres("blockEnderium").isEmpty()) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(extrautils.ExtraUtils.gen, 1, 3),
			    	"PPP", 
			    	"EBE", 
			    	"DFD", 
			    	
			    	'P', Item.enderPearl,
			    	'E', Item.eyeOfEnder, 
			    	'B', "blockEnderium", 
			    	'D', Item.redstone, 
			    	'F', Block.furnaceIdle
			    ));
		}
		
	}
	
	public static void reAddRecipeAfterLoad()
	{
	    GameRegistry.addRecipe(new ItemStack(extrautils.ExtraUtils.decorative1, 1, 5), "iii", "iii", "iii", 'i', extrautils.ExtraUtils.unstableIngot);
	}
}
