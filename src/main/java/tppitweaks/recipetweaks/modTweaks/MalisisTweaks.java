package tppitweaks.recipetweaks.modTweaks;

import net.malisis.doors.MalisisDoors;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class MalisisTweaks
{
    @RecipeRemoval(requiredModids = "malisisdoors")
    public static void removeRecipes()
    {
        TweakingRegistry.markItemForRecipeRemoval(MalisisDoors.Items.curtainsItem, 0, TweakingAction.CHANGED, "Recipe changed to force red wool", "for compat with Extra Utils");
    }

    @RecipeAddition(requiredModids = "malisisdoors")
    public static void addRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(MalisisDoors.Items.curtainsItem, "xx", "xx", "xx", 'x', "blockWoolRed"));
    }
}
