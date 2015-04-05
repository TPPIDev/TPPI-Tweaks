package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class BuildcraftTweaks {
    static Item machineBlock = GameRegistry.findItem("BuildCraft|Factory", "machineBlock");
    static Item robot = GameRegistry.findItem("BuildCraft|Silicon", "robot");
    static Item drillDiamond = GameRegistry.findItem("IC2", "itemToolDDrill");

    @RecipeRemoval(requiredModids={"IC2", "BuildCraft|Factory", "BuildCraft|Silicon"})
    public static void init(){
        if(ConfigurationHandler.harderQuarryRecipe){
            TweakingRegistry.markItemForRecipeRemoval(machineBlock, 0, TweakingAction.CHANGED, "Tweaked recipe to be", "more balanced with other", "forms of automated mining");
        }
    }

    @RecipeAddition(requiredModids={"IC2","BuildCraft|Factory", "BuildCraft|Silicon"})
    public static void addRecipes(){
        if(ConfigurationHandler.harderQuarryRecipe) {
            GameRegistry.addRecipe(new ShapedOreRecipe(machineBlock,
                    "IRI",
                    "GIG",
                    "DAD",
                    'I', "gearIron",
                    'R', robot,
                    'G', "gearGold",
                    'D', "gearDiamond",
                    'A', new ItemStack(drillDiamond, 1, OreDictionary.WILDCARD_VALUE)
            ));
        }
    }
}
