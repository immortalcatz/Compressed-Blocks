package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;

public class BlockCompressedGlowstone extends Block
{
    private String name;
    private int type;
    
    protected BlockCompressedGlowstone(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return (Item)Item.itemRegistry.getObject(name + "_dust");
    }
    
    public int quantityDroppedWithBonus(int par1, Random par2)
    {
    	int j = 0;
    	
        for (int i = 0; i < Math.pow(8, type); ++i)
        {
        	j = j + MathHelper.clamp_int(par2.nextInt(par1 + 1), 1, 4);
        }
        
        return this.quantityDropped(par2) + j;
    }
    
    public int quantityDropped(Random par1)
    {
    	int j = 0;
    	
        for (int i = 0; i < Math.pow(8, type); ++i)
        {
        	j =  j + par1.nextInt(3);
        }
        
        return (int) Math.pow(8, type) * 2 + j;
    }
    
    public MapColor getMapColor(int par1)
    {
        return MapColor.sandColor;
    }
}