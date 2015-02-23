package tppitweaks.item;

import cofh.api.modhelpers.ThermalExpansionHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import appeng.core.Api;
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
        if (Loader.isModLoaded("appliedenergistics2") && Loader.isModLoaded("StevesFactoryManager") && ConfigurationHandler.tweakSFM)
        {
            /* @formatter:off */
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tppiMaterial, 1, 0),
			        "CdC",
					"dsd",
					"CdC",
		                                                    
					'C', Api.INSTANCE.materials().materialCalcProcessor.stack(1),
					's', "itemSilicon",
					'd', Api.INSTANCE.materials().materialFluixDust.stack(1)
			));
			/* @formatter:on */
        }
        
        if (Loader.isModLoaded("ThermalExpansion") && ConfigurationHandler.harderDraconiumBlendRecipe)
        {
        	ThermalExpansionHelper.addTransposerFill(12000, new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 76), new ItemStack(ModItems.tppiMaterial, 1, 4), new FluidStack(FluidRegistry.getFluid("pyrotheum"), 1000), false);
        }
    }
}
