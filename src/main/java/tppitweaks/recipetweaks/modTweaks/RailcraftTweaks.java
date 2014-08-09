package tppitweaks.recipetweaks.modTweaks;

import mods.railcraft.common.items.ItemPlate;
import mods.railcraft.common.items.ItemPlate.EnumPlate;
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
		
		ItemStack plateIron = ItemPlate.getPlate(EnumPlate.IRON);
		ItemStack plateSteel = ItemPlate.getPlate(EnumPlate.STEEL);
		ItemStack plateTin = ItemPlate.getPlate(EnumPlate.TIN);
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(plateIron, "plateIron"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(plateSteel, "plateSteel"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(plateTin, "plateTin"));
		
		TweakingRegistry.addTweakedTooltip(plateIron.itemID, plateIron.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
		TweakingRegistry.addTweakedTooltip(plateIron.itemID, plateSteel.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
		TweakingRegistry.addTweakedTooltip(plateIron.itemID, plateTin.getItemDamage(), TweakingAction.ADDED, "Shapeless recipe to swap", "plate types");
	}
}
