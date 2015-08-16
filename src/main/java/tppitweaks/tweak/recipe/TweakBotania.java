package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeRuneAltar;

public class TweakBotania {

    public static ItemStack ttDarkQuartz = new ItemStack(GameRegistry.findItem("ThaumicTinkerer", "darkQuartzItem"));
    public static ItemStack bDarkQuartz = new ItemStack(GameRegistry.findItem("Botania", "quartz"));

    @RecipeAddition(requiredModids = {"Botania", "ThaumicTinkerer"})
    public static void addSmokeyQuartzConversion() {
        TweakingRegistry.addTweakedTooltip(ttDarkQuartz.getItem(), 0, TweakingAction.NOTE, "Added recipe to convert to", "the Botania version.");
        TweakingRegistry.addTweakedTooltip(bDarkQuartz.getItem(), 0, TweakingAction.NOTE, "Added recipe to convert to", "the ThaumicTinkerer version.");

        GameRegistry.addRecipe(new ShapelessOreRecipe(bDarkQuartz, ttDarkQuartz));
        GameRegistry.addRecipe(new ShapelessOreRecipe(ttDarkQuartz, bDarkQuartz));

        if (ConfigurationHandler.enableOcelotAndWolfEggRecipes) {
            TweakingRegistry.addTweakedTooltip(Items.spawn_egg, 98, TweakingAction.ADDED, "Added Runic Altar recipe to craft.");
            TweakingRegistry.addTweakedTooltip(Items.spawn_egg, 95, TweakingAction.ADDED, "Added Runic Altar recipe to craft.");
            BotaniaAPI.runeAltarRecipes.add(new RecipeRuneAltar(new ItemStack(Items.spawn_egg, 1, 98), 75000, new ItemStack(Items.egg), new ItemStack(Items.fish, 1, 0), new ItemStack(Items.fish, 1, 1), new ItemStack(Blocks.sapling, 1, 3), new ItemStack(GameRegistry.findItem("Botania", "rune"), 1, 8)));
            BotaniaAPI.runeAltarRecipes.add(new RecipeRuneAltar(new ItemStack(Items.spawn_egg, 1, 95), 75000, new ItemStack(Items.egg), new ItemStack(Items.bone, 1, 0), new ItemStack(Items.bone, 1, 0), new ItemStack(Items.stick, 1, 0), new ItemStack(GameRegistry.findItem("Botania", "rune"), 1, 8)));
        }
    }
}
