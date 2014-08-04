package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCompressedNetherrack extends Block
{
    private String name;
    private int type;
    
    public BlockCompressedNetherrack(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return (Item)Item.itemRegistry.getObject(name);
    }
    
    public int quantityDropped(Random random)
    {
        return (int) Math.pow(8, type);
    }
    
    public MapColor getMapColor(int p_149728_1_)
    {
        return MapColor.netherrackColor;
    }
    
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
    {
        return true;
    }
}