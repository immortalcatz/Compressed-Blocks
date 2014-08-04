package ztk.compressedblocks;

import java.util.Random;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedPumpkin extends BlockDirectional
{
    private boolean lit;
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon frontIcon;
    
    private String name;
    private int type;
    
    protected BlockCompressedPumpkin(int type, String name, Material material, boolean par4)
    {
        super(material);
        
        this.name = name;
        this.type = type;
        this.setTickRandomly(true);
        this.lit = par4;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.topIcon : (par1 == 0 ? this.topIcon : (par2 == 2 && par1 == 2 ? this.frontIcon : (par2 == 3 && par1 == 5 ? this.frontIcon : (par2 == 0 && par1 == 3 ? this.frontIcon : (par2 == 1 && par1 == 4 ? this.frontIcon : this.blockIcon)))));
    }
    
    public void onBlockPlacedBy(World par1, int par2, int par3, int par4, EntityLivingBase par5, ItemStack par6)
    {
        int l = MathHelper.floor_double((double)(par5.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1)
    {
        this.frontIcon = par1.registerIcon(this.getTextureName() + "_face_" + (this.lit ? "on" : "off"));
        this.topIcon = par1.registerIcon(this.getTextureName() + "_top");
        this.blockIcon = par1.registerIcon(this.getTextureName() + "_side");
    }
    
    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return (Item)Item.itemRegistry.getObject(name);
    }
    
    public int quantityDropped(Random par1)
    {
        return (int) Math.pow(8, type);
    }
}