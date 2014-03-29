package tppitweaks.recipetweaks.modTweaks;

import java.util.HashSet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class GTandMFRTweaks {

public static void addRecipes() {
    if(ConfigurationHandler.unnerfPlasticSheetRecipe) {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.plasticsheet, 1, 4),
            "PP ", 
            "PP ", 
            "   ", 
            
            'P', Item.rawplastic
            ));}}}