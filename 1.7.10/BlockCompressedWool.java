package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCompressedWool extends BlockColored
{
    private String name;
    private int type;

    protected BlockCompressedWool(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return Item.getItemFromBlock((Block)Block.blockRegistry.getObject(name));
    }
    
    public int quantityDropped(Random par1)
    {
        return (int) Math.pow(8, type);
    }
    
    public boolean isFlammable (IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
    return true;
    }
    
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
    	 return ((BlockFire)Block.blockRegistry.getObject("fire")).getFlammability(this);
    }
    
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return ((BlockFire)Block.blockRegistry.getObject("fire")).getEncouragement(this);
    }
}