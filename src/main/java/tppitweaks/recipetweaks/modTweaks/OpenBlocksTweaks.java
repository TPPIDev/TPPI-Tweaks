package tppitweaks.recipetweaks.modTweaks;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import openblocks.OpenBlocks;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class OpenBlocksTweaks
{
	@RecipeRemoval(requiredModids = "OpenBlocks")
	public static void init()
	{
		if (ConfigurationHandler.eloraamBreakersAndDeployers)
		{
			if (openblocks.OpenBlocks.Blocks.blockBreaker != null)
				TweakingRegistry.markItemForRecipeRemoval(((Block) openblocks.OpenBlocks.Blocks.blockBreaker).blockID, -1, TweakingAction.CHANGED, "Recipe changed to bring back", "RP2-like recipes");

			if (openblocks.OpenBlocks.Blocks.blockPlacer != null)
				TweakingRegistry.markItemForRecipeRemoval(((Block) openblocks.OpenBlocks.Blocks.blockPlacer).blockID, -1, TweakingAction.CHANGED, "Recipe changed to bring back", "RP2-like recipes");
		}

		if (ConfigurationHandler.disablePigmenTrophyDrop)
		{
			MinecraftForge.EVENT_BUS.register(new OpenBlocksTweaks());
		}
	}

	@RecipeAddition(requiredModids = "OpenBlocks")
	public static void addRecipes()
	{
		if (ConfigurationHandler.eloraamBreakersAndDeployers)
		{
			if (openblocks.OpenBlocks.Blocks.blockBreaker != null)
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((openblocks.OpenBlocks.Blocks.blockBreaker), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A',
						Item.pickaxeIron, 'P', Block.pistonBase, 'R', Item.redstone }));

			if (openblocks.OpenBlocks.Blocks.blockPlacer != null)
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack((openblocks.OpenBlocks.Blocks.blockPlacer), 1), new Object[] { "CAC", "CPC", "CRC", 'C', "cobblestone", 'A', Block.chest, 'P',
						Block.pistonBase, 'R', Item.redstone }));
		}
	}

	@ForgeSubscribe
	public void onLivingDrop(LivingDropsEvent event)
	{
		if (event.entityLiving instanceof EntityPigZombie)
		{
			Iterator<EntityItem> iter = event.drops.iterator();
			while (iter.hasNext())
			{
				ItemStack stack = iter.next().getEntityItem();
				if (stack != null && stack.getItem() == Item.itemsList[((Block) OpenBlocks.Blocks.trophy).blockID])
				{
					iter.remove();
				}
			}
		}
	}
}
