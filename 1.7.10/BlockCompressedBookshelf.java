package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedBookshelf extends Block
{    
    private String name;
    private int type;
    
    public BlockCompressedBookshelf(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 != 1 && par1 != 0 ? super.getIcon(par1, par2) : GameRegistry.findBlock(Core.modid, type == 1 ? "lc_wood" : (type == 2 ? "mc_wood" : "hc_wood")).getBlockTextureFromSide(par1);
    }
    
    public int quantityDropped(Random par1)
    {
        return (int) Math.pow(8, type) * 3;
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return (Item)Item.itemRegistry.getObject("book");
    }
}