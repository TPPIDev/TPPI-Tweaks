package tppitweaks.recipetweaks.modTweaks;

import java.util.Iterator;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import cpw.mods.fml.common.registry.GameRegistry;

public class ExUTweaks
{
	@SuppressWarnings("unchecked")
	public static void fixRecipes()
	{
		Iterator<IRecipe> iter = CraftingManager.getInstance().getRecipeList().listIterator();
		while (iter.hasNext())
		{
			IRecipe recipe = iter.next();

			ItemStack stack = recipe.getRecipeOutput();
			if (stack != null && stack.getItem() == extrautils.ExtraUtils.unstableIngot && stack.stackSize == 9)
			{
				iter.remove();
			}
			if (recipe != null && stack != null && stack.getItem().itemID == extrautils.ExtraUtils.decorative1Id && stack.getItemDamage() == 1)
			{
				ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;
				if (shapedRecipe.recipeItems.length > 0 && shapedRecipe.recipeItems[0] != null && recipe instanceof ShapedRecipes
						&& ((ItemStack) shapedRecipe.recipeItems[0]).getItem() == extrautils.ExtraUtils.unstableIngot)
				{
					iter.remove();
				}
			}
		}

		GameRegistry.addShapelessRecipe(new ItemStack(extrautils.ExtraUtils.unstableIngot, 9), new ItemStack(extrautils.ExtraUtils.decorative1, 1, 5));
		GameRegistry.addRecipe(new ItemStack(extrautils.ExtraUtils.decorative1, 1, 5), new Object[] { "iii", "iii", "iii",

		'i', new ItemStack(extrautils.ExtraUtils.unstableIngot) });
	}
}
