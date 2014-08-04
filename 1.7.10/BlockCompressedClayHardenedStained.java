package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockCompressedClayHardenedStained extends BlockColored
{
    private String name;
    private int type;
    
    protected BlockCompressedClayHardenedStained(int type, String name, Material material)
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
}