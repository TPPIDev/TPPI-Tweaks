package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ReliquaryTweaks
{
	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(xreliquary.items.XRItems.condensedPotion.itemID, 10);
	}
	
	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(xreliquary.items.XRItems.condensedPotion, 1, 10), new Object[]{
				"sap",
				"aap",
				"ppp",
				
				's', xreliquary.items.XRItems.condensedPotion,
				'a', Item.appleGold,
				'p', new ItemStack(Item.dyePowder, 1, 15)
		}));
	}
}
