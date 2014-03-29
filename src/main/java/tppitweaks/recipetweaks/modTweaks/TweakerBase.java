package tppitweaks.recipetweaks.modTweaks;

import java.util.HashMap;
import java.util.HashSet;

public class TweakerBase
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
	
}