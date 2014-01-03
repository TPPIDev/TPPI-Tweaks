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
		GameRegistry.addShapelessRecipe(tppiBook.getBook(), Item.ingotIron, Item.paper, Item.paper, Item.paper);
		
		if(Loader.isModLoaded("AppliedEnergistics") && Loader.isModLoaded("StevesFactoryManager") && ConfigurationHandler.tweakSFM) {
			GameRegistry.addRecipe(tppiMaterial.getUncookedProcessor(),
		            new Object[] {
									"CSC",
									"SPS",
									"CSC",
		                                                    
									'C', Materials.matFluxDust.copy(),
									'P', new ItemStack(Item.dyePowder, 1, 4),
									'S', Materials.matProcessorAdvanced.copy(),
					});
				
			FurnaceRecipes.smelting().addSmelting(tppiMaterial.itemID, 1, tppiMaterial.getCookedProcessor(), 0.1f);
		}
	}

}
