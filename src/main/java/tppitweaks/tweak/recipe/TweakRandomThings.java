package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class TweakRandomThings {

    public static Item itemCollector = GameRegistry.findItem("RandomThings", "itemCollector");
    public static Item advItemCollector = GameRegistry.findItem("RandomThings", "advancedItemCollector");
    public static Item ingredient = GameRegistry.findItem("RandomThings", "ingredient");

    @RecipeRemoval(requiredModids = "RandomThings")
    public static void init() {
        if (ConfigurationHandler.harderActivatorRecipe)
            TweakingRegistry.markItemForRecipeRemoval(advItemCollector, 0, TweakingAction.CHANGED, "Made recipe more costly.");
    }

    @RecipeAddition(requiredModids = "RandomThings")
    public static void addRecipes() {
        if (ConfigurationHandler.harderActivatorRecipe)
            GameRegistry.addRecipe(new ShapedOreRecipe(advItemCollector,
                    " D ",
                    "ECE",
                    "   ",

                    'D', new ItemStack(ingredient, 1, 6),
                    'E', new ItemStack(ingredient, 1, 3),
                    'C', itemCollector
            ));
    }
}
