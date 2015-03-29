package tppitweaks.recipetweaks.modTweaks;

import mods.railcraft.common.items.ItemPlate.EnumPlate;
import mods.railcraft.common.items.RailcraftItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeAddition.EventTime;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class RailcraftTweaks
{
	@RecipeAddition(requiredModids={"Railcraft"}, time=EventTime.WORLD_LOAD)
	public static void registerOres()
	{
		// Please don't kill me CJ
		
		ItemStack plateIron = RailcraftItem.plate.getStack(EnumPlate.IRON);
		ItemStack plateSteel = RailcraftItem.plate.getStack(EnumPlate.STEEL);
		ItemStack plateTin = RailcraftItem.plate.getStack(EnumPlate.TIN);
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(plateIron, "plateIron"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(plateSteel, "plateSteel"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(plateTin, "plateTin"));
		
		TweakingRegistry.addTweakedTooltip(plateIron.getItem(), plateIron.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
		TweakingRegistry.addTweakedTooltip(plateIron.getItem(), plateSteel.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
		TweakingRegistry.addTweakedTooltip(plateIron.getItem(), plateTin.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
	}
}
