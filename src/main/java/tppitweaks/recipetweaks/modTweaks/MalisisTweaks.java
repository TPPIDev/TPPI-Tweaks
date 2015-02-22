package tppitweaks.recipetweaks.modTweaks;

import net.malisis.doors.MalisisDoors;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
        
        TweakingRegistry.markItemForRecipeRemoval(GameRegistry.findItem("malisisdoors", "rustyLadder"), 0, TweakingAction.CHANGED, "Recipe change to remove conflict", "with Electrical Age wire.");
    }

    @RecipeAddition(requiredModids = "malisisdoors")
    public static void addRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(MalisisDoors.Items.curtainsItem, "xx", "xx", "xx", 'x', "blockWoolRed"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MalisisDoors.Items.curtainsItem, 2, 0), 
        		"xxx", 
        		"   ",
        		"xxx",
        		'x', "ingotIron"));
    }
}
