package tppitweaks.recipetweaks.modTweaks;


public class MagicropsTweaks
{
//    @RecipeRemoval(requiredModids = "magicalcrops")
//    public static void init()
//    {
//        TweakingRegistry.markItemForRecipeRemoval(mod_mCrops.mSeedsEssence.itemID, -1);
//        TweakingRegistry.markItemForRecipeRemoval(mod_mCrops.mSeedsDiamond.itemID, -1);
//        TweakingRegistry.markItemForRecipeRemoval(mod_mCrops.mSeedsEmerald.itemID, -1);
//        TweakingRegistry.markItemForRecipeRemoval(mod_mCrops.mSeedsXP.itemID, -1);
//        TweakingRegistry.markItemForRecipeRemoval(mod_mCrops.mSeedsThaumcraftShard.itemID, -1);
//        TweakingRegistry.markItemForRecipeRemoval(mod_mCrops.mSeedsCobalt.itemID, -1);
//        TweakingRegistry.markItemForRecipeRemoval(mod_mCrops.mSeedsArdite.itemID, -1);
//        TweakingRegistry.markItemForRecipeRemoval(mod_mCrops.mSeedsPlatinum.itemID, -1);
//        TweakingRegistry.markItemForRecipeRemoval(mod_mCrops.mSeedsUranium.itemID, -1);
//    }
//
//    @RecipeAddition(requiredModids = "magicalcrops", time = EventTime.INIT)
//    public static void registerOres()
//    {
//        if (ConfigurationHandler.registerMagicalCropsOre)
//        {
//            OreDictionary.registerOre("oreMCropsEssence", new ItemStack(mod_mCrops.BlockOreEssence));
//            OreDictionary.registerOre("oreMCropsNetherEssence", new ItemStack(mod_mCrops.BlockOreEssenceNether));
//        }
//    }
//
//    @RecipeAddition(requiredModids = "magicalcrops")
//    public static void addRecipes()
//    {
//        /* @formatter:off */
//		if (ConfigurationHandler.addEssenceSeedRecipe)
//		{
//			GameRegistry.addRecipe(new ItemStack(mod_mCrops.mSeedsEssence, 8), new Object[]{
//				"sRs",
//				"RSR",
//				"sRs",
//
//				'R', new ItemStack(mod_mCrops.MagicEssence, 1, 2),
//				'S', new ItemStack(mod_mCrops.MagicEssence, 1, 3),
//				's', Item.seeds
//			});
//		}
//		
//		/* params= result, amount, material, level of essence */
//		Object[][] items = new Object[][]{
//				{mod_mCrops.mSeedsDiamond, 2, Item.diamond, 4},
//				{mod_mCrops.mSeedsEmerald, 2, Item.emerald, 4},
//				{mod_mCrops.mSeedsThaumcraftShard, 2, "shardAir", 3},
//				{mod_mCrops.mSeedsThaumcraftShard, 2, "shardWater", 3},
//				{mod_mCrops.mSeedsThaumcraftShard, 2, "shardFire", 3},
//				{mod_mCrops.mSeedsThaumcraftShard, 2, "shardEarth", 3},
//				{mod_mCrops.mSeedsThaumcraftShard, 2, "shardOrdo", 3},
//				{mod_mCrops.mSeedsThaumcraftShard, 2, "shardEntropy", 3},
//				{mod_mCrops.mSeedsCobalt, 4, "ingotCobalt", 3},
//				{mod_mCrops.mSeedsArdite, 4, "ingotArdite", 4},
//				{mod_mCrops.mSeedsUranium, 4, "oreUranium", 4}
//		};
//		
//		/* Loops through arrays grabbing the correct info */
//		for (Object[] oa : items)
//		{
//			if ((oa[2] instanceof String && !OreDictionary.getOres((String) oa[2]).isEmpty()) || !(oa[2] instanceof String))
//				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((Item) oa[0], (Integer) oa[1]), "XEX", "ESE", "XEX", 'X', oa[2], 'E', new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 1, (Integer) oa[3]), 'S', Item.seeds));
//		}
//		
//		/* anything below here was too special of a recipe and had to be added manually */
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(mod_mCrops.mSeedsXP, 4),
//				"tEp", 
//				"EsE", 
//				"cEr", 
//				
//				't', "itemTear", 
//				'E', new ItemStack(mod_mCrops.MagicEssence, 1, 3), 
//				'p', "gemEnderPearl",
//				's', Item.seeds,
//				'c', Item.magmaCream,
//				'r', "stickBlaze"
//		));
//		
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(mod_mCrops.mSeedsPlatinum, 1),
//				"fEi", 
//				"EsE", 
//				"iEf", 
//				
//				'f', mod_mCrops.mSeedsNickel,
//				'E', new ItemStack(magicalcrops.mod_mCrops.MagicEssence, 1, 4),
//				'i', "ingotPlatinum",
//				's', Item.seeds
//		));
//		/* @formatter:on */
//    }
//
//    @RecipeAddition(requiredModids = { "magicalcrops", "IC2" })
//    public static void addIC2Recipes()
//    {
//        TweakingRegistry.addTweakedTooltip(Ic2Items.macerator.getItem(), Ic2Items.macerator.getItemDamage(), TweakingAction.ADDED, "Recipe for Essence Ore and",
//                "Nether Essence Ore");
//        IC2Tweaks.addMaceratorRecipe("oreMCropsEssence", 1, new ItemStack(mod_mCrops.MagicEssence, 8, 0));
//        IC2Tweaks.addMaceratorRecipe("oreMCropsNetherEssence", 1, new ItemStack(mod_mCrops.MagicEssence, 12, 0));
//    }
//
//    @RecipeAddition(requiredModids = { "magicalcrops", "ThermalExpansion" })
//    public static void addTERecipes()
//    {
//        ItemStack pulv = thermalexpansion.block.machine.BlockMachine.pulverizer;
//        TweakingRegistry.addTweakedTooltip(pulv.getItem(), pulv.getItemDamage(), TweakingAction.ADDED, "Recipes for Essence Ore and", "Nether Essence Ore");
//        TETweaks.addPulverizerRecipe(2400, OreDictionary.getOres("oreMCropsEssence").get(0), new ItemStack(mod_mCrops.MagicEssence, 8, 0), new ItemStack(
//                mod_mCrops.CropEssence, 1, 0), 5);
//        TETweaks.addPulverizerRecipe(2400, OreDictionary.getOres("oreMCropsNetherEssence").get(0), new ItemStack(mod_mCrops.MagicEssence, 12, 0), new ItemStack(
//                mod_mCrops.CropEssence, 1, 0), 10);
//    }
}
