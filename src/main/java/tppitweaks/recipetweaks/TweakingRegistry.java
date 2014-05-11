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
	private static HashMap<Integer, HashMap<Integer, String[]>> removalReasons = new HashMap<Integer, HashMap<Integer,String[]>>();
	
	public enum TweakingAction
	{
		REMOVED("Removed:"),
		CHANGED("Recipe Changed:"),
		ADDED("Added:"), 
		NOTE("Note: ");

		private String name;
		
		TweakingAction(String name)
		{
			this.name = name;
		}
		
		@Override
		public String toString()
		{
			return name;
		}
	}
	
	public static void markItemForRecipeRemoval(int id, int damage) {	
		if(!recipesToRemove.containsKey(id)) {
			recipesToRemove.put(id, new HashSet<Integer>());
		}
		recipesToRemove.get(id).add(damage);
	}
	
	public static void markItemForRecipeRemoval(int id, int damage, TweakingAction action, String... details) {	
		markItemForRecipeRemoval(id, damage);
		addTweakedTooltip(id, damage, action, details);
	}
	
	public static void addTweakedTooltip(int id, int damage, TweakingAction action, String... details)
	{
		if (!removalReasons.containsKey(id))
		{
			removalReasons.put(id, new HashMap<Integer, String[]>());
		}
		
		String[] lines = new String[details.length + 1];
		lines[0] = action.toString();
		
		for (int i = 1; i < lines.length; i++)
			lines[i] = details[i - 1];
		
		removalReasons.get(id).put(damage, lines);
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
	
	/**
	 * Whether or not this ID (and damage value) has been removed
	 * @param data <br>- [0] - ID
	 * 			   <br>- [1] - damage (can be ommitted)<p>
	 * 						No further data will be analyzed
	 */
	public static boolean contains(int id, int damage)
	{
		return removalReasons.get(id) != null && removalReasons.get(id).containsKey(damage);
	}
	
	/**
	 * The tooltip associated with this ID/damage
	 * @param id
	 * @param damage - no sensitivity = -1
	 * @return null if no tooltip associated
	 */
	public static String[] getTooltip(int id, int damage)
	{
		if (contains(id, damage))
		{
			return removalReasons.get(id).get(damage);
		}
		else if (contains(id, -1))
		{
			return removalReasons.get(id).get(-1);
		}
		return null;
	}
}