package tppitweaks.recipetweaks.modTweaks;

import emasher.sockets.SocketsMod;
import net.minecraft.block.Block;
import tterrag.rtc.RecipeAddition.EventTime;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class EngToolboxTweaks
{
	@RecipeRemoval(time = EventTime.POST_INIT)
	public static void removeRecipes()
	{
		TweakingRegistry.markItemForRecipeRemoval(((Block) SocketsMod.mjAdapter).blockID, -1, TweakingAction.REMOVED, "Infinite energy bug.");
	}
}
