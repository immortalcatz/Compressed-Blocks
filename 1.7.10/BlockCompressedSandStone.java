package ztk.compressedblocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedSandStone extends Block
{
    public static final String[] type_name = new String[] {"default", "chiseled", "smooth"};
    private static final String[] type_icon = new String[] {"normal", "carved", "smooth"};
    
    @SideOnly(Side.CLIENT)
    private IIcon[] Icon;
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon botIcon;
    
    private String name;
    private int type;
    
    public BlockCompressedSandStone(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        if (par1 != 1 && (par1 != 0 || par2 != 1 && par2 != 2))
        {
            if (par1 == 0)
            {
                return this.botIcon;
            }
            else
            {
                if (par2 < 0 || par2 >= this.Icon.length)
                {
                    par2 = 0;
                }
                
                return this.Icon[par2];
            }
        }
        else
        {
            return this.topIcon;
        }
    }
    
    public int damageDropped(int par1)
    {
        return par1;
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
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
    {
        par3.add(new ItemStack(par1, 1, 0));
        par3.add(new ItemStack(par1, 1, 1));
        par3.add(new ItemStack(par1, 1, 2));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.Icon = new IIcon[type_icon.length];
        
        for (int i = 0; i < this.Icon.length; ++i)
        {
            this.Icon[i] = p_149651_1_.registerIcon(this.getTextureName() + "_" + type_icon[i]);
        }
        
        this.topIcon = p_149651_1_.registerIcon(this.getTextureName() + "_top");
        this.botIcon = p_149651_1_.registerIcon(this.getTextureName() + "_bottom");
    }
}