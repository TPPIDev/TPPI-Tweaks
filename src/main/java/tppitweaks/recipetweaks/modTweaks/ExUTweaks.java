package tppitweaks.recipetweaks.modTweaks;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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

import java.lang.reflect.Field;
import java.util.HashMap;

public class ExUTweaks
{
	@RecipeRemoval(requiredModids="ExtraUtilities")
	public static void init() {
		if(ConfigurationHandler.nerfEnderQuarry) {
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.exuQuarry, -1, TweakingAction.CHANGED, "Recipe changed to better", "balance with BC quarry recipe");
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.exuDecor1, 11, TweakingAction.CHANGED, "Recipe changed for ender quarry");
		}
		if(ConfigurationHandler.nerfRedstoneGen) {
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.exuGenerator, 4, TweakingAction.CHANGED, "Recipe requires 2xcompressed redstone", "because it produces tonnes of RF");
		}
		if(ConfigurationHandler.nerfEnderGen && !OreDictionary.getOres("blockEnderium").isEmpty()) {
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.exuGenerator, 3, TweakingAction.CHANGED, "Recipe requires enderium", "because it produces tonnes of RF");
		}
		if(ConfigurationHandler.nerfAngelRings) {
			TweakingRegistry.markItemForRecipeRemoval(ModItems.exuAngelRing, 0, TweakingAction.CHANGED, "Recipe balanced around other", "mod's creative flight abilities");
			TweakingRegistry.markItemForRecipeRemoval(ModItems.exuAngelRing, 1, TweakingAction.CHANGED, "Recipe balanced around other", "mod's creative flight abilities");
			TweakingRegistry.markItemForRecipeRemoval(ModItems.exuAngelRing, 2, TweakingAction.CHANGED, "Recipe balanced around other", "mod's creative flight abilities");
			TweakingRegistry.markItemForRecipeRemoval(ModItems.exuAngelRing, 3, TweakingAction.CHANGED, "Recipe balanced around other", "mod's creative flight abilities");
			TweakingRegistry.markItemForRecipeRemoval(ModItems.exuAngelRing, 4, TweakingAction.CHANGED, "Recipe balanced around other", "mod's creative flight abilities");
		}
		TweakingRegistry.markItemForRecipeRemoval(ModBlocks.exuCurtain, 0, TweakingAction.CHANGED, "Recipe changed to force black wool", "for compat with Malisis' Doors");

		if(ConfigurationHandler.harderDiamondSpikeRecipe) {
			TweakingRegistry.markItemForRecipeRemoval(ModBlocks.exuSpikeDiamond, 0, TweakingAction.CHANGED, "Recipe balanced to account", "for more valuable drops.");
		}

		if(ConfigurationHandler.enableSoulFragmentRecipes){
			TweakingRegistry.addTweakedTooltip(ModItems.exuSoul, 0, TweakingAction.NOTE, "Added secondary recipes for souls, which can","be auto-crafted to not lose health.");
		}
	}

	@SuppressWarnings("unchecked")
	@RecipeAddition(requiredModids="ExtraUtilities")
	public static void addRecipes() {
		if(ConfigurationHandler.nerfEnderQuarry) {

			ItemStack portal = new ItemStack(ModBlocks.exuPortal);
			ItemStack quadDirt = new ItemStack(ModBlocks.exuCompressed, 1, 11);
			ItemStack enderObs = new ItemStack(ModBlocks.exuDecor1, 1, 1);
			ItemStack magicalWood = new ItemStack(ModBlocks.exuDecor1, 1, 8);
			ItemStack enderCore = new ItemStack(ModBlocks.exuDecor1, 1, 11);
			ItemStack DECM = new ItemStack(ModBlocks.exuDecor1, 1, 12);

		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 2),
		    	"AWS",
		    	"PBP",
		    	"GEG",

		    	'A', ModItems.exuPickaxe,
		    	'W', ModItems.exuWand,
		    	'S', ModItems.exuShovel,
		    	'P', portal,
		    	'B', Blocks.iron_bars,
		    	'G', enderCore,
		    	'E', Loader.isModLoaded("ThermalExpansion") ? new ItemStack(ModBlocks.teEnergyCell, 1, 4) : Items.ender_pearl
		    ));

		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tppiMaterial, 1, 2),
		    	"SWA",
		    	"PBP",
		    	"GEG",

		    	'A', ModItems.exuPickaxe,
		    	'W', ModItems.exuWand,
		    	'S', ModItems.exuShovel,
		    	'P', portal,
		    	'B', Blocks.iron_bars,
		    	'G', enderCore,
		    	'E', Loader.isModLoaded("ThermalExpansion") ? new ItemStack(ModBlocks.teEnergyCell, 1, 4) : Items.ender_pearl
		    ));

		    GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.exuQuarry,
		    	"EQE",
		    	"CDC",
		    	"pPp",

		    	'E', enderObs,
		    	'Q', quadDirt,
		    	'M', magicalWood,
		    	'C', enderCore,
		    	'D', DECM,
		    	'P', new ItemStack(ModItems.tppiMaterial, 1, 2),
		    	'p', ModBlocks.exuPump == null ? new ItemStack(ModBlocks.exuDecor1, 1, 12) : ModBlocks.exuPump
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
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.exuGenerator, 1, 4),
			    	"RRR",
			    	"RGR",
			    	"DFD",

			    	'R', ModBlocks.tppiBlock,
			    	'G', new ItemStack(ModBlocks.exuGenerator, 1, 2),
			    	'D', Items.redstone,
			    	'F', Blocks.furnace
			    ));
		}
		if(ConfigurationHandler.nerfEnderGen && !OreDictionary.getOres("blockEnderium").isEmpty()) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.exuGenerator, 1, 3),
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
		if(ConfigurationHandler.nerfAngelRings) {

            ItemStack batWing = new ItemStack(GameRegistry.findItem("Reliquary", "mob_ingredient"), 0, 5);
            ItemStack bucketGold = new ItemStack(GameRegistry.findItem("TConstruct", "buckets"), 1, 1);

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.exuAngelRing, 1, 0),
					"GUG",
					"NSN",
					"WBW",

					'G', new ItemStack(Blocks.glass),
					'U', new ItemStack(ModItems.exuUnstableIngot),
					'N', new ItemStack(Items.nether_star),
					'S', new ItemStack(ModItems.exuSoul),
					'W', Loader.isModLoaded("Reliquary") ? batWing : new ItemStack(Items.feather),
					'B', Loader.isModLoaded("TConstruct") ? bucketGold : new ItemStack(Blocks.gold_block)
				));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.exuAngelRing, 1, 1),
					"FUF",
					"NSN",
					"WBW",

					'F', new ItemStack(Items.feather),
					'U', new ItemStack(ModItems.exuUnstableIngot),
					'N', new ItemStack(Items.nether_star),
					'S', new ItemStack(ModItems.exuSoul),
					'W', Loader.isModLoaded("Reliquary") ? batWing : new ItemStack(Items.feather),
					'B', Loader.isModLoaded("TConstruct") ? bucketGold : new ItemStack(Blocks.gold_block)
				));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.exuAngelRing, 1, 2),
					"DUP",
					"NSN",
					"WBW",

					'D', new ItemStack(Items.dye, 1, 5),
					'P', new ItemStack(Items.dye, 1, 9),
					'U', new ItemStack(ModItems.exuUnstableIngot),
					'N', new ItemStack(Items.nether_star),
					'S', new ItemStack(ModItems.exuSoul),
					'W', Loader.isModLoaded("Reliquary") ? batWing : new ItemStack(Items.feather),
					'B', Loader.isModLoaded("TConstruct") ? bucketGold : new ItemStack(Blocks.gold_block)
				));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.exuAngelRing, 1, 3),
					"LUL",
					"NSN",
					"WBW",

					'L', new ItemStack(Items.leather),
					'U', new ItemStack(ModItems.exuUnstableIngot),
					'N', new ItemStack(Items.nether_star),
					'S', new ItemStack(ModItems.exuSoul),
					'W', Loader.isModLoaded("Reliquary") ? batWing : new ItemStack(Items.feather),
					'B', Loader.isModLoaded("TConstruct") ? bucketGold : new ItemStack(Blocks.gold_block)
				));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.exuAngelRing, 1, 4),
					"GUG",
					"NSN",
					"WBW",

					'G', new ItemStack(Items.gold_nugget),
					'U', new ItemStack(ModItems.exuUnstableIngot),
					'N', new ItemStack(Items.nether_star),
					'S', new ItemStack(ModItems.exuSoul),
					'W', Loader.isModLoaded("Reliquary") ? batWing : new ItemStack(Items.feather),
					'B', Loader.isModLoaded("TConstruct") ? bucketGold : new ItemStack(Blocks.gold_block)
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

			OreDictionary.registerOre("blockUnstable", new ItemStack(ModBlocks.exuDecor1, 1, 5));
			OreDictionary.registerOre("burntquartz", new ItemStack(ModBlocks.exuDecor1, 1, 2));
			OreDictionary.registerOre("icestone", new ItemStack(ModBlocks.exuDecor1, 1, 3));
		}

		if (Loader.isModLoaded("gregtech_addon"))
			TPPITweaks.logger.info("Stahp, greg, I know. Blame Tema.");

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.exuCurtain, 12), "xx", "xx", "xx", 'x', "blockWoolBlack"));

		if (ConfigurationHandler.harderDiamondSpikeRecipe)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.exuSpikeDiamond, 3, 0),
					" D ",
					"DSD",
					"SBS",
					'D', new ItemStack(Items.diamond_sword),
					'S', new ItemStack(ModBlocks.exuSpikeGold),
					'B', Loader.isModLoaded("RandomThings") ? new ItemStack(ModItems.rtSpiritBinder) : new ItemStack(Items.nether_star)
			));
		}

		if (ConfigurationHandler.enableSoulFragmentRecipes)
		{

            Item heartCanister = GameRegistry.findItem("TConstruct", "heartCanister");
            Item galgadorianMetal = GameRegistry.findItem("StevesCarts", "BlockMetalStorage");

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.exuSoul),
					"GSG",
					"SDS",
					"GSG",
					'G', Loader.isModLoaded("TConstruct") ? new ItemStack(heartCanister, 1, 5) : new ItemStack(Blocks.emerald_block),
					'S', new ItemStack(Items.nether_star),
					'D', new ItemStack(Blocks.dragon_egg)
					));
			if (Loader.isModLoaded("StevesCarts"))
			{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.exuSoul),
                    "GSG",
                    "SBS",
                    "GSG",
                    'G', Loader.isModLoaded("TConstruct") ? new ItemStack(heartCanister, 1, 5) : new ItemStack(Blocks.emerald_block),
                    'S', new ItemStack(Items.nether_star),
                    'B', new ItemStack(galgadorianMetal, 1, 2)
                ));
			}
		}
	}

	@RecipeAddition(requiredModids="ExtraUtilities", time=EventTime.WORLD_LOAD)
	public static void doPostLoadRecipeAdditions()
	{
		ItemStack stableIngot = new ItemStack(ModItems.exuUnstableIngot);
		stableIngot.stackTagCompound = new NBTTagCompound();
		stableIngot.stackTagCompound.setBoolean("stable", true);

	    GameRegistry.addRecipe(new ItemStack(ModBlocks.exuDecor1, 1, 5), "iii", "iii", "iii", 'i', ModItems.exuUnstableIngot);
	    GameRegistry.addRecipe(stableIngot, "nnn", "nnn", "nnn", 'n', new ItemStack(ModItems.exuUnstableIngot, 1, 1));
	}
}
