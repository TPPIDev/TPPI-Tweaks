package tppitweaks.recipetweaks.modTweaks;

import mods.immibis.chunkloader.DimensionalAnchors;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
			TweakingRegistry.markItemForRecipeRemoval(DimensionalAnchors.instance.block, -1);
	}
	
	@RecipeAddition(requiredModids="DimensionalAnchors")
	public static void addRecipes()
	{
		if(ConfigurationHandler.tweakDA)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DimensionalAnchors.instance.block, 1, 0), 
			        "ded", 
			        "oIo", 
			        "gog", 
			        
			        'd', "gemDiamond", 
			        'e', Items.ender_pearl, 
			        'o', Blocks.obsidian, 
			        'I', "blockIron",
			        'g', "ingotGold"
			));
	}
	
	public static void addTooltip(ItemTooltipEvent event)
	{
		if (event.itemStack.getItem() == Item.getItemFromBlock(DimensionalAnchors.instance.block))
		{
			event.toolTip.add("\u00A7oA chunk loader");
		}
	}
}
