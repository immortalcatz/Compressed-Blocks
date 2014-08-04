package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.item.Item;

public class BlockCompressedStoneBrick extends BlockStoneBrick
{
    private String name;
    private int type;
    
    protected BlockCompressedStoneBrick(int type, String name)
    {
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
}