package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class ElectricalAgeTweaks {
	@RecipeAddition(requiredModids= {"Eln", "Thaumcraft"})
	public static void addRecipes() {
		TweakingRegistry.addTweakedTooltip(GameRegistry.findItem("Eln", "Eln.sharedItem"), 520, TweakingAction.NOTE, "Added recipe to convert", "to Thaumcraft quicksilver.");
		TweakingRegistry.addTweakedTooltip(GameRegistry.findItem("Thaumcraft", "ItemResource"), 3, TweakingAction.NOTE, "Added recipe to convert","to Electrical Age mercury.");
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("Eln", "Eln.sharedItem"), 1, 520), new ItemStack(GameRegistry.findItem("Thaumcraft", "ItemResource"), 1, 3)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("Thaumcraft", "ItemResource"), 1, 3), new ItemStack(GameRegistry.findItem("Eln", "Eln.sharedItem"), 1, 520)));
	}
}
