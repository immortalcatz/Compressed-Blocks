package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockCompressedGravel extends BlockFalling
{
    private String name;
    private int type;
    
    public BlockCompressedGravel(int type, String name)
    {
        this.name = name;
        this.type = type;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        if (par3 > 3)
        {
            par3 = 3;
        }
        
        return par2.nextInt(10 - par3 * 3) == 0 ? Items.flint : Item.getItemFromBlock((Block)Block.blockRegistry.getObject(name));
    }
    
    public int quantityDropped(Random random)
    {
        return (int) Math.pow(8, type);
    }
}