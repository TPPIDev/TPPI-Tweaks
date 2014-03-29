package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class MPSATweaks
{
	private static ItemStack solarPanel, magnet, computerChip;
	
	public static void init()
	{
		solarPanel = andrew.powersuits.common.AddonComponent.solarPanel;
		magnet = andrew.powersuits.common.AddonComponent.magnet;
		computerChip = andrew.powersuits.common.AddonComponent.computerChip;
		
		//removeRecipes();
		
		TweakerBase.markItemForRecipeRemoval(solarPanel.itemID, 17);
		TweakerBase.markItemForRecipeRemoval(magnet.itemID, 18);
		TweakerBase.markItemForRecipeRemoval(computerChip.itemID, 19);
	}
	
	/*@SuppressWarnings("unchecked")
	private static void removeRecipes()
	{
		Iterator<IRecipe> iter = CraftingManager.getInstance().getRecipeList().listIterator();
		
		while (iter.hasNext())
		{
			IRecipe recipe = iter.next();
			if (recipe.getRecipeOutput() != null && recipe.getRecipeOutput().itemID == solarPanel.itemID && matchesDamage(recipe))
			{
				iter.remove();
			}
		}
	}
	
	private static boolean matchesDamage(IRecipe recipe)
	{
		int[] damages = {17,18,19};
		for (int i : damages)
		{
			if (i == recipe.getRecipeOutput().getItemDamage())
				return true;
		}
		return false;
	}*/
	
	public static void addRecipes()
	{
		ItemStack powerCoilGold = thermalexpansion.item.TEItems.powerCoilGold;
		ItemStack powerCoilSilver = thermalexpansion.item.TEItems.powerCoilSilver;
		ItemStack powerCoilElectrum = thermalexpansion.item.TEItems.powerCoilElectrum;
		
		GameRegistry.addRecipe(new ShapedOreRecipe(solarPanel, new Object[]{
				"GGG",
				"PPP",
				"WWW",
				
				'G', "glassHardened",
				'P', "ingotPlatinum",
				'W', "componentWiring"
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(magnet, new Object[]{
				"IRI",
				"SSS",
				"sRs",
				
				'I', "ingotInvar",
				'R', powerCoilGold,
				'S', "componentSolenoid",
				's', "ingotSteel"
				
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(computerChip, new Object[]{
				"SC ",
				"RIT",
				"WWW",
				
				'S', "componentSolenoid",
				'C', powerCoilElectrum,
				'R', powerCoilGold,
				'I', new ItemStack(buildcraft.BuildCraftSilicon.redstoneChipset, 1, 1),
				'T', powerCoilSilver,
				'W', "componentWiring"
		}));
	}
}
