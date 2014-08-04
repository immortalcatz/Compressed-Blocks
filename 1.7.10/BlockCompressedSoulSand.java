package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockCompressedSoulSand extends Block
{
    private String name;
    private int type;
    
    public BlockCompressedSoulSand(int type, String name, Material material)
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
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1, int par2, int par3, int par4)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)((float)(par3 + 1) - f), (double)(par4 + 1));
    }
    
    public void onEntityCollidedWithBlock(World par1, int par2, int par3, int par4, Entity par5)
    {
        par5.motionX *= 0.4D;
        par5.motionZ *= 0.4D;
    }
}