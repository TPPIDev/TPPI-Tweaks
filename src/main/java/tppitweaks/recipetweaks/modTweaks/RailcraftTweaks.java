package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeAddition.EventTime;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class RailcraftTweaks
{
	@RecipeAddition(requiredModids={"Railcraft"}, time=EventTime.WORLD_LOAD)
	public static void registerOres()
	{
		// Please don't kill me CJ

        ItemStack plateIron = new ItemStack(GameRegistry.findItem("Railcraft", "part.plate"), 1, 0);
        ItemStack plateSteel = new ItemStack(GameRegistry.findItem("Railcraft", "part.plate"), 1, 1);
        ItemStack plateTin = new ItemStack(GameRegistry.findItem("Railcraft", "part.plate"), 1, 2);

		GameRegistry.addRecipe(new ShapelessOreRecipe(plateIron, "plateIron"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(plateSteel, "plateSteel"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(plateTin, "plateTin"));
		
		TweakingRegistry.addTweakedTooltip(plateIron.getItem(), plateIron.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
		TweakingRegistry.addTweakedTooltip(plateIron.getItem(), plateSteel.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
		TweakingRegistry.addTweakedTooltip(plateIron.getItem(), plateTin.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
	}
}
