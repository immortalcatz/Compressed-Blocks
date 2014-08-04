package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedRedstoneLight extends Block
{
    private final boolean lit;

    private String name;
    private int type;
    
    public BlockCompressedRedstoneLight(int type, String name, Material material, boolean par4)
    {
        super(material);
        
        this.name = name;
        this.type = type;
        this.lit = par4;

        if (par4)
        {
            this.setLightLevel(1.0F);
        }
    }
    
    public void onBlockAdded(World par1, int par2, int par3, int par4)
    {
        if (!par1.isRemote)
        {
            if (this.lit && !par1.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1.scheduleBlockUpdate(par2, par3, par4, this, 4);
            }
            else if (!this.lit && par1.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1.setBlock(par2, par3, par4, type == 1 ? GameRegistry.findBlock(Core.modid, "lc_lit_redstone_lamp") : (type == 2 ? GameRegistry.findBlock(Core.modid, "mc_lit_redstone_lamp") : GameRegistry.findBlock(Core.modid, "hc_lit_redstone_lamp")), 0, 2);
            }
        }
    }
    
    public void onNeighborBlockChange(World par1, int par2, int par3, int par4, Block par5)
    {
        if (!par1.isRemote)
        {
            if (this.lit && !par1.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1.scheduleBlockUpdate(par2, par3, par4, this, 4);
            }
            else if (!this.lit && par1.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1.setBlock(par2, par3, par4, type == 1 ? GameRegistry.findBlock(Core.modid, "lc_lit_redstone_lamp") : (type == 2 ? GameRegistry.findBlock(Core.modid, "mc_lit_redstone_lamp") : GameRegistry.findBlock(Core.modid, "hc_lit_redstone_lamp")), 0, 2);
            }
        }
    }
    
    public void updateTick(World par1, int par2, int par3, int par4, Random par5)
    {
        if (!par1.isRemote && this.lit && !par1.isBlockIndirectlyGettingPowered(par2, par3, par4))
        {
            par1.setBlock(par2, par3, par4, type == 1 ? GameRegistry.findBlock(Core.modid, "lc_redstone_lamp") : (type == 2 ? GameRegistry.findBlock(Core.modid, "mc_redstone_lamp") : GameRegistry.findBlock(Core.modid, "hc_redstone_lamp")), 0, 2);
        }
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return Item.getItemFromBlock((Block)Block.blockRegistry.getObject(name));
    }
    
    public int quantityDropped(Random par1)
    {
        return (int) Math.pow(8, type);
    }
    
    @SideOnly(Side.CLIENT)
    public Item getItem(World par1, int par2, int par3, int par4)
    {
        return Item.getItemFromBlock((Block)Block.blockRegistry.getObject(name));
    }
    
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(type == 1 ? GameRegistry.findBlock(Core.modid, "lc_redstone_lamp") : (type == 2 ? GameRegistry.findBlock(Core.modid, "mc_redstone_lamp") : GameRegistry.findBlock(Core.modid, "hc_redstone_lamp")));
    }
}