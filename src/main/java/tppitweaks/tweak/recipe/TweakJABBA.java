package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class TweakJABBA {

    public static Item mover = GameRegistry.findItem("JABBA", "mover");
    public static Item moverDiamond = GameRegistry.findItem("JABBA", "moverDiamond");

    @RecipeRemoval(requiredModids = "JABBA")
    public static void init() {
        if (ConfigurationHandler.tweakJABBA)
            TweakingRegistry.markItemForRecipeRemoval(moverDiamond, 0, TweakingAction.CHANGED, "Recipe changed to balance", "against other ways to", "move spawners.");
    }

    @RecipeAddition(requiredModids = "JABBA")
    public static void addRecipes() {
        if (ConfigurationHandler.tweakJABBA) {
            GameRegistry.addRecipe(new ShapedOreRecipe(moverDiamond,
                    " N ",
                    "dWd",
                    " d ",

                    'W', mover,
                    'd', Items.diamond,
                    'N', Items.nether_star
            ));
        }
    }
}
