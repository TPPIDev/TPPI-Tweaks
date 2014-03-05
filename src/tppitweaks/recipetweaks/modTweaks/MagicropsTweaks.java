package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class MagicropsTweaks
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(magicalcrops.mod_mCrops.mSeedsDiamond.itemID, -1);
		TweakerBase.markItemForRecipeRemoval(magicalcrops.mod_mCrops.mSeedsEmerald.itemID, -1);
		TweakerBase.markItemForRecipeRemoval(magicalcrops.mod_mCrops.mSeedsXP.itemID, -1);
		TweakerBase.markItemForRecipeRemoval(magicalcrops.mod_mCrops.mSeedsThaumcraftShard.itemID, -1);
		TweakerBase.markItemForRecipeRemoval(magicalcrops.mod_mCrops.mSeedsCobalt.itemID, -1);
		TweakerBase.markItemForRecipeRemoval(magicalcrops.mod_mCrops.mSeedsArdite.itemID, -1);
		TweakerBase.markItemForRecipeRemoval(magicalcrops.mod_mCrops.mSeedsPlatinum.itemID, -1);
		TweakerBase.markItemForRecipeRemoval(magicalcrops.mod_mCrops.mSeedsUranium.itemID, -1);
	}

	public static void registerOres()
	{
		if (ConfigurationHandler.registerMagicalCropsOre)
		{
			OreDictionary.registerOre("oreMCropsEssence", new ItemStack(magicalcrops.mod_mCrops.BlockOreEssence));
			OreDictionary.registerOre("oreMCropsNetherEssence", new ItemStack(magicalcrops.mod_mCrops.BlockOreEssenceNether));
		}
	}

	public static void addRecipes()
	{
		if (ConfigurationHandler.addEssenceSeedRecipe)
		{
			GameRegistry.addRecipe(new ItemStack(magicalcrops.mod_mCrops.mSeedsEssence, 8), new Object[]{
				"sRs",
				"RSR",
				"sRs",

				'R', new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 1, 2),
				'S', new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 1, 3),
				's', Item.seeds
			});
		}
		
		/* params= result, amount, material, level of essence */
		Object[][] items = new Object[][]{
				{magicalcrops.mod_mCrops.mSeedsDiamond, 2, Item.diamond, 4},
				{magicalcrops.mod_mCrops.mSeedsEmerald, 2, Item.emerald, 4},
				{magicalcrops.mod_mCrops.mSeedsThaumcraftShard, 2, "shardAir", 3},
				{magicalcrops.mod_mCrops.mSeedsThaumcraftShard, 2, "shardWater", 3},
				{magicalcrops.mod_mCrops.mSeedsThaumcraftShard, 2, "shardFire", 3},
				{magicalcrops.mod_mCrops.mSeedsThaumcraftShard, 2, "shardEarth", 3},
				{magicalcrops.mod_mCrops.mSeedsThaumcraftShard, 2, "shardOrdo", 3},
				{magicalcrops.mod_mCrops.mSeedsThaumcraftShard, 2, "shardEntropy", 3},
				{magicalcrops.mod_mCrops.mSeedsCobalt, 4, "ingotCobalt", 3},
				{magicalcrops.mod_mCrops.mSeedsArdite, 4, "ingotArdite", 4},
				{magicalcrops.mod_mCrops.mSeedsUranium, 4, "oreUranium", 4}
		};
		
		/* Loops through arrays grabbing the correct info */
		for (Object[] oa : items)
		{
			if ((oa[2] instanceof String && !OreDictionary.getOres((String) oa[2]).isEmpty()) || !(oa[2] instanceof String))
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((Item) oa[0], (Integer) oa[1]), "XEX", "ESE", "XEX", 'X', oa[2], 'E', new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 1, (Integer) oa[3]), 'S', Item.seeds));
		}
		
		/* anything below here was too special of a recipe and had to be added manually */
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(magicalcrops.mod_mCrops.mSeedsXP, 4),
				"tEp", 
				"EsE", 
				"cEr", 
				
				't', "itemTear", 
				'E', new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 1, 3), 
				'p', "gemEnderPearl",
				's', Item.seeds,
				'c', Item.magmaCream,
				'r', "stickBlaze"
		));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(magicalcrops.mod_mCrops.mSeedsPlatinum, 1),
				"fEi", 
				"EsE", 
				"iEf", 
				
				'f', magicalcrops.mod_mCrops.mSeedsNickel,
				'E', new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 1, 4),
				'i', "ingotPlatinum",
				's', Item.seeds
		));		
	}
}
