package ztk.compressedblocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedQuartz extends Block
{
    public static final String[] type_name = new String[] {"default", "chiseled", "lines"};
    private static final String[] type_icon = new String[] {"side", "chiseled", "lines", null, null};
    
    @SideOnly(Side.CLIENT)
    private IIcon[] Icon;
    @SideOnly(Side.CLIENT)
    private IIcon chiseled_topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon lines_topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon botIcon;
    
    private String name;
    private int type;
    
    public BlockCompressedQuartz(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 != 2 && par2 != 3 && par2 != 4)
        {
            if (par1 != 1 && (par1 != 0 || par2 != 1))
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
                return par2 == 1 ? this.chiseled_topIcon : this.topIcon;
            }
        }
        else
        {
            return par2 == 2 && (par1 == 1 || par1 == 0) ? this.lines_topIcon : (par2 == 3 && (par1 == 5 || par1 == 4) ? this.lines_topIcon : (par2 == 4 && (par1 == 2 || par1 == 3) ? this.lines_topIcon : this.Icon[par2]));
        }
    }

    public int onBlockPlaced(World par1, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        if (par9 == 2)
        {
            switch (par5)
            {
                case 0:
                case 1:
                    par9 = 2;
                    break;
                case 2:
                case 3:
                    par9 = 4;
                    break;
                case 4:
                case 5:
                    par9 = 3;
            }
        }

        return par9;
    }

    public int damageDropped(int par1)
    {
        return par1 != 3 && par1 != 4 ? par1 : 2;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return (Item)Item.itemRegistry.getObject(name);
    }
    
    public int quantityDropped(Random par1)
    {
        return (int) Math.pow(8, type);
    }
    
    protected ItemStack createStackedBlock(int par1)
    {
        return par1 != 3 && par1 != 4 ? super.createStackedBlock(par1) : new ItemStack(Item.getItemFromBlock(this), 1, 2);
    }

    public int getRenderType()
    {
        return 39;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
    {
        par3.add(new ItemStack(par1, 1, 0));
        par3.add(new ItemStack(par1, 1, 1));
        par3.add(new ItemStack(par1, 1, 2));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1)
    {
        this.Icon = new IIcon[type_icon.length];
        
        for (int i = 0; i < this.Icon.length; ++i)
        {
            if (type_icon[i] == null)
            {
                this.Icon[i] = this.Icon[i - 1];
            }
            else
            {
                this.Icon[i] = par1.registerIcon(this.getTextureName() + "_" + type_icon[i]);
            }
        }
        
        this.topIcon = par1.registerIcon(this.getTextureName() + "_" + "top");
        this.chiseled_topIcon = par1.registerIcon(this.getTextureName() + "_" + "chiseled_top");
        this.lines_topIcon = par1.registerIcon(this.getTextureName() + "_" + "lines_top");
        this.botIcon = par1.registerIcon(this.getTextureName() + "_" + "bottom");
    }
    
    public MapColor getMapColor(int p_149728_1_)
    {
        return MapColor.quartzColor;
    }
}