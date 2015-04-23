package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
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
        Block anchor = GameRegistry.findBlock("DimensionalAnchors", "chunkloader");

		if (ConfigurationHandler.tweakDA)
			TweakingRegistry.markItemForRecipeRemoval(anchor, -1);
	}
	
	@RecipeAddition(requiredModids="DimensionalAnchors")
	public static void addRecipes()
	{
        Block anchor = GameRegistry.findBlock("DimensionalAnchors", "chunkloader");

		if(ConfigurationHandler.tweakDA)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(anchor, 1, 0),
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
        Block anchor = GameRegistry.findBlock("DimensionalAnchors", "chunkloader");

		if (event.itemStack.getItem() == Item.getItemFromBlock(anchor))
		{
			event.toolTip.add("\u00A7oA chunk loader");
		}
	}
}
