package tppitweaks.recipetweaks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class TweakingRegistry
{
	private static HashMap<Integer, HashSet<Integer>> recipesToRemove = new HashMap<Integer, HashSet<Integer>>();
	
	public static void markItemForRecipeRemoval(int id, int meta) {	
		if(!recipesToRemove.containsKey(id)) {
			recipesToRemove.put(id, new HashSet<Integer>());
		}
		recipesToRemove.get(id).add(meta);
	}
	
	public static HashSet<Integer> getDamageValuesToRemove(int itemID) {
		return recipesToRemove.get(itemID);
	}
	
	@SuppressWarnings({ "unchecked" })
	static void removeRecipes()
	{
		ListIterator<IRecipe> iterator = CraftingManager.getInstance().getRecipeList().listIterator();
		while (iterator.hasNext())
		{
			IRecipe r = iterator.next();
			if (canRemoveRecipe(r))
			{
				iterator.remove();
			}
		}
	}
	
	private static boolean canRemoveRecipe(IRecipe r)
	{
		try
		{
			ItemStack output = r.getRecipeOutput();
			HashSet<Integer> validMetas = getDamageValuesToRemove(output.itemID);
			return validMetas.contains(-1) || validMetas.contains(output.getItemDamage());
		}
		catch (Throwable e)
		{
			return false;
		}
	}
}