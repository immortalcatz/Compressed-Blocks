package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedGlass extends BlockBreakable
{
    public BlockCompressedGlass(int type, String name, Material material, boolean par4)
    {
        super(name, material, par4);
    }
    
    public int quantityDropped(Random par1)
    {
        return 0;
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    protected boolean canSilkHarvest()
    {
        return true;
    }
}