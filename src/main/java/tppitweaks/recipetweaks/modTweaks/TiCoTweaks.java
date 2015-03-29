package tppitweaks.recipetweaks.modTweaks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tconstruct.armor.TinkerArmor;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.TweakingRegistry;

public class TiCoTweaks
{

	@RecipeAddition(requiredModids = "TConstruct")
	public static void addRecipes()
	{
		TweakingRegistry.addTweakedTooltip(TinkerArmor.heartCanister, 5, TweakingRegistry.TweakingAction.ADDED, "Added green heart",  "canister recipe");
		TweakingRegistry.addTweakedTooltip(TinkerArmor.heartCanister, 6, TweakingRegistry.TweakingAction.ADDED, "Added green heart",  "canister recipe");

		if(ConfigurationHandler.addGreenHeartRecipe) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(TinkerArmor.heartCanister, 1, 5),
					"BBB",
					"BHB",
					"BBB",

					'B', Blocks.emerald_block,
					'H', new ItemStack(TinkerArmor.heartCanister, 1, 3)
				));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TinkerArmor.heartCanister, 1, 6),
					new ItemStack(TinkerArmor.heartCanister, 1, 5),
					new ItemStack(TinkerArmor.heartCanister, 1, 4),
					Items.nether_star
				));
		}
	}

}
