package tppitweaks.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
    public static Item tppiMaterial;

    public static Item aeMaterial;
    public static Item aeMultipart;

    public static Item teSatchel;
    public static Item teFlorb;
    public static Item teMaterial;

    public static Item eioCapacitor;

    public static Item exuAngelRing;
    public static Item exuSoul;
    public static Item exuPickaxe;
    public static Item exuShovel;
    public static Item exuWand;
    public static Item exuUnstableIngot;

    public static Item rtSpiritBinder;

    public static Item tconHeartCanister;

    public static void initItems() {
        tppiMaterial = new TPPIMaterial();
        GameRegistry.registerItem(tppiMaterial, "tppiMaterial");

        aeMaterial = GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial");
        aeMultipart = GameRegistry.findItem("appliedenergistics2", "item.ItemMultiPart");

        teSatchel = GameRegistry.findItem("ThermalExpansion", "satchel");
        teFlorb = GameRegistry.findItem("ThermalExpansion", "florb");
        teMaterial = GameRegistry.findItem("ThermalExpansion", "material");

        eioCapacitor = GameRegistry.findItem("EnderIO", "itemBasicCapacitor");

        exuAngelRing = GameRegistry.findItem("ExtraUtilities", "angelRing");
        exuSoul = GameRegistry.findItem("ExtraUtilities", "mini-soul");
        exuPickaxe = GameRegistry.findItem("ExtraUtilities", "destructionpickaxe");
        exuShovel = GameRegistry.findItem("ExtraUtilities", "erosionShovel");
        exuWand = GameRegistry.findItem("ExtraUtilities", "builderswand");
        exuUnstableIngot = GameRegistry.findItem("ExtraUtilities", "unstableingot");

        rtSpiritBinder = GameRegistry.findItem("RandomThings", "spiritBinder");

        tconHeartCanister = GameRegistry.findItem("TinkersConstruct", "heartCanister");
    }

    public static void registerRecipes()
    {
        if (Loader.isModLoaded("appliedenergistics2") && Loader.isModLoaded("StevesFactoryManager") && ConfigurationHandler.tweakSFM)
        {
            /* @formatter:off */
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tppiMaterial, 1, 0),
			        "CdC",
					"dsd",
					"CdC",

					'C', new ItemStack(aeMaterial, 1, 23),
					's', "itemSilicon",
					'd', new ItemStack(aeMaterial, 1, 8)
			));
			/* @formatter:on */
        }
        
        /*  --Commented out in case item is needed for future tweak
        if (Loader.isModLoaded("ThermalExpansion")
        {
        	ThermalExpansionHelper.addTransposerFill(12000, new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 76), new ItemStack(ModItems.tppiMaterial, 1, 4), new FluidStack(FluidRegistry.getFluid("pyrotheum"), 1000), false);
        }
        */
    }
}
