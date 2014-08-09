package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class DATweaks
{
	@RecipeRemoval(requiredModids="DimensionalAnchors")
	public static void init()
	{
		if (ConfigurationHandler.tweakDA)
			TweakingRegistry.markItemForRecipeRemoval(((Block) mods.immibis.chunkloader.DimensionalAnchors.instance.block).blockID, -1);
	}
	
	@RecipeAddition(requiredModids="DimensionalAnchors")
	public static void addRecipes()
	{
		if(ConfigurationHandler.tweakDA)
			GameRegistry.addRecipe(new ItemStack(mods.immibis.chunkloader.DimensionalAnchors.instance.block, 1, 0), "ded", "oIo", "gog", 'd', Item.diamond, 'e', Item.enderPearl, 'o', Block.obsidian, 'I', Block.blockIron,'g', Item.ingotGold);
	}
	
	public static void addTooltip(ItemTooltipEvent event)
	{
		if (event.itemStack.itemID == ((Block) mods.immibis.chunkloader.DimensionalAnchors.instance.block).blockID)
		{
			event.toolTip.add("\u00A7oA chunk loader");
		}
	}
}
