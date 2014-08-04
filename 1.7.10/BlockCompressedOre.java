package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockCompressedOre extends Block
{
    private String name;
    private int type;
    
    public BlockCompressedOre(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreCoal" : (type == 2 ? "mc_oreCoal" : "hc_oreCoal")) ? (Item)Item.itemRegistry.getObject("coal") : (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreDiamond" : (type == 2 ? "mc_oreDiamond" : "hc_oreDiamond")) ? (Item)Item.itemRegistry.getObject("diamond") : (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreLapis" : (type == 2 ? "mc_oreLapis" : "hc_oreLapis")) ? (Item)Item.itemRegistry.getObject("dye") : (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreEmerald" : (type == 2 ? "mc_oreEmerald" : "hc_oreEmerald")) ? (Item)Item.itemRegistry.getObject("emerald") : (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_netherquartz" : (type == 2 ? "mc_netherquartz" : "hc_netherquartz")) ? (Item)Item.itemRegistry.getObject("quartz") : Item.getItemFromBlock((Block)Block.blockRegistry.getObject(name))))));
    }
    
    public int quantityDropped(Random par1)
    {
    	int j = 0;
    	
        for (int i = 0; i < Math.pow(8, type); ++i)
        {
        	j = par1.nextInt(5);
        }
        
        return this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreLapis" : (type == 2 ? "mc_oreLapis" : "hc_oreLapis")) ? (int) (Math.pow(8, type) * 4) + j : (int) (Math.pow(8, type));
    }
    
    public int quantityDroppedWithBonus(int par1, Random par2)
    {
        if (par1 > 0 && this != GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreIron" : (type == 2 ? "mc_oreIron" : "hc_oreIron")) && this != GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreGold" : (type == 2 ? "mc_oreGold" : "hc_oreGold")))
        {
        	int j = 0;
        	
            for (int i = 0; i < Math.pow(8, type); ++i)
            {
            	int k = par2.nextInt((par1 * 2) - 1);
            	if (k > 0) j = j + k;
            }
            
            return this.quantityDropped(par2) + j;
        }
        else
        {
            return this.quantityDropped(par2);
        }
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
            int j = 0;
            
            if (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreCoal" : (type == 2 ? "mc_oreCoal" : "hc_oreCoal")))
            {
                j = MathHelper.getRandomIntegerInRange(rand, 0, (int) (Math.pow(8, type) * 2));
            }
            else if (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreDiamond" : (type == 2 ? "mc_oreDiamond" : "hc_oreDiamond")))
            {
                j = MathHelper.getRandomIntegerInRange(rand, (int) (Math.pow(8, type) * 3), (int) (Math.pow(8, type) * 7));
            }
            else if (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreEmerald" : (type == 2 ? "mc_oreEmerald" : "hc_oreEmerald")))
            {
                j = MathHelper.getRandomIntegerInRange(rand, (int) (Math.pow(8, type) * 3), (int) (Math.pow(8, type) * 7));
            }
            else if (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreLapis" : (type == 2 ? "mc_oreLapis" : "hc_oreLapis")))
            {
                j = MathHelper.getRandomIntegerInRange(rand, (int) (Math.pow(8, type) * 2), (int) (Math.pow(8, type) * 5));
            }
            else if (this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreQuartz" : (type == 2 ? "mc_oreQuartz" : "hc_oreQuartz")))
            {
                j = MathHelper.getRandomIntegerInRange(rand, (int) (Math.pow(8, type) * 2), (int) (Math.pow(8, type) * 5));
            }
            
            return j;
        }
        return 0;
    }
    
    public int damageDropped(int par1)
    {
        return this == GameRegistry.findBlock(Core.modid, type == 1 ? "lc_oreLapis" : (type == 2 ? "mc_oreLapis" : "hc_oreLapis")) ? 4 : 0;
    }
}