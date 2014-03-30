package tppitweaks.recipetweaks.modTweaks;

import java.util.HashSet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class GTandMFRTweaks
{

public static void addRecipes() {
    if(ConfigurationHandler.unnerfPlasticSheetRecipe) {
        TETweaks.addRecipes();
        ItemStack PlasticPlate = OreDictionary.getOres("platePlastic").get(0).copy();
        PlasticPlate.stackSize = 4;
        GameRegistry.addRecipe(new ShapedOreRecipe(PlasticPlate, new Object[] {
            "PP ", 
            "PP ", 
            "   ", 
            
            'P', "dustPlastic"
            }));
        }}}