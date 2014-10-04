package tppitweaks.recipetweaks.modTweaks;

import tterrag.rtc.RecipeAddition.EventTime;
import tterrag.rtc.RecipeRemoval;

public class EngToolboxTweaks
{
	@RecipeRemoval(time = EventTime.POST_INIT)
	public static void removeRecipes()
	{
//		TweakingRegistry.markItemForRecipeRemoval(((Block) SocketsMod.mjAdapter).blockID, -1, TweakingAction.REMOVED, "Infinite energy bug.");
	}
}
