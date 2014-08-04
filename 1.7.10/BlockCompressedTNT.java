package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedTNT extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon botIcon;
    
    private String name;
    private int type;
    
    protected BlockCompressedTNT(int type, String name, Material material)
    {
        super(material);
        
        this.name = name;
        this.type = type;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 0 ? this.botIcon : (par1 == 1 ? this.topIcon : this.blockIcon);
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
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
        this.topIcon = p_149651_1_.registerIcon(this.getTextureName() + "_top");
        this.botIcon = p_149651_1_.registerIcon(this.getTextureName() + "_bottom");
    }
}
