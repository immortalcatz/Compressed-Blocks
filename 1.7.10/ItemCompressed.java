package ztk.compressedblocks;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCompressed extends Item
{
    private int type;
    
	public ItemCompressed(int type)
    {
        this.type = type;
    }
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if (type == 1)
		{
			String name = EnumChatFormatting.ITALIC + StatCollector.translateToLocal("compressed.low");
			
			if(par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("structureName"))
			{
				name = par1ItemStack.getTagCompound().getString("structureName");
			}
			
			par3List.add(EnumChatFormatting.BLUE + name);
		}
		else if (type == 2)
		{
			String name = StatCollector.translateToLocal("compressed.medium");
			
			if(par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("structureName"))
			{
				name = par1ItemStack.getTagCompound().getString("structureName");
			}
			
			par3List.add(EnumChatFormatting.GOLD + name);
		}
		else
		{
			String name = EnumChatFormatting.BOLD + StatCollector.translateToLocal("compressed.high");
			
			if(par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("structureName"))
			{
				name = par1ItemStack.getTagCompound().getString("structureName");
			}
			
			par3List.add(EnumChatFormatting.GREEN + name);  
		}
	}
}