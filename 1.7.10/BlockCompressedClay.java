package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockCompressedClay extends Block
{
    private String name;
    private int type;
    
    public BlockCompressedClay(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return (Item)Item.itemRegistry.getObject(name + "_ball");
    }
    
    public int quantityDropped(Random par1)
    {
        return (int) Math.pow(8, type) * 4;
    }
}