package tppitweaks.recipetweaks.modTweaks;

import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumic.tinkerer.common.ThaumicTinkerer;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import vazkii.botania.common.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class BotaniaTweaks {
	
	@RecipeAddition(requiredModids = {"Botania","ThaumicTinkerer"})
	public static void addSmokeyQuartzConversion()
	{
		TweakingRegistry.addTweakedTooltip(ThaumicTinkerer.registry.getFirstItemFromClass(thaumic.tinkerer.common.item.quartz.ItemDarkQuartz.class), 0, TweakingAction.NOTE, "Added recipe to convert","between the two types.");
		TweakingRegistry.addTweakedTooltip(ModItems.quartz, 0, TweakingAction.NOTE, "Added recipe to convert","between the two types.");
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.quartz, ThaumicTinkerer.registry.getFirstItemFromClass(thaumic.tinkerer.common.item.quartz.ItemDarkQuartz.class)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ThaumicTinkerer.registry.getFirstItemFromClass(thaumic.tinkerer.common.item.quartz.ItemDarkQuartz.class), ModItems.quartz));
	}
}
