package ztk.compressedblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemBlockCompressedWithMetadata extends ItemBlockCompressed  {
	
    private Block block;
    
	public ItemBlockCompressedWithMetadata(Block block) {
		super(block);
		
		setHasSubtypes(true);
		this.block = block;
	}
	
	public int getMetadata (int par1)
	{
		return par1;
	}
	
	public String getUnlocalizedName(ItemStack par1)
	{
		if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_clayHardenedStained")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_clayHardenedStained")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_clayHardenedStained")))
		{
			return getUnlocalizedName() + "." + ItemDye.field_150923_a[15 - par1.getItemDamage()];
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_stainedGlass")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_stainedGlass")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_stainedGlass")))
		{
			return getUnlocalizedName() + "." + ItemDye.field_150923_a[15 - par1.getItemDamage()];
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_log")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_log")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_log")))
		{
			return getUnlocalizedName() + "." + BlockCompressedOldLog.type_name[par1.getItemDamage()];
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_log2")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_log2")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_log2")))
		{
			return getUnlocalizedName() + "." + BlockCompressedNewLog.type_name[par1.getItemDamage()];
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_quartzBlock")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_quartzBlock")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_quartzBlock")))
		{
			return getUnlocalizedName() + "." + BlockCompressedQuartz.type_name[par1.getItemDamage()];
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_sand")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_sand")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_sand")))
		{
			return getUnlocalizedName() + "." + BlockCompressedSand.type_name[par1.getItemDamage()];
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_sandStone")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_sandStone")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_sandStone")))
		{
			return getUnlocalizedName() + "." + BlockCompressedSandStone.type_name[par1.getItemDamage()];
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_stonebricksmooth")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_stonebricksmooth")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_stonebricksmooth")))
		{
			return getUnlocalizedName() + "." + BlockStoneBrick.field_150142_a[par1.getItemDamage()];
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_wood")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_wood")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_wood")))
		{
			return getUnlocalizedName() + "." + BlockCompressedWood.field_150096_a[par1.getItemDamage()];
		}
		else if (getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "lc_cloth")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "mc_cloth")) || getItemFromBlock(block) == getItemFromBlock(GameRegistry.findBlock(Core.modid, "hc_cloth")))
		{
			return getUnlocalizedName() + "." + ItemDye.field_150923_a[15 - par1.getItemDamage()];
		}
		else
		{
			String type_name[] = {"unknow[0]", "unknow[1]", "unknow[2]", "unknow[3]", "unknow[4]", "unknow[5]", "unknow[6]", "unknow[7]", "unknow[8]", "unknow[9]", "unknow[10]", "unknow[11]", "unknow[12]", "unknow[13]", "unknow[14]", "unknow[15]"};
			return getUnlocalizedName() + "." + type_name[par1.getItemDamage()];
		}
	}
}