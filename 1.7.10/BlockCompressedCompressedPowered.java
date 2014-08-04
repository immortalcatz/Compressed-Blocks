package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressedPowered;
import net.minecraft.block.material.MapColor;
import net.minecraft.item.Item;

public class BlockCompressedCompressedPowered extends BlockCompressedPowered
{
    private String name;
    private int type;
    
	protected BlockCompressedCompressedPowered(int type, String name, MapColor mapcolor)
    {
        super(mapcolor);
        
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