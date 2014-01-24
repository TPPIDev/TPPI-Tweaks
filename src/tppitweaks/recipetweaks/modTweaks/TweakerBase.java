package tppitweaks.recipetweaks.modTweaks;

import java.util.HashMap;

public class TweakerBase
{
	private static HashMap<Integer, Integer> recipesToRemove = new HashMap<Integer, Integer>();
	
	public static void markItemForRecipeRemoval(int id, int meta) {		
		recipesToRemove.put(id, meta);
	}
	
	public static int getDamageValueToRemove(int itemID) {
		return recipesToRemove.get(itemID);
	}
	
}
