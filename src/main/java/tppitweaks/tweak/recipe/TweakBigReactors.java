package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class TweakBigReactors {

    public static ItemStack reactorCasing = new ItemStack(GameRegistry.findBlock("BigReactors", "BRReactorPart"));
    public static ItemStack turbineCasing = new ItemStack(GameRegistry.findBlock("BigReactors", "BRTurbinePart"));
    public static ItemStack reactorRod = new ItemStack(GameRegistry.findBlock("BigReactors", "YelloriumFuelRod"));
    public static ItemStack reactorGlass = new ItemStack(GameRegistry.findBlock("BigReactors", "BRMultiblockGlass"));

    @RecipeRemoval(requiredModids = "BigReactors")
    public static void init() {
        if (ConfigurationHandler.steelReactorCasings && !OreDictionary.getOres("ingotSteel").isEmpty()) {
            TweakingRegistry.markItemForRecipeRemoval(reactorCasing.getItem(), reactorCasing.getItemDamage(), TweakingAction.CHANGED, "Recipe requires steel to", "make the mod later game");
        }
        if (ConfigurationHandler.glassFuelRods) {
            TweakingRegistry.markItemForRecipeRemoval(reactorRod.getItem(), -1, TweakingAction.CHANGED, "Recipe requires hardened glass", "to make the mod later game");
        }
        if (ConfigurationHandler.twoReactorGlass) {
            TweakingRegistry.markItemForRecipeRemoval(reactorGlass.getItem(), 0, TweakingAction.CHANGED, "Recipe output doubled", "to offset the expensive glass");
            TweakingRegistry.markItemForRecipeRemoval(reactorGlass.getItem(), 1, TweakingAction.CHANGED, "Recipe output doubled", "to offset the expensive glass");
        }
    }

    @RecipeAddition(requiredModids = "BigReactors")
    public static void addRecipes() {
        if (ConfigurationHandler.steelReactorCasings && !OreDictionary.getOres("ingotSteel").isEmpty()) {
            /* @formatter:off */

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reactorCasing.getItem(), 4),
                    "ICI",
                    "CUC",
                    "ICI",

                    'I', "ingotSteel",
                    'C', "ingotGraphite",
                    'U', "ingotYellorium"
            ));
        }
        if (ConfigurationHandler.glassFuelRods) {
            GameRegistry.addRecipe(new ShapedOreRecipe(reactorRod,
                    "ICI",
                    "GUG",
                    "ICI",

                    'I', reactorGlass,
                    'C', "ingotIron",
                    'U', "ingotYellorium",
                    'G', "ingotGraphite"
            ));
        }
        if (ConfigurationHandler.twoReactorGlass) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reactorGlass.getItem(), 2),
                    "gCg",

                    'g', "blockGlassHardened",
                    'C', reactorCasing
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reactorGlass.getItem(), 2),
                    "gCg",

                    'g', "glassReinforced",
                    'C', reactorCasing
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reactorGlass.getItem(), 2, 1),
                    "gCg",

                    'g', "blockGlassHardened",
                    'C', new ItemStack(turbineCasing.getItem(), 1, 0)
            ));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reactorGlass.getItem(), 2, 1),
                    "gCg",

                    'g', "glassReinforced",
                    'C', new ItemStack(turbineCasing.getItem(), 1, 0)
            ));
			/* @formatter:on */
        }
    }
}
