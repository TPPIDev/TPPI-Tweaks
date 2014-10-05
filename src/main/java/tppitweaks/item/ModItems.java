package tppitweaks.item;

import net.minecraft.item.ItemStack;
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
        if (Loader.isModLoaded("AppliedEnergistics") && Loader.isModLoaded("StevesFactoryManager") && ConfigurationHandler.tweakSFM)
        {
            /* @formatter:off */
			GameRegistry.addRecipe(new ItemStack(tppiMaterial, 1, 1),
			        "CSC",
					"SPS",
					"CSC",
		                                                    
					'C', Api.instance.materials().materialCalcProcessor.stack(1),
					'P', Api.instance.materials().materialSilicon.stack(1),
					'S', Api.instance.materials().materialFluixDust.stack(1)
			);
			/* @formatter:on */
			
            GameRegistry.addSmelting(new ItemStack(tppiMaterial, 1, 1), new ItemStack(tppiMaterial), 0.1f);
        }
    }
}
