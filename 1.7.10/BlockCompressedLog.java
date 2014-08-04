package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockCompressedLog extends BlockRotatedPillar
{
    @SideOnly(Side.CLIENT)
    protected IIcon[] sideIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon[] topIcon;
    
    private String name;
    private int type;
    
    public BlockCompressedLog(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    public static int func_150165_c(int par1)
    {
        return par1 & 3;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return Item.getItemFromBlock((Block)Block.blockRegistry.getObject(name));
    }
    
    public int quantityDropped(Random random)
    {
        return (int) Math.pow(8, type);
    }
    
    public void breakBlock(World par1, int par2, int par3, int par4, Block p_149749_5_, int par6)
    {
        byte b0 = 4;
        int i1 = b0 + 1;
        
        if (par1.checkChunksExist(par2 - i1, par3 - i1, par4 - i1, par2 + i1, par3 + i1, par4 + i1))
        {
            for (int j1 = -b0; j1 <= b0; ++j1)
            {
                for (int k1 = -b0; k1 <= b0; ++k1)
                {
                    for (int l1 = -b0; l1 <= b0; ++l1)
                    {
                        Block block = par1.getBlock(par2 + j1, par3 + k1, par4 + l1);
                        
                        if (block.isLeaves(par1, par2 + j1, par3 + k1, par4 + l1))
                        {
                            block.beginLeavesDecay(par1, par2 + j1, par3 + k1, par4 + l1);
                        }
                    }
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int par1)
    {
        return this.sideIcon[par1 % this.sideIcon.length];
    }
    
    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int par1)
    {
        return this.topIcon[par1 % this.topIcon.length];
    }
    
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
}