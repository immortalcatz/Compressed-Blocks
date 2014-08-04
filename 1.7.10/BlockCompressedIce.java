package ztk.compressedblocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedIce extends BlockBreakable
{
    public BlockCompressedIce(int type, String name, Material material)
    {
        super(name, material, false);
        
        this.slipperiness = 1F + (int) Math.pow(type, 3) * 0.1F;
        this.setTickRandomly(true);
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1, int par2, int par3, int par4, int par5)
    {
        return super.shouldSideBeRendered(par1, par2, par3, par4, 1 - par5);
    }
    
    public void harvestBlock(World par1, EntityPlayer par2, int par3, int par4, int par5, int par6)
    {
        par2.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
        par2.addExhaustion(0.025F);

        if (this.canSilkHarvest(par1, par2, par3, par4, par5, par6) && EnchantmentHelper.getSilkTouchModifier(par2))
        {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>();
            ItemStack itemstack = this.createStackedBlock(par6);

            if (itemstack != null)
            {
            	items.add(itemstack);
            }

            ForgeEventFactory.fireBlockHarvesting(items, par1, this, par3, par4, par5, par6, 0, 1.0f, true, par2);
            
            for (ItemStack is : items)
            {
                this.dropBlockAsItem(par1, par3, par4, par5, is);
            }
        }
        else
        {
            if (par1.provider.isHellWorld)
            {
                par1.setBlockToAir(par3, par4, par5);
                return;
            }

            int i1 = EnchantmentHelper.getFortuneModifier(par2);
            harvesters.set(par2);
            this.dropBlockAsItem(par1, par3, par4, par5, par6, i1);
            harvesters.set(null);
            Material material = par1.getBlock(par3, par4 - 1, par5).getMaterial();

            if (material.blocksMovement() || material.isLiquid())
            {
                par1.setBlock(par3, par4, par5, Blocks.flowing_water);
            }
        }
    }
    
    public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }
    
    public int getMobilityFlag()
    {
        return 0;
    }
}