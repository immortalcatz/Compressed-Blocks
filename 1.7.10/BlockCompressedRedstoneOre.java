package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedRedstoneOre extends Block
{
    private boolean lit;
    private String name;
    private int type;
    
    public BlockCompressedRedstoneOre(int type, String name, Material material, boolean par4)
    {
        super(material);
        
        this.name = name;
        this.type = type;

        if (par4)
        {
            this.setTickRandomly(true);
        }

        this.lit = par4;
    }
    
    public int tickRate(World par1)
    {
        return (int) Math.pow(8, type) * 30;
    }
    
    public void onBlockClicked(World par1, int par2, int par3, int par4, EntityPlayer par5)
    {
        this.func_150185_e(par1, par2, par3, par4);
        super.onBlockClicked(par1, par2, par3, par4, par5);
    }
    
    public void onEntityWalking(World par1, int par2, int par3, int par4, Entity par5)
    {
        this.func_150185_e(par1, par2, par3, par4);
        super.onEntityWalking(par1, par2, par3, par4, par5);
    }
    
    public boolean onBlockActivated(World par1, int par2, int par3, int par4, EntityPlayer par5, int par6, float par7, float par8, float par9)
    {
        this.func_150185_e(par1, par2, par3, par4);
        return super.onBlockActivated(par1, par2, par3, par4, par5, par6, par7, par8, par9);
    }
    
    private void func_150185_e(World par1, int par2, int par3, int par4)
    {
        this.func_150186_m(par1, par2, par3, par4);

        if (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreRedstone" : (type == 2 ? "mc_oreRedstone" : "hc_oreRedstone")))
        {
            par1.setBlock(par2, par3, par4, GameRegistry.findBlock(Core.modid, type == 1 ? "lc_lit_oreRedstone" : (type == 2 ? "mc_lit_oreRedstone" : "hc_lit_oreRedstone")));
        }
    }
    
    public void updateTick(World par1, int par2, int par3, int par4, Random par5)
    {
        if (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_lit_oreRedstone" : (type == 2 ? "mc_lit_oreRedstone" : "hc_lit_oreRedstone")))
        {
            par1.setBlock(par2, par3, par4, GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreRedstone" : (type == 2 ? "mc_oreRedstone" : "hc_oreRedstone")));
        }
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return (Item)Item.itemRegistry.getObject("redstone");
    }
    
    public int quantityDroppedWithBonus(int par1, Random par2)
    {
    	int j = 0;
    	
        for (int i = 0; i < Math.pow(8, type); ++i)
        {
        	j = j + par2.nextInt(par1 + 1);
        }
        
        return this.quantityDropped(par2) + j;
    }
    
    public int quantityDropped(Random par1)
    {
    	int j = 0;
        for (int i = 0; i < Math.pow(8, type); ++i)
        	j = j + par1.nextInt(2);
        return (int) (Math.pow(8, type) * 4) + j;
    }
    
    public void dropBlockAsItemWithChance(World par1, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1, par2, par3, par4, par5, par6, par7);
    }

    private Random rand = new Random();
    @Override
    public int getExpDrop(IBlockAccess par1, int par2, int par3)
    {
        if (this.getItemDropped(par2, rand, par3) != Item.getItemFromBlock(this))
        {
            return (int) Math.pow(8, type) + rand.nextInt((int) (Math.pow(8, type) * 5));
        }
        
        return 0;
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1, int par2, int par3, int par4, Random par5)
    {
        if (this.lit)
        {
            this.func_150186_m(par1, par2, par3, par4);
        }
    }
    
    private void func_150186_m(World par1, int par2, int par3, int par4)
    {
        Random random = par1.rand;
        double d0 = 0.0625D;
        
        for (int l = 0; l < 6; ++l)
        {
            double d1 = (double)((float)par2 + random.nextFloat());
            double d2 = (double)((float)par3 + random.nextFloat());
            double d3 = (double)((float)par4 + random.nextFloat());
            
            if (l == 0 && !par1.getBlock(par2, par3 + 1, par4).isOpaqueCube())
            {
                d2 = (double)(par3 + 1) + d0;
            }
            
            if (l == 1 && !par1.getBlock(par2, par3 - 1, par4).isOpaqueCube())
            {
                d2 = (double)(par3 + 0) - d0;
            }
            
            if (l == 2 && !par1.getBlock(par2, par3, par4 + 1).isOpaqueCube())
            {
                d3 = (double)(par4 + 1) + d0;
            }
            
            if (l == 3 && !par1.getBlock(par2, par3, par4 - 1).isOpaqueCube())
            {
                d3 = (double)(par4 + 0) - d0;
            }
            
            if (l == 4 && !par1.getBlock(par2 + 1, par3, par4).isOpaqueCube())
            {
                d1 = (double)(par2 + 1) + d0;
            }
            
            if (l == 5 && !par1.getBlock(par2 - 1, par3, par4).isOpaqueCube())
            {
                d1 = (double)(par2 + 0) - d0;
            }
            
            if (d1 < (double)par2 || d1 > (double)(par2 + 1) || d2 < 0.0D || d2 > (double)(par3 + 1) || d3 < (double)par4 || d3 > (double)(par4 + 1))
            {
                par1.spawnParticle("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreRedstone" : (type == 2 ? "mc_oreRedstone" : "hc_oreRedstone")));
    }
}