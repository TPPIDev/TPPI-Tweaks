package tppitweaks.aspecttweaks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class AspectTweaks {

	public static void init() {
		//Fix chest aspects
		ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.chest), new int[] {0}, new AspectList().add(Aspect.TREE, 4).add(Aspect.VOID, 4));	
	}
	
	public static void onWorldLoad() {
		//Lower TC Iron Nugget Aspects
		ThaumcraftApi.registerObjectTag(new ItemStack(GameRegistry.findItem("Thaumcraft", "ItemNugget")), new int[] {0}, new AspectList().add(Aspect.METAL, 1));
	}
}
