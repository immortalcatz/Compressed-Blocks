package ztk.compressedblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockCompressed extends ItemBlock
{
    private Block block;
    
	public ItemBlockCompressed(Block block)
	{
		super(block);
		this.block = block;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1, EntityPlayer par2, List par3, boolean par4)
	{
		if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_" + block.getUnlocalizedName().substring(5))) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_log2")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_block_mushroom2")))
		{
			
			String name = EnumChatFormatting.ITALIC + StatCollector.translateToLocal("compressed.low");
			
			if(par1.hasTagCompound() && par1.getTagCompound().hasKey("structureName"))
			{
				name = par1.getTagCompound().getString("structureName");
			}
			
			par3.add(EnumChatFormatting.BLUE + name);
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_" + block.getUnlocalizedName().substring(5))) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_log2")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_block_mushroom2")))
		{
			String name = StatCollector.translateToLocal("compressed.medium");
			
			if(par1.hasTagCompound() && par1.getTagCompound().hasKey("structureName"))
			{
				name = par1.getTagCompound().getString("structureName");
			}
			
			par3.add(EnumChatFormatting.GOLD + name);
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_" + block.getUnlocalizedName().substring(5))) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_log2")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_block_mushroom2")))
		{
			String name = EnumChatFormatting.BOLD + StatCollector.translateToLocal("compressed.high");
			
			if(par1.hasTagCompound() && par1.getTagCompound().hasKey("structureName"))
			{
				name = par1.getTagCompound().getString("structureName");
			}
			
			par3.add(EnumChatFormatting.GREEN + name);
		}
		else
		{
			String name = EnumChatFormatting.ITALIC + "Unknow block";
			
			if(par1.hasTagCompound() && par1.getTagCompound().hasKey("structureName"))
			{
				name = par1.getTagCompound().getString("structureName");
			}
			
			par3.add(EnumChatFormatting.GRAY + name);
		}
	}
}