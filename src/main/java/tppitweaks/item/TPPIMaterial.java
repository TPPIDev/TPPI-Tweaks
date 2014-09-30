package tppitweaks.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tppitweaks.TPPITweaks;
import tppitweaks.recipetweaks.modTweaks.MekanismTweaks;
import cpw.mods.fml.common.Loader;

public class TPPIMaterial extends Item
{
    public IIcon uncookedIcon;

    public TPPIMaterial()
    {
        super();
        setCreativeTab(TPPITweaks.creativeTab);
        setHasSubtypes(true);
    }

    private IIcon[] icons;

    private String[] unlocNames = { "multicoreProcessor", "multicoreProcessorUncooked", "disassemblerCore", "miningCore", "dmCore" };

    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        icons = new IIcon[5];
        icons[0] = par1IconRegister.registerIcon("tppitweaks:tppiProcessor");
        icons[1] = par1IconRegister.registerIcon("tppitweaks:tppiProcessorUncooked");
        icons[2] = par1IconRegister.registerIcon("tppitweaks:disassemblerCore");
        icons[3] = par1IconRegister.registerIcon("tppitweaks:miningCore");
        icons[4] = Loader.isModLoaded("Mekanism") ? MekanismTweaks.getCircuitIcon() : par1IconRegister.registerIcon("not:applicable");
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return unlocNames[par1ItemStack.getItemDamage()];
    }

    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return stack.getItemDamage() >= 3;
    }

    @Override
    public IIcon getIconFromDamage(int dmg)
    {
        return dmg < icons.length ? icons[dmg] : null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < 5; i++)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }
}
