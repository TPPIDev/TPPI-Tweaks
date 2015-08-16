package tppitweaks.tweak.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeAddition.EventTime;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class TweakRailcraft {
    
    public static Item plate = GameRegistry.findItem("Railcraft", "part.plate");
    
    @RecipeAddition(requiredModids = {"Railcraft"}, time = EventTime.WORLD_LOAD)
    public static void registerOres() {

        ItemStack plateIron = new ItemStack(plate, 1, 0);
        ItemStack plateSteel = new ItemStack(plate, 1, 1);
        ItemStack plateTin = new ItemStack(plate, 1, 2);
        ItemStack plateCopper = new ItemStack(plate, 1, 3);

        GameRegistry.addRecipe(new ShapelessOreRecipe(plateIron, "plateIron"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(plateSteel, "plateSteel"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(plateTin, "plateTin"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(plateCopper, "plateCopper"));

        TweakingRegistry.addTweakedTooltip(plate, plateIron.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
        TweakingRegistry.addTweakedTooltip(plate, plateSteel.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
        TweakingRegistry.addTweakedTooltip(plate, plateTin.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
        TweakingRegistry.addTweakedTooltip(plate, plateCopper.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
    }
}
