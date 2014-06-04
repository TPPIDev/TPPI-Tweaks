package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.RecipeRemoval;
import tppitweaks.recipetweaks.TweakingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ReliquaryTweaks
{
	@RecipeRemoval(requiredModids="xreliquary")
	public static void init()
	{
/*		if (ConfigurationHandler.harderLillipadRecipe)
*			TweakingRegistry.markItemForRecipeRemoval(xreliquary.items.XRItems.condensedPotion.itemID, 10); */
	}
	
	@RecipeAddition(requiredModids="xreliquary")
	public static void addRecipes()
	{
/*		if (ConfigurationHandler.harderLillipadRecipe)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(xreliquary.items.XRItems.condensedPotion, 1, 10),
					"sap",
					"aap",
					"ppp",

					's', xreliquary.items.XRItems.condensedPotion,
					'a', Item.appleGold,
					'p', new ItemStack(Item.dyePowder, 1, 15)
			));
		} */
	}
}
