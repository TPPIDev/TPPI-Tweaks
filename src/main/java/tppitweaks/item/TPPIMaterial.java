package tppitweaks.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tppitweaks.TPPITweaks;
import tppitweaks.tweak.recipe.TweakMekanism;
import cpw.mods.fml.common.Loader;

public class TPPIMaterial extends Item {
    public IIcon uncookedIcon;

    public TPPIMaterial() {
        super();
        setCreativeTab(TPPITweaks.creativeTab);
        setHasSubtypes(true);
    }

    private IIcon[] icons;

    private String[] unlocNames = { "multicoreProcessor", "disassemblerCore", "miningCore", "dmCore", "enderium420" };

    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        icons = new IIcon[5];
        icons[0] = par1IconRegister.registerIcon("tppitweaks:tppiProcessor");
        icons[1] = par1IconRegister.registerIcon("tppitweaks:disassemblerCore");
        icons[2] = par1IconRegister.registerIcon("tppitweaks:miningCore");
        icons[3] = Loader.isModLoaded("Mekanism") ? TweakMekanism.getCircuitIcon() : par1IconRegister.registerIcon("not:applicable");
        icons[4] = par1IconRegister.registerIcon("tppitweaks:blazeEnderStill");
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return unlocNames[stack.getItemDamage()];
    }

    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
    	return stack.getItemDamage() == 2 || stack.getItemDamage() == 3;
    }

    @Override
    public IIcon getIconFromDamage(int dmg) {
        return dmg < icons.length ? icons[dmg] : null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 5; i++)
            list.add(new ItemStack(this, 1, i));
    }
}
