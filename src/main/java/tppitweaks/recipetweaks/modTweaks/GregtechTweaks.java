package tppitweaks.recipetweaks.modTweaks;

import java.util.HashSet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class GregtechTweaks
{
	public static void doStuff()
	{	
		
		if (ConfigurationHandler.addOsmiumToOreWasher && Loader.isModLoaded("IC2") && !OreDictionary.getOres("dustImpureOsmium").isEmpty() && !OreDictionary.getOres("dustOsmium").isEmpty() && !OreDictionary.getOres("dustTinyCopper").isEmpty())
		{
			ic2.core.block.machine.tileentity.TileEntityOreWashing.addRecipe("dustImpureOsmium", 1, 1000, new ItemStack[] { OreDictionary.getOres("dustOsmium").get(0), OreDictionary.getOres("dustTinyCopper").get(0), ic2.core.Ic2Items.stoneDust });
		}
		/* Removed until we can find a way to remove recipes from Gregtech machines. ~~~Generalcamo
		if (ConfigurationHandler.balanceMekanismOsmiumUnification && Loader.isModLoaded("gregtech_addon") && Loader.isModLoaded("IC2") && !OreDictionary.getOres("crushedOsmium").isEmpty() && !OreDictionary.getOres("crushedPurifiedOsmium").isEmpty() && !OreDictionary.getOres("dustTinyCopper").isEmpty())
		{
			ic2.core.block.machine.tileentity.TileEntityOreWashing.addRecipe("crushedOsmium", 1, 1000, new ItemStack[] { OreDictionary.getOres("crushedPurifiedOsmium").get(0), ic2.core.Ic2Items.stoneDust, OreDictionary.getOres("dustTinyCopper").get(0) });
		}
		
		if (ConfigurationHandler.balanceMekanismOsmiumUnification && Loader.isModLoaded("gregtech_addon") && Loader.isModLoaded("IC2") && !OreDictionary.getOres("crushedOsmium").isEmpty() && !OreDictionary.getOres("dustOsmium").isEmpty() && !OreDictionary.getOres("dustTinyCopper").isEmpty())
		{
			ic2.core.block.machine.tileentity.TileEntityCentrifuge.addRecipe("crushedOsmium", 1, 1000, new ItemStack[] { OreDictionary.getOres("dustOsmium").get(0), ic2.core.Ic2Items.stoneDust, OreDictionary.getOres("dustTinyCopper").get(0) });
		}
		
		if (ConfigurationHandler.balanceMekanismOsmiumUnification && Loader.isModLoaded("gregtech_addon") && Loader.isModLoaded("IC2") && !OreDictionary.getOres("crushedPurifiedOsmium").isEmpty() && !OreDictionary.getOres("crushedCentrifugedOsmium").isEmpty() && !OreDictionary.getOres("dustTinyCopper").isEmpty())
		{
			ic2.core.block.machine.tileentity.TileEntityCentrifuge.addRecipe("crushedPurifiedOsmium", 1, 1000, new ItemStack[] { OreDictionary.getOres("crushedCentrifugedOsmium").get(0), OreDictionary.getOres("dustTinyCopper").get(0) });
		}
        */
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
					ItemStack dust = OreDictionary.getOres("crushedPurifiedAluminium").get(0).copy();
					dust.stackSize = 2;
					gregtechmod.api.GregTech_API.sRecipeAdder.addGrinderRecipe(s, ic2.core.Ic2Items.waterCell, dust, OreDictionary.getOres("dustSmallBauxite").get(0), OreDictionary.getOres("dustSmallBauxite").get(0),
							ic2.core.Ic2Items.cell);
				}}}
		/*
		if (Loader.isModLoaded("gregtech_addon") && ConfigurationHandler.balanceMekanismOsmiumUnification)
        {
            HashSet<Integer> okIds = new HashSet<Integer>();
            for (ItemStack s : OreDictionary.getOres("oreOsmium"))
            {
                if (okIds.contains(s.itemID))
                {
                    ItemStack dust = OreDictionary.getOres("crushedPurifiedOsmium").get(0).copy();
                    dust.stackSize = 2;
                    gregtechmod.api.GregTech_API.sRecipeAdder.addGrinderRecipe(s, ic2.core.Ic2Items.waterCell, dust, OreDictionary.getOres("dustSmallCopper").get(0), OreDictionary.getOres("dustSmallNickel").get(0),
                            ic2.core.Ic2Items.cell);
	    }}}
		*/
		}
	
	   public static void addRecipes() {
	       if(ConfigurationHandler.unnerfPaperRecipe) {
	       GameRegistry.addShapelessRecipe(new ItemStack(Item.paper, 3), new Object[] {Item.reed, Item.reed, Item.reed});
	       TETweaks.addRecipes();
	       GameRegistry.addRecipe(new ItemStack(Item.paper, 3), new Object[] {"#", "#", "#", '#', Item.reed});
	       }
	       if(ConfigurationHandler.readdResinSmelting) {
	       FurnaceRecipes.smelting().addSmelting(ic2.core.Ic2Items.resin.itemID, ic2.core.Ic2Items.resin.getItemDamage(), ic2.core.Ic2Items.rubber, 0F);
	       }
	       }

	       }
