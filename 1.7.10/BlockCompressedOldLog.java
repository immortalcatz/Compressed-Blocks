package ztk.compressedblocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedOldLog extends BlockCompressedLog
{
    public static final String[] type_name = new String[] {"oak", "spruce", "birch", "jungle"};
    
    protected BlockCompressedOldLog(int type, String name, Material material)
    {
        super(type, name, material);
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
    {
        par3.add(new ItemStack(par1, 1, 0));
        par3.add(new ItemStack(par1, 1, 1));
        par3.add(new ItemStack(par1, 1, 2));
        par3.add(new ItemStack(par1, 1, 3));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.sideIcon = new IIcon[type_name.length];
        this.topIcon = new IIcon[type_name.length];
        
        for (int i = 0; i < this.sideIcon.length; ++i)
        {
            this.sideIcon[i] = p_149651_1_.registerIcon(this.getTextureName() + "_" + type_name[i]);
            this.topIcon[i] = p_149651_1_.registerIcon(this.getTextureName() + "_" + type_name[i] + "_top");
        }
    }
}