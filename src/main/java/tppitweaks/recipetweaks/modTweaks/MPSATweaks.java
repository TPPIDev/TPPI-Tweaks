package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.RecipeRemoval;
import tppitweaks.recipetweaks.TweakingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class MPSATweaks
{
	private static ItemStack solarPanel, magnet, computerChip;
	
	@RecipeRemoval(requiredModids="powersuitaddons")
	public static void init()
	{
		solarPanel = andrew.powersuits.common.AddonComponent.solarPanel;
		magnet = andrew.powersuits.common.AddonComponent.magnet;
		computerChip = andrew.powersuits.common.AddonComponent.computerChip;
				
		if (ConfigurationHandler.changeMPSARecipes)
		{
			TweakingRegistry.markItemForRecipeRemoval(solarPanel.itemID, 17);
			TweakingRegistry.markItemForRecipeRemoval(magnet.itemID, 18);
			TweakingRegistry.markItemForRecipeRemoval(computerChip.itemID, 19);
		}
	}
	
	@RecipeAddition(requiredModids="powersuitaddons")
	public static void addRecipes()
	{
		if (ConfigurationHandler.changeMPSARecipes)
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
}
