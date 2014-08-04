package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedMelon extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    
    private String name;
    private int type;
    
    protected BlockCompressedMelon(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 != 1 && par1 != 0 ? this.blockIcon : this.topIcon;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return (Item)Item.itemRegistry.getObject(name);
    }

    public int quantityDropped(Random par1)
    {
    	int j = 0;
    	
        for (int i = 0; i < Math.pow(8, type); ++i)
        {
    		j = j + par1.nextInt(5);
        }
        
        return (int) Math.pow(8, type) * 3 + j;
    }

    public int quantityDroppedWithBonus(int par1, Random par2)
    {
        int j = 0;
        
        for (int i = 0; i < Math.pow(8, type); ++i)
        {
        	j = j + par2.nextInt(1 + par1);
        }
        
        if (j > Math.pow(8, type) * 9)
        {
            j = (int) Math.pow(8, type) * 9;
        }

        return this.quantityDropped(par2) + j;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1)
    {
        this.blockIcon = par1.registerIcon(this.getTextureName() + "_side");
        this.topIcon = par1.registerIcon(this.getTextureName() + "_top");
    }
}