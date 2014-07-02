package tppitweaks.recipetweaks.modTweaks;

import java.util.Arrays;
import java.util.HashSet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import tterrag.rtc.RecipeAddition;
import tterrag.rtc.RecipeAddition.EventTime;
import tterrag.rtc.RecipeRemoval;
import tterrag.rtc.TweakingRegistry;
import tterrag.rtc.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class GregtechTweaks
{
	@RecipeRemoval(requiredModids={"IC2", "gregtech_addon"})
	public static void doStuff()
	{	
		if (ConfigurationHandler.addOsmiumToOreWasher && Loader.isModLoaded("IC2") && !OreDictionary.getOres("dustImpureOsmium").isEmpty() && !OreDictionary.getOres("dustOsmium").isEmpty())
		{
			ic2.core.block.machine.tileentity.TileEntityOreWashing.addRecipe("dustImpureOsmium", 1, 1000, new ItemStack[] { OreDictionary.getOres("dustOsmium").get(0), ic2.core.Ic2Items.stoneDust });
		}

		if (Loader.isModLoaded("gregtech_addon") && ConfigurationHandler.doPlatinumInCentrifuge)
		{
			gregtechmod.api.GregTech_API.sRecipeAdder.addCentrifugeRecipe(OreDictionary.getOres("dustPlatinum").get(0), 0, OreDictionary.getOres("nuggetIridium").get(0),
					OreDictionary.getOres("dustSmallNickel").get(0), null, null, 3000);
		}
		if (Loader.isModLoaded("gregtech_addon") && ConfigurationHandler.addLapisDustMortarRecipes)
		{
			for (ItemStack s : OreDictionary.getOres("dustLapis"))
			{
				GameRegistry.addRecipe(new ShapelessOreRecipe(s, new Object[] { gregtechmod.api.enums.GT_Items.Tool_Mortar_Iron.getWildcard(1L, new Object[0]), new ItemStack(Item.dyePowder, 1, 4) }));
				GameRegistry.addRecipe(new ShapelessOreRecipe(s, new Object[] { gregtechmod.api.enums.GT_Items.Tool_Mortar_Wood.getWildcard(1L, new Object[0]), new ItemStack(Item.dyePowder, 1, 4) }));
			}
			for (ItemStack s : OreDictionary.getOres("dustZinc"))
				for (ItemStack s1 : OreDictionary.getOres("ingotZinc"))
					GameRegistry.addRecipe(new ShapelessOreRecipe(s, new Object[] { gregtechmod.api.enums.GT_Items.Tool_Mortar_Iron.getWildcard(1L, new Object[0]), s1}));
		}

		if (Loader.isModLoaded("gregtech_addon") && Loader.isModLoaded("TConstruct") && ConfigurationHandler.tinkersAluminumPlates)
		{
			int id = OreDictionary.getOres("ingotCobalt").get(0).itemID;
			for (ItemStack s : OreDictionary.getOres("ingotAluminum"))
			{
				if (s.itemID == id)
				{
					gregtechmod.api.GregTech_API.sRecipeAdder.addBenderRecipe(s, OreDictionary.getOres("plateAluminium").get(0), 52, 24);
				}
			}
		}
		if (Loader.isModLoaded("gregtech_addon") && Loader.isModLoaded("TConstruct") && ConfigurationHandler.tinkersAluminumOreInGTMachines)
		{
			HashSet<Integer> okIds = new HashSet<Integer>();
			for (ItemStack s : OreDictionary.getOres("oreCobalt"))
			{
				okIds.add(s.itemID);
			}

			for (ItemStack s : OreDictionary.getOres("oreAluminum"))
			{
				if (okIds.contains(s.itemID))
				{
					ItemStack dust = OreDictionary.getOres("dustAluminium").get(0).copy();
					dust.stackSize = 2;
					gregtechmod.api.GregTech_API.sRecipeAdder.addGrinderRecipe(s, ic2.core.Ic2Items.waterCell, dust, OreDictionary.getOres("dustSmallBauxite").get(0), OreDictionary.getOres("dustSmallBauxite").get(0),
							ic2.core.Ic2Items.cell);
				}
			}
		}
		
		// Extruder Recipies are added later, and we can only remove them after they have been added.
		gregtechmod.api.GregTech_API.sAfterGTPostload.add(new Runnable()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				
				
				String[] blacklist = new String[ConfigurationHandler.removeExtruderInput.length];
				System.arraycopy(ConfigurationHandler.removeExtruderInput, 0, blacklist, 0, ConfigurationHandler.removeExtruderInput.length);
				java.util.Arrays.sort(blacklist);
				java.util.Map<Long, gregtechmod.api.util.GT_Recipe> extruderRecipies;
				java.util.ArrayList<Long> recipesToRemove = new java.util.ArrayList<Long>();
				try
				{
					extruderRecipies = (java.util.Map<Long, gregtechmod.api.util.GT_Recipe>)gregtechmod.api.util.GT_Recipe.class.getField("pExtruderRecipes").get(null);
					for (java.util.Map.Entry<Long, gregtechmod.api.util.GT_Recipe> entry : extruderRecipies.entrySet())
					{
						gregtechmod.api.util.GT_Recipe rec = entry.getValue();
						int oreDictId = OreDictionary.getOreID(rec.getRepresentativeInput1());
						if (oreDictId == -1) continue;
						String oreDictName = OreDictionary.getOreName(oreDictId);
						if (oreDictName != null && Arrays.binarySearch(blacklist, oreDictName) >= 0)
						{
							recipesToRemove.add(entry.getKey());
						}
				
					}
					for (Long id : recipesToRemove)
					{
						extruderRecipies.remove(id);
					}
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	@RecipeAddition(time=EventTime.PLAYER_JOIN, requiredModids={"IC2", "gregtech_addon"})
	public static void doPostLoadRecipeAdditions() {
		if(ConfigurationHandler.unnerfPaperRecipe) {
			TweakingRegistry.addTweakedTooltip(Item.paper.itemID, -1,  TweakingAction.ADDED, "Check recipe to ensure 3x output");
			GameRegistry.addShapelessRecipe(new ItemStack(Item.paper, 3), new Object[] {Item.reed, Item.reed, Item.reed});
			TETweaks.addRecipes();
			GameRegistry.addRecipe(new ItemStack(Item.paper, 3), new Object[] {"#", "#", "#", '#', Item.reed});
		}
		if(ConfigurationHandler.readdResinSmelting) {
			FurnaceRecipes.smelting().addSmelting(ic2.core.Ic2Items.resin.itemID, ic2.core.Ic2Items.resin.getItemDamage(), ic2.core.Ic2Items.rubber, 0F);
		}
	}

}
