package ztk.compressedblocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedStainedGlass extends BlockBreakable
{
    private static final IIcon[] Icon = new IIcon[16];
    
    public BlockCompressedStainedGlass(int type, String name, Material material)
    {
        super(name, material, false);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return Icon[par2 % Icon.length];
    }
    
    public int damageDropped(int par1)
    {
        return par1;
    }
    
    public int quantityDropped(Random par1)
    {
        return 0;
    }
    
    @SideOnly(Side.CLIENT)
    public static int func_149997_b(int par1)
    {
        return ~par1 & 15;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
    {
        for (int i = 0; i < Icon.length; ++i)
        {
            par3.add(new ItemStack(par1, 1, i));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1)
    {
        for (int i = 0; i < Icon.length; ++i)
        {
            Icon[i] = par1.registerIcon(this.getTextureName() + "_" + ItemDye.field_150921_b[func_149997_b(i)]);
        }
    }
    
    protected boolean canSilkHarvest()
    {
        return true;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}