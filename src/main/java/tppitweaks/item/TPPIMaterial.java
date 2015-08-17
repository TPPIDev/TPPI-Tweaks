package tppitweaks.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tppitweaks.TPPITweaks;
import tppitweaks.tweak.recipe.TweakExtraUtilities;
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

    private String[] unlocNames = { "multicoreProcessor", "disassemblerCore", "miningCore", "dmCore", "enderium420", "soul" };

    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        icons = new IIcon[6];
        icons[0] = par1IconRegister.registerIcon("tppitweaks:tppiProcessor");
        icons[1] = par1IconRegister.registerIcon("tppitweaks:disassemblerCore");
        icons[2] = par1IconRegister.registerIcon("tppitweaks:miningCore");
        icons[3] = Loader.isModLoaded("Mekanism") ? TweakMekanism.getCircuitIcon() : par1IconRegister.registerIcon("not:applicable");
        icons[4] = par1IconRegister.registerIcon("tppitweaks:blazeEnderStill");
        icons[5] = TweakExtraUtilities.getSoulFragmentIcon();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return unlocNames[stack.getItemDamage()];
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        if (stack.getItemDamage() != 5)
            return stack;

        if (!world.isRemote) {
            player.inventory.decrStackSize(player.inventory.currentItem, 1);
            world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(TweakExtraUtilities.soul)));
            player.playSound("random.drink", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        }

        return stack;
    }

    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
    	return stack.getItemDamage() == 2 || stack.getItemDamage() == 3 || stack.getItemDamage() == 5;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        switch (stack.getItemDamage()) {
            case 4: {
                list.add("Unused");
                break;
            }
            case 5: {
                list.add(StatCollector.translateToLocal("tppi.tooltip.soul"));
            }
            default: break;
        }
    }

    @Override
    public IIcon getIconFromDamage(int dmg) {
        return dmg < icons.length ? icons[dmg] : null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < unlocNames.length; i++)
            list.add(new ItemStack(this, 1, i));
    }
}
