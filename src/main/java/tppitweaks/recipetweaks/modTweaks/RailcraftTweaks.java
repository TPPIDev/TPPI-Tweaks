package tppitweaks.recipetweaks.modTweaks;

import mods.railcraft.common.items.ItemPlate;
import mods.railcraft.common.items.ItemPlate.EnumPlate;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeAddition.EventTime;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;

public class RailcraftTweaks
{
	@RecipeAddition(requiredModids={"Railcraft"}, time=EventTime.WORLD_LOAD)
	public static void registerOres()
	{
		// Please don't kill me CJ
		
		ItemStack plateIron = ItemPlate.getPlate(EnumPlate.IRON);
		ItemStack plateSteel = ItemPlate.getPlate(EnumPlate.STEEL);
		ItemStack plateTin = ItemPlate.getPlate(EnumPlate.TIN);
		
		OreDictionary.registerOre("plateIron", plateIron);
		OreDictionary.registerOre("plateSteel", plateSteel);
		OreDictionary.registerOre("plateTin", plateTin);
		
		TweakingRegistry.addTweakedTooltip(plateIron.itemID, plateIron.getItemDamage(), TweakingAction.NOTE, "Registered in ore dictionary", "as plateIron");
		TweakingRegistry.addTweakedTooltip(plateIron.itemID, plateSteel.getItemDamage(), TweakingAction.NOTE, "Registered in ore dictionary", "as plateSteel");
		TweakingRegistry.addTweakedTooltip(plateIron.itemID, plateTin.getItemDamage(), TweakingAction.NOTE, "Registered in ore dictionary", "as plateTin");
	}
}
