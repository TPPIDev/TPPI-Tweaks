package tppitweaks.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
    public static TPPIMaterial tppiMaterial;

    public static void initItems()
    {
        tppiMaterial = new TPPIMaterial();
        GameRegistry.registerItem(tppiMaterial, "tppiMaterial");
    }

    public static void registerRecipes()
    {
        if (Loader.isModLoaded("appliedenergistics2") && Loader.isModLoaded("StevesFactoryManager") && ConfigurationHandler.tweakSFM) {
            /* @formatter:off */
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tppiMaterial, 1, 0),
			        "CdC",
					"dsd",
					"CdC",
		                                                    
					'C', new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial"), 1, 23),
					's', "itemSilicon",
					'd', new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial"), 1, 8)
			));
			/* @formatter:on */
        }
        
        /*  --Commented out in case item is needed for future tweak
        if (Loader.isModLoaded("ThermalExpansion")
        {
        	ThermalExpansionHelper.addTransposerFill(12000, new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 76), new ItemStack(ModItems.tppiMaterial, 1, 4), new FluidStack(FluidRegistry.getFluid("pyrotheum"), 1000), false);
        }
        */
    }
}
