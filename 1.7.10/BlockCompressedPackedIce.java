package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCompressedPackedIce extends Block
{
    private String name;
    private int type;
    
    public BlockCompressedPackedIce(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    public int quantityDropped(Random par1)
    {
        return 0;
    }
}