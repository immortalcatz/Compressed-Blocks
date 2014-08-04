package ztk.compressedblocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedSand extends BlockFalling
{
    public static final String[] type_name = new String[] {"default", "red"};
    
    @SideOnly(Side.CLIENT)
    private static IIcon sandIcon;
    @SideOnly(Side.CLIENT)
    private static IIcon red_sandIcon;
    
    private String name;
    private int type;
    
    public BlockCompressedSand(int type, String name)
    {
        this.name = name;
        this.type = type;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par2 == 1 ? red_sandIcon : sandIcon;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1)
    {
        sandIcon = par1.registerIcon(name + "sand");
        red_sandIcon = par1.registerIcon(name + "red_sand");
    }
    
    public int damageDropped(int par1)
    {
        return par1;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return Item.getItemFromBlock((Block)Block.blockRegistry.getObject("sand"));
    }
    
    public int quantityDropped(Random par1)
    {
        return (int) Math.pow(8, type);
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
    {
        par3.add(new ItemStack(par1, 1, 0));
        par3.add(new ItemStack(par1, 1, 1));
    }
    
    public MapColor getMapColor(int par1)
    {
        return par1 == 1 ? MapColor.dirtColor : MapColor.sandColor;
    }
}