package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class TweakBuildcraft {

    public static ItemStack diamondDrill = new ItemStack(GameRegistry.findItem("IC2", "itemToolDDrill"), 1, -1);

    @RecipeRemoval(requiredModids = {"IC2", "BuildCraft|Factory"})
    public static void init() {
        if (ConfigurationHandler.harderQuarryRecipe) {
            TweakingRegistry.markItemForRecipeRemoval(GameRegistry.findItem("BuildCraft|Factory", "machineBlock"), 0, TweakingAction.CHANGED, "Replaced Diamond Pickaxe with Drill", "as temporary cost adjustment.");
        }
    }

    @RecipeAddition(requiredModids = {"IC2", "BuildCraft|Factory"})
    public static void addRecipes() {
        if (ConfigurationHandler.harderQuarryRecipe) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BuildCraft|Factory", "machineBlock")),
                    "IRI",
                    "GIG",
                    "DAD",
                    'I', "gearIron",
                    'R', "dustRedstone",
                    'G', "gearGold",
                    'D', "gearDiamond",
                    'A', diamondDrill
            ));
        }
    }
}
