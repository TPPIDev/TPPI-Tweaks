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

    public static ItemStack quarry = new ItemStack(GameRegistry.findItem("Buildcraft|Builders", "machineBlock"));
    public static ItemStack robot = new ItemStack(GameRegistry.findItem("Buildcraft|Robotics", "robot"));

    @RecipeRemoval(requiredModids = {"BuildCraft|Robotics", "BuildCraft|Silicon", "BuildCraft|Builders"})
    public static void init() {
        if (ConfigurationHandler.harderQuarryRecipe) {
            TweakingRegistry.markItemForRecipeRemoval(GameRegistry.findItem("BuildCraft|Factory", "machineBlock"), 0, TweakingAction.CHANGED, "Moved farther down the", "BuildCraft tech tree");
        }
    }

    @RecipeAddition(requiredModids = {"BuildCraft|Robotics", "BuildCraft|Silicon", "BuildCraft|Builders"})
    public static void addRecipes() {
        if (ConfigurationHandler.harderQuarryRecipe) {
            GameRegistry.addRecipe(new ShapedOreRecipe(quarry,
                    "IRI",
                    "GIG",
                    "DAD",
                    'I', "gearIron",
                    'R', "crystalRedstone",
                    'G', "gearGold",
                    'D', "gearDiamond",
                    'A', robot
            ));
        }
    }
}
