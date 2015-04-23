package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class BotaniaTweaks {
	
	@RecipeAddition(requiredModids = {"Botania","ThaumicTinkerer"})
	public static void addSmokeyQuartzConversion()
	{
        Item ttQuartz = GameRegistry.findItem("ThaumicTinkerer", "darkQuartzItem");
		TweakingRegistry.addTweakedTooltip(ttQuartz, 0, TweakingAction.NOTE, "Added recipe to convert to","the Botania version.");
		TweakingRegistry.addTweakedTooltip(ModItems.quartz, 0, TweakingAction.NOTE, "Added recipe to convert to","the ThaumicTinkerer version.");
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.quartz, ttQuartz));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ttQuartz, ModItems.quartz));
		
		if(ConfigurationHandler.enableOcelotAndWolfEggRecipes){
			TweakingRegistry.addTweakedTooltip(Items.spawn_egg, 98, TweakingAction.ADDED, "Added Runic Altar recipe to craft.");
			TweakingRegistry.addTweakedTooltip(Items.spawn_egg, 95, TweakingAction.ADDED, "Added Runic Altar recipe to craft.");
			BotaniaAPI.runeAltarRecipes.add(new RecipeRuneAltar(new ItemStack(Items.spawn_egg, 1, 98), 75000, new ItemStack(Items.egg), new ItemStack(Items.fish, 1, 0), new ItemStack(Items.fish, 1, 1), new ItemStack(Blocks.sapling, 1, 3), new ItemStack(GameRegistry.findItem("Botania", "rune"), 1, 8)));
			BotaniaAPI.runeAltarRecipes.add(new RecipeRuneAltar(new ItemStack(Items.spawn_egg, 1, 95), 75000, new ItemStack(Items.egg), new ItemStack(Items.bone, 1, 0), new ItemStack(Items.bone, 1, 0), new ItemStack(Items.stick, 1, 0), new ItemStack(GameRegistry.findItem("Botania", "rune"), 1, 8)));
		}
	}
}
