package tppitweaks.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import tppitweaks.config.ConfigurationHandler;
import appeng.api.Materials;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static TPPIBook tppiBook;
	public static TPPIMaterial tppiMaterial;
	
	public static void initItems() {
		tppiBook = new TPPIBook(ConfigurationHandler.bookID);
		tppiMaterial = new TPPIMaterial(ConfigurationHandler.materialID);
	}
	
	public static void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(tppiBook.getGuide(), Item.ingotIron, Item.paper, Item.paper, Item.paper);
		
		if(Loader.isModLoaded("AppliedEnergistics") && Loader.isModLoaded("StevesFactoryManager") && ConfigurationHandler.tweakSFM) {
			GameRegistry.addRecipe(new ItemStack(tppiMaterial.itemID, 1, 1),
		            new Object[] {
									"CSC",
									"SPS",
									"CSC",
		                                                    
									'C', Materials.matProcessorAdvanced.copy(),
									'P', Materials.matSilicon.copy(),
									'S', Materials.matFluxDust.copy(),
					});
				
			FurnaceRecipes.smelting().addSmelting(tppiMaterial.itemID, 1, new ItemStack(tppiMaterial), 0.1f);
		}
	}

}
