package tppitweaks.item;

import net.minecraft.item.ItemStack;
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
        if (Loader.isModLoaded("AppliedEnergistics") && Loader.isModLoaded("StevesFactoryManager") && ConfigurationHandler.tweakSFM)
        {
            // FIXME AppEng recipes
            /* @formatter:off */
//			GameRegistry.addRecipe(new ItemStack(tppiMaterial.itemID, 1, 1),
//		            new Object[] {
//									"CSC",
//									"SPS",
//									"CSC",
//		                                                    
//									'C', Materials.matProcessorAdvanced.copy(),
//									'P', Materials.matSilicon.copy(),
//									'S', Materials.matFluxDust.copy(),
//					});
			/* @formatter:on */
			
            GameRegistry.addSmelting(new ItemStack(tppiMaterial, 1, 1), new ItemStack(tppiMaterial), 0.1f);
        }
    }
}
