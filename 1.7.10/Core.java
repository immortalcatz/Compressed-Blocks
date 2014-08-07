package ztk.compressedblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Core.modid, name = "Compressed Blocks", version = "1.0.1.0")

public class Core
{
	public static final String modid = "compressedblocks";
	
	public final static String type[] = {null, "lc", "mc", "hc"};
	public final static int type_num[] = {0, 1, 2, 3};
	
	public static boolean ConfigAvailableLCB;
	public static boolean ConfigAvailableMCB;
	public static boolean ConfigAvailableHCB;
	
	public static boolean ConfigCraftingLCB;
	public static boolean ConfigCraftingMCB;
	public static boolean ConfigCraftingHCB;
	
	public static boolean ConfigEasyCraftingLCB;
	public static boolean ConfigEasyCraftingMCB;
	public static boolean ConfigEasyCraftingHCB;
	
	public static boolean ConfigSmeltingLCB;
	public static boolean ConfigSmeltingMCB;
	public static boolean ConfigSmeltingHCB;
	
	public static int ConfigUnCraftingLCB;
	public static int ConfigUnCraftingMCB;
	public static int ConfigUnCraftingHCB;

	@EventHandler
	public void preInit(FMLPreInitializationEvent Event)
	{
		/** Configuration file */
		Configuration config = new Configuration(Event.getSuggestedConfigurationFile());
		config.load();
		
        ConfigAvailableLCB = config.get(Configuration.CATEGORY_GENERAL, "Available Low compressed blocks", true, "Whether blocks to be available in the game").getBoolean(true);
        ConfigAvailableMCB = config.get(Configuration.CATEGORY_GENERAL, "Available Medium compressed blocks", true, "Whether blocks to be available in the game").getBoolean(true);
        ConfigAvailableHCB = config.get(Configuration.CATEGORY_GENERAL, "Available High compressed blocks", true, "Whether blocks to be available in the game").getBoolean(true);
		
        ConfigCraftingLCB = config.get(Configuration.CATEGORY_GENERAL, "Crafting Low compressed blocks", true, "Whether blocks can be crafted.").getBoolean(true);
        ConfigCraftingMCB = config.get(Configuration.CATEGORY_GENERAL, "Crafting Medium compressed blocks", true, "Whether blocks can be crafted.").getBoolean(true);
        ConfigCraftingHCB = config.get(Configuration.CATEGORY_GENERAL, "Crafting High compressed blocks", true, "Whether blocks can be crafted.").getBoolean(true);
        
        ConfigEasyCraftingLCB = config.get(Configuration.CATEGORY_GENERAL, "Easy Crafting Low compressed blocks", false, "Whether blocks can be easily crafted.").getBoolean(true);
        ConfigEasyCraftingMCB = config.get(Configuration.CATEGORY_GENERAL, "Easy Crafting Medium compressed blocks", false, "Whether blocks can be easily crafted.").getBoolean(true);
        ConfigEasyCraftingHCB = config.get(Configuration.CATEGORY_GENERAL, "Easy Crafting High compressed blocks", false, "Whether blocks can be easily crafted.").getBoolean(true);
		
        ConfigSmeltingLCB = config.get(Configuration.CATEGORY_GENERAL, "Smelting Low compressed blocks", true, "Whether blocks can be smelted.").getBoolean(true);
        ConfigSmeltingMCB = config.get(Configuration.CATEGORY_GENERAL, "Smelting Medium compressed blocks", true, "Whether blocks can be smelted.").getBoolean(true);
        ConfigSmeltingHCB = config.get(Configuration.CATEGORY_GENERAL, "Smelting High compressed blocks", true, "Whether blocks can be smelted.").getBoolean(true);
        
        ConfigUnCraftingLCB = config.get(Configuration.CATEGORY_GENERAL, "Uncrafting Low compressed blocks", 1, "How many water buckets to use for uncrafting?" + config.NEW_LINE + "-1 : recipe unavailable" + config.NEW_LINE + "0/1/2/4/8 : available").getInt();
        ConfigUnCraftingMCB = config.get(Configuration.CATEGORY_GENERAL, "Uncrafting Medium compressed blocks", 4, "How many lava buckets to use for uncrafting?" + config.NEW_LINE + "-1 : recipe unavailable" + config.NEW_LINE + "0/1/2/4/8 : available").getInt();
        ConfigUnCraftingHCB = config.get(Configuration.CATEGORY_GENERAL, "Uncrafting High compresse blocks", 8, "How many milk buckets to use for uncrafting?" + config.NEW_LINE + "-1 : recipe unavailable" + config.NEW_LINE + "0/1/2/4/8 : available").getInt();
		
		config.save();
		
		String items[][] =
		{
			{null, null, null},
			{"low_adhesive", "slime_ball", "water_bucket"},
			{"medium_adhesive", "magma_cream", "lava_bucket"},
			{"high_adhesive", "high_magma_cream" ,"milk_bucket"}
		};
		
		/** Name of items */
		String low_adhesive = "low_adhesive";
		String medium_adhesive = "medium_adhesive";
		String little_medium_adhesive = "little_medium_adhesive";
		String very_medium_adhesive = "very_medium_adhesive";
		String high_magma_cream = "high_magma_cream";
		String high_ender_pearl = "high_ender_pearl";
		String high_adhesive = "high_adhesive";
		String little_high_adhesive = "little_high_adhesive";
		String slime_cream = "slime_cream";
		String very_high_adhesive = "very_high_adhesive";
		
		/** New 4 creative tab [items/low blocks/medium blocks/high blocks] */
		CreativeTabs tab[] = 
		{
			new CreativeTabs("tab[0]")
			{
				public Item getTabIconItem()
				{
					return GameRegistry.findItem(modid, "slime_cream");
				}
			},	
			
			new CreativeTabs("tab_" + type[1] + "_blocks")
			{
				public Item getTabIconItem()
				{
					return Item.getItemFromBlock(GameRegistry.findBlock(modid, type[1] + "_blockIron"));
				}
			},
						
			new CreativeTabs("tab_" + type[2] + "_blocks")
			{
				public Item getTabIconItem()
				{
					return Item.getItemFromBlock(GameRegistry.findBlock(modid, type[2] + "_blockGold"));
				}
			},
					
			new CreativeTabs("tab_" + type[3] + "_blocks")
			{
				public Item getTabIconItem()
				{
					return Item.getItemFromBlock(GameRegistry.findBlock(modid, type[3] + "_blockDiamond"));
				}
			}
		};
		
		/** Registration items */
		GameRegistry.registerItem((new ItemCompressed(1)).setUnlocalizedName(low_adhesive).setCreativeTab(tab[0]).setTextureName(modid + ":" + low_adhesive), low_adhesive);
		GameRegistry.registerItem((new ItemCompressed(2)).setUnlocalizedName(medium_adhesive).setCreativeTab(tab[0]).setTextureName(modid + ":" + medium_adhesive), medium_adhesive);
		GameRegistry.registerItem((new ItemCompressed(2)).setUnlocalizedName(little_medium_adhesive).setCreativeTab(tab[0]).setTextureName(modid + ":" + little_medium_adhesive), little_medium_adhesive);
		GameRegistry.registerItem((new ItemCompressed(2)).setUnlocalizedName(very_medium_adhesive).setCreativeTab(tab[0]).setTextureName(modid + ":" + very_medium_adhesive), very_medium_adhesive);
		GameRegistry.registerItem((new ItemCompressed(3)).setUnlocalizedName(high_magma_cream).setCreativeTab(tab[0]).setTextureName(modid + ":" + high_magma_cream), high_magma_cream);
		GameRegistry.registerItem((new ItemCompressed(3)).setUnlocalizedName(high_ender_pearl).setCreativeTab(tab[0]).setTextureName(modid + ":" + high_ender_pearl), high_ender_pearl);
		GameRegistry.registerItem((new ItemCompressed(3)).setUnlocalizedName(high_adhesive).setCreativeTab(tab[0]).setTextureName(modid + ":" + high_adhesive), high_adhesive);
		GameRegistry.registerItem((new ItemCompressed(3)).setUnlocalizedName(little_high_adhesive).setCreativeTab(tab[0]).setTextureName(modid + ":" + little_high_adhesive), little_high_adhesive);
		GameRegistry.registerItem((new ItemCompressed(3)).setUnlocalizedName(slime_cream).setCreativeTab(tab[0]).setTextureName(modid + ":" + slime_cream), slime_cream);
		GameRegistry.registerItem((new ItemCompressed(3)).setUnlocalizedName(very_high_adhesive).setCreativeTab(tab[0]).setTextureName(modid + ":" + very_high_adhesive), very_high_adhesive);
		
		String blocks[][] =
		{
			{null, null, null},
			{"4", "stonebrick", "stonebricksmooth"},
			{null, "stone", null},
			{null, "cobblestone", "stonebrick"},
			{null, "mossy_cobblestone", "stoneMoss"},
			{null, "gravel", null},				
			{null, "dirt", "dirt.default"},				
			{null, "sand", null},
			{"4", "sandstone", "sandStone"},
			{null, "end_stone", "whiteStone"},
			{null, "sponge", null},
			{"6", "planks", "wood"},
			{null, "bookshelf", null},
			{"4", "log", null},
			{"2", "log2", "log"},
			{null, "pumpkin", null},
			{null, "lit_pumpkin", "litpumpkin"},
			{null, "melon", null},
			{null, "glass", null},
			{"16", "stained_glass", "stainedGlass"},
			{"16", "wool", null},
			{null, "tnt", null},
			{null, "redstone_lamp", "redstoneLight"},
			{null, "glowstone", "lightgem"},
			{null, "soul_sand", "hellsand"},
			{null, "obsidian", null},
			{null, "nether_brick", "netherBrick"},
			{null, "netherrack", "hellrock"},
			{null, "quartz_ore", "netherquartz"},
			{"3", "quartz_block", "quartzBlock"},
			{null, "snow", null},
			{null, "ice", null},
			{null, "packed_ice", "icePacked"},
			{null, "clay", null},
			{null, "hardened_clay", "clayHardened"},
			{"16", "stained_hardened_clay", "clayHardenedStained"},
			{null, "brick_block", "brick"},
			{null, "coal_block", "blockCoal"},
			{null, "iron_block", "blockIron"},
			{null, "gold_block", "blockGold"},
			{null, "diamond_block", "blockDiamond"},
			{null, "lapis_block", "blockLapis"},
			{null, "emerald_block", "blockEmerald"},
			{null, "redstone_block", "blockRedstone"},
			{null, "coal_ore", "oreCoal"},
			{null, "iron_ore", "oreIron"},
			{null, "gold_ore", "oreGold"},
			{null, "diamond_ore", "oreDiamond"},
			{null, "lapis_ore", "oreLapis"},
			{null, "emerald_ore", "oreEmerald"},
			{null, "redstone_ore", "oreRedstone"}
		};
		
		String smelt[][][] =
		{
			{
				{null, null, null, null},
				{null, "stonebrick", "stone", "0.35"},
				{null, "sand", "glass", "0.1"},
				{null, "clay", "clayHardened", "0.35"}
			},
			
			{
				{null, null, null, null},
				{"1", "log", "coal", "0.15"},
				{"1", "log2", "coal", "0.15"},
				{null, "hellrock", "netherbrick", "0.1"},
				{null, "oreQuartz", "quartz", "0.2"},		
				{null, "oreCoal", "coal", "0.1"},
				{null, "oreIron", "iron_ingot", "0.7"},
				{null, "oreGold", "gold_ingot", "1.0"},
				{null, "oreDiamond", "diamond", "1.0"},
				{"4", "oreLapis", "dye", "0.2"},
				{null, "oreEmerald", "emerald", "1.0"},
				{null, "oreRedstone", "redstone", "0.7"}
			}
		};
		
		/** Name of blocks */
		String bedrock = "bedrock";
		String stonebrick = "stonebrick";
		String stone = "stone";
		String cobblestone[] = {"cobblestone", "stonebrick"};
		String cobblestone_mossy[] = {"mossy_cobblestone", "stoneMoss", "cobblestone_mossy"};
		String gravel = "gravel";
		String dirt = "dirt";
		String sand = "sand";
		String sandstone[] = {"sandstone", "sandStone"};
		String end_stone[] = {"end_stone", "whiteStone"};
		String sponge = "sponge";
		String wood[] = {"wood", "planks"};
		String bookshelf = "bookshelf";
		String log = "log";
		String pumpkin = "pumpkin";
		String melon = "melon";
		String glass = "glass";
		String stained_glass[] = {"stained_glass", "stainedGlass", "glass"};
		String wool[] = {"wool", "cloth"};
		String tnt = "tnt";
		String redstone_lamp[] = {"redstone_lamp", "redstoneLight"};
		String glowstone[] = {"glowstone", "lightgem"};
		String soul_sand[] = {"soul_sand", "hellsand"};
		String obsidian = "obsidian";
		String nether_brick[] = {"nether_brick", "netherBrick"};
		String netherrack[] = {"netherrack", "hellrock"};
		String quartz_ore[] = {"quartz_ore", "netherquartz"};
		String quartz_block[] = {"quartz_block", "quartzBlock"};
		String snow = "snow";
		String ice = "ice";
		String ice_packed[] = {"packed_ice", "icePacked", "ice_packed"};
		String clay = "clay";
		String hardened_clay[] = {"hardened_clay", "clayHardened"};
		String hardened_clay_stained[] = {"hardened_clay_stained", "clayHardenedStained", "stained_hardened_clay"};
		String brick = "brick";
		String coal_block[] = {"coal_block", "blockCoal"};
		String iron_block[] = {"iron_block", "blockIron"};
		String gold_block[] = {"gold_block", "blockGold"};
		String diamond_block[] = {"diamond_block", "blockDiamond"};
		String lapis_block[] = {"lapis_block", "blockLapis"};
		String emerald_block[] = {"emerald_block", "blockEmerald"};
		String redstone_block[] = {"redstone_block", "blockRedstone"};
		String coal_ore[] = {"coal_ore", "oreCoal"};
		String iron_ore[] = {"iron_ore", "oreIron"};
		String gold_ore[] = {"gold_ore", "oreGold"};
		String diamond_ore[] = {"diamond_ore", "oreDiamond"};
		String lapis_ore[] = {"lapis_ore", "oreLapis"};
		String emerald_ore[] = {"emerald_ore", "oreEmerald"};
		String redstone_ore[] = {"redstone_ore", "oreRedstone"};
		
		/** Registration Compressed blocks */
		for (int i = 1; i <= 3; ++i)
		{
			if ((i == 1 && ConfigAvailableLCB == false) || (i == 2 && ConfigAvailableMCB == false) || (i == 3 && ConfigAvailableHCB == false))
			{
				i += 1;
			}
				
			if (i > 0 && i <= 3)
			{
				GameRegistry.registerBlock((new BlockCompressed(type_num[i], bedrock, Material.rock)).setCreativeTab(tab[i]).setBlockUnbreakable().setResistance((float) (Math.pow(8, type_num[i]) * 6000000.0F)).setStepSound(Block.soundTypePiston).setBlockName(bedrock).setBlockTextureName(modid + ":" + type[i] + "/" + bedrock), ItemBlockCompressed.class, type[i] + "_" + bedrock);
				GameRegistry.registerBlock((new BlockCompressedStoneBrick(type_num[i], stonebrick)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 1.5F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypePiston).setBlockName(stonebrick + "smooth").setBlockTextureName(modid + ":" + type[i] + "/" + stonebrick), ItemBlockCompressedWithMetadata.class, type[i] + "_" + stonebrick + "smooth");
				GameRegistry.registerBlock((new BlockCompressedStone(type_num[i], stone, Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 1.5F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypePiston).setBlockName(stone).setBlockTextureName(modid + ":" + type[i] + "/" + stone), ItemBlockCompressed.class, type[i] + "_" + stone);
				GameRegistry.registerBlock((new BlockCompressed(type_num[i], cobblestone[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 2.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypePiston).setBlockName(cobblestone[1]).setBlockTextureName(modid + ":" + type[i] + "/" + cobblestone[0]), ItemBlockCompressed.class, type[i] + "_" + cobblestone[1]);
				GameRegistry.registerBlock((new BlockCompressed(type_num[i], cobblestone_mossy[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 2.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypePiston).setBlockName(cobblestone_mossy[1]).setBlockTextureName(modid + ":" + type[i] + "/" + cobblestone_mossy[2]), ItemBlockCompressed.class, type[i] + "_" + cobblestone_mossy[1]);
				GameRegistry.registerBlock((new BlockCompressedGravel(type_num[i], gravel)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.6F)).setStepSound(Block.soundTypeGravel).setBlockName(gravel).setBlockTextureName(modid + ":" + type[i] + "/" + gravel), ItemBlockCompressed.class, type[i] + "_" + gravel);
				GameRegistry.registerBlock((new BlockCompressedDirt(type_num[i], dirt, Material.ground)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.5F)).setStepSound(Block.soundTypeGravel).setBlockName(dirt + ".default").setBlockTextureName(modid + ":" + type[i] + "/" + dirt), ItemBlockCompressed.class, type[i] + "_" + dirt + ".default");
				GameRegistry.registerBlock((new BlockCompressedSand(type_num[i], modid + ":" + type[i] + "/")).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.5F)).setStepSound(Block.soundTypeSand).setBlockName(sand).setBlockTextureName(modid + ":" + type[i] + "/" + sand), ItemBlockCompressedWithMetadata.class, type[i] + "_" + sand);
				GameRegistry.registerBlock((new BlockCompressedSandStone(type_num[i], sandstone[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.8F)).setStepSound(Block.soundTypePiston).setBlockName(sandstone[1]).setBlockTextureName(modid + ":" + type[i] + "/" + sandstone[0]), ItemBlockCompressedWithMetadata.class, type[i] + "_" + sandstone[1]);
				GameRegistry.registerBlock((new BlockCompressed(type_num[i], end_stone[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 15.0F)).setStepSound(Block.soundTypePiston).setBlockName(end_stone[1]).setBlockTextureName(modid + ":" + type[i] + "/" + end_stone[0]), ItemBlockCompressed.class, type[i] + "_" + end_stone[1]);
				GameRegistry.registerBlock((new BlockCompressedWood(type_num[i], wood[1])).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 2.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypeWood).setBlockName(wood[0]).setBlockTextureName(modid + ":" + type[i] + "/" + wood[1]), ItemBlockCompressedWithMetadata.class, type[i] + "_" + wood[0]);
				GameRegistry.registerBlock((new BlockCompressedBookshelf(type_num[i], bookshelf, Material.wood)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 1.5F)).setStepSound(Block.soundTypeWood).setBlockName(bookshelf).setBlockTextureName(modid + ":" + type[i] + "/" + bookshelf), ItemBlockCompressed.class, type[i] + "_" + bookshelf);
				GameRegistry.registerBlock((new BlockCompressedOldLog(type_num[i], log, Material.wood)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 2.0F)).setStepSound(Block.soundTypeWood).setBlockName(log).setBlockTextureName(modid + ":" + type[i] + "/" + log), ItemBlockCompressedWithMetadata.class, type[i] + "_" + log);
				GameRegistry.registerBlock((new BlockCompressedNewLog(type_num[i], log + "2", Material.wood)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 2.0F)).setStepSound(Block.soundTypeWood).setBlockName(log).setBlockTextureName(modid + ":" + type[i] + "/" + log), ItemBlockCompressedWithMetadata.class, type[i] + "_" + log + "2");
				GameRegistry.registerBlock((new BlockCompressedPumpkin(type_num[i], pumpkin, Material.gourd, false)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 1.0F)).setStepSound(Block.soundTypeWood).setBlockName(pumpkin).setBlockTextureName(modid + ":" + type[i] + "/" + pumpkin), ItemBlockCompressed.class, type[i] + "_" + pumpkin);
				GameRegistry.registerBlock((new BlockCompressedPumpkin(type_num[i], "lit_" + pumpkin, Material.gourd, true)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 1.0F)).setLightLevel(1.0F).setStepSound(Block.soundTypeWood).setBlockName("lit" + pumpkin).setBlockTextureName(modid + ":" + type[i] + "/" + pumpkin), ItemBlockCompressed.class, type[i] + "_lit" + pumpkin);
				GameRegistry.registerBlock((new BlockCompressedMelon(type_num[i], melon, Material.gourd)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 1.0F)).setStepSound(Block.soundTypeWood).setBlockName(melon).setBlockTextureName(modid + ":" + type[i] + "/" + melon), ItemBlockCompressed.class, type[i] + "_" + melon);
				GameRegistry.registerBlock((new BlockCompressedSponge(type_num[i], sponge, Material.sponge)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.6F)).setStepSound(Block.soundTypeGrass).setBlockName(sponge).setBlockTextureName(modid + ":" + type[i] + "/" + sponge), ItemBlockCompressed.class, type[i] + "_" + sponge);
				GameRegistry.registerBlock((new BlockCompressedGlass(type_num[i], modid + ":" + type[i] + "/" + glass, Material.glass, false)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.3F)).setStepSound(Block.soundTypeGlass).setBlockName(glass).setBlockTextureName(modid + ":" + type[i] + "/" + glass), ItemBlockCompressed.class, type[i] + "_" + glass);
				GameRegistry.registerBlock((new BlockCompressedStainedGlass(type_num[i], stained_glass[2], Material.glass)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.3F)).setStepSound(Block.soundTypeGlass).setBlockName(stained_glass[1]).setBlockTextureName(modid + ":" + type[i] + "/" + stained_glass[2]), ItemBlockCompressedWithMetadata.class, type[i] + "_" + stained_glass[1]);
				GameRegistry.registerBlock((new BlockCompressedWool(type_num[i], wool[0], Material.cloth)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.6F)).setStepSound(Block.soundTypeCloth).setBlockName(wool[1]).setBlockTextureName(modid + ":" + type[i] + "/" + wool[0] + "_colored"), ItemBlockCompressedWithMetadata.class, type[i] + "_" + wool[1]);
				GameRegistry.registerBlock((new BlockCompressedTNT(type_num[i], tnt, Material.tnt)).setCreativeTab(tab[i]).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName(tnt).setBlockTextureName(modid + ":" + type[i] + "/" + tnt), ItemBlockCompressed.class, type[i] + "_" + tnt);
				GameRegistry.registerBlock((new BlockCompressedRedstoneLight(type_num[i], redstone_lamp[0], Material.redstoneLight, false)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.3F)).setStepSound(Block.soundTypeGlass).setBlockName(redstone_lamp[1]).setBlockTextureName(modid + ":" + type[i] + "/" + redstone_lamp[0] + "_off"), ItemBlockCompressed.class, type[i] + "_" + redstone_lamp[1]);
				GameRegistry.registerBlock((new BlockCompressedRedstoneLight(type_num[i], "lit_" + redstone_lamp[0], Material.redstoneLight, true)).setHardness((float) (Math.pow(8, type_num[i]) * 0.3F)).setStepSound(Block.soundTypeGlass).setBlockName(redstone_lamp[1]).setBlockTextureName(modid + ":" + type[i] + "/" + redstone_lamp[0] + "_on"), ItemBlockCompressed.class, type[i] + "_lit_" + redstone_lamp[1]);
				GameRegistry.registerBlock((new BlockCompressedGlowstone(type_num[i], glowstone[0], Material.glass)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.3F)).setStepSound(Block.soundTypeGlass).setLightLevel(1.0F).setBlockName(glowstone[1]).setBlockTextureName(modid + ":" + type[i] + "/" + glowstone[0]), ItemBlockCompressed.class, type[i] + "_" + glowstone[1]);
				GameRegistry.registerBlock((new BlockCompressedSoulSand(type_num[i], soul_sand[0], Material.sand)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.5F)).setStepSound(Block.soundTypeSand).setBlockName(soul_sand[1]).setBlockTextureName(modid + ":" + type[i] + "/" + soul_sand[0]), ItemBlockCompressed.class, type[i] + "_" + soul_sand[1]);
				GameRegistry.registerBlock((new BlockCompressedObsidian(type_num[i], obsidian, Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 50.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 2000.0F)).setStepSound(Block.soundTypePiston).setBlockName(obsidian).setBlockTextureName(modid + ":" + type[i] + "/" + obsidian), ItemBlockCompressed.class, type[i] + "_" + obsidian);
				GameRegistry.registerBlock((new BlockCompressed(type_num[i], nether_brick[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 2.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypePiston).setBlockName(nether_brick[1]).setBlockTextureName(modid + ":" + type[i] + "/" + nether_brick[0]), ItemBlockCompressed.class, type[i] + "_" + nether_brick[1]);
				GameRegistry.registerBlock((new BlockCompressedNetherrack(type_num[i], netherrack[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.4F)).setStepSound(Block.soundTypePiston).setBlockName(netherrack[1]).setBlockTextureName(modid + ":" + type[i] + "/" + netherrack[0]), ItemBlockCompressed.class, type[i] + "_" + netherrack[1]);
				GameRegistry.registerBlock((new BlockCompressedOre(type_num[i], quartz_ore[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypePiston).setBlockName(quartz_ore[1]).setBlockTextureName(modid + ":" + type[i] + "/" + quartz_ore[0]), ItemBlockCompressed.class, type[i] + "_" + quartz_ore[1]);
				GameRegistry.registerBlock((new BlockCompressedQuartz(type_num[i], quartz_block[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.8F)).setStepSound(Block.soundTypePiston).setBlockName(quartz_block[1]).setBlockTextureName(modid + ":" + type[i] + "/" + quartz_block[0]), ItemBlockCompressedWithMetadata.class, type[i] + "_" + quartz_block[1]);
				GameRegistry.registerBlock((new BlockCompressedSnowBlock(type_num[i], snow, Material.craftedSnow)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.2F)).setStepSound(Block.soundTypeSnow).setBlockName(snow).setBlockTextureName(modid + ":" + type[i] + "/" + snow), ItemBlockCompressed.class, type[i] + "_" + snow);
				GameRegistry.registerBlock((new BlockCompressedIce(type_num[i], modid + ":" + type[i] + "/" + ice, Material.ice)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.5F)).setLightOpacity(6 - type_num[i] * 2).setStepSound(Block.soundTypeGlass).setBlockName(ice).setBlockTextureName(modid + ":" + type[i] + "/" + ice), ItemBlockCompressed.class, type[i] + "_" + ice);
				GameRegistry.registerBlock((new BlockCompressedPackedIce(type_num[i],ice_packed[0], Material.packedIce)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.5F)).setStepSound(Block.soundTypeGlass).setBlockName(ice_packed[1]).setBlockTextureName(modid + ":" + type[i] + "/" + ice_packed[2]), ItemBlockCompressed.class, type[i] + "_" + ice_packed[1]);
				GameRegistry.registerBlock((new BlockCompressedClay(type_num[i], clay, Material.clay)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 0.6F)).setStepSound(Block.soundTypeGravel).setBlockName(clay).setBlockTextureName(modid + ":" + type[i] + "/" + clay), ItemBlockCompressed.class, type[i] + "_" + clay);
				GameRegistry.registerBlock((new BlockCompressedClayHardened(type_num[i], hardened_clay[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 1.25F)).setResistance((float) (Math.pow(8, type_num[i]) * 7.0F)).setStepSound(Block.soundTypePiston).setBlockName(hardened_clay[1]).setBlockTextureName(modid + ":" + type[i] + "/" + hardened_clay[0]), ItemBlockCompressed.class, type[i] + "_" + hardened_clay[1]);
				GameRegistry.registerBlock((new BlockCompressedClayHardenedStained(type_num[i], hardened_clay_stained[2], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 1.25F)).setResistance((float) (Math.pow(8, type_num[i]) * 7.0F)).setStepSound(Block.soundTypePiston).setBlockName(hardened_clay_stained[1]).setBlockTextureName(modid + ":" + type[i] + "/" + hardened_clay_stained[0]), ItemBlockCompressedWithMetadata.class, type[i] + "_" + hardened_clay_stained[1]);
				GameRegistry.registerBlock((new BlockCompressed(type_num[i], brick + "_block", Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 2.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypePiston).setBlockName(brick).setBlockTextureName(modid + ":" + type[i] + "/" + brick), ItemBlockCompressed.class, type[i] + "_" + brick);
				GameRegistry.registerBlock((new BlockCompressed(type_num[i], coal_block[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 50.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypePiston).setBlockName(coal_block[1]).setBlockTextureName(modid + ":" + type[i] + "/" + coal_block[0]), ItemBlockCompressed.class, type[i] + "_" + coal_block[1]);
				GameRegistry.registerBlock((new BlockCompressedCompressed(type_num[i], iron_block[0], MapColor.ironColor)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 5.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypeMetal).setBlockName(iron_block[1]).setBlockTextureName(modid + ":" + type[i] + "/" + iron_block[0]), ItemBlockCompressed.class, type[i] + "_" + iron_block[1]);
				GameRegistry.registerBlock((new BlockCompressedCompressed(type_num[i], gold_block[0], MapColor.goldColor)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypeMetal).setBlockName(gold_block[1]).setBlockTextureName(modid + ":" + type[i] + "/" + gold_block[0]), ItemBlockCompressed.class, type[i] + "_" + gold_block[1]);
				GameRegistry.registerBlock((new BlockCompressedCompressed(type_num[i], diamond_block[0], MapColor.diamondColor)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 5.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypeMetal).setBlockName(diamond_block[1]).setBlockTextureName(modid + ":" + type[i] + "/" + diamond_block[0]), ItemBlockCompressed.class, type[i] + "_" + diamond_block[1]);
				GameRegistry.registerBlock((new BlockCompressedCompressed(type_num[i], lapis_block[0], MapColor.lapisColor)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypePiston).setBlockName(lapis_block[1]).setBlockTextureName(modid + ":" + type[i] + "/" + lapis_block[0]), ItemBlockCompressed.class, type[i] + "_" + lapis_block[1]);
				GameRegistry.registerBlock((new BlockCompressedCompressed(type_num[i], emerald_block[0], MapColor.emeraldColor)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 5.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypeMetal).setBlockName(emerald_block[1]).setBlockTextureName(modid + ":" + type[i] + "/" + emerald_block[0]), ItemBlockCompressed.class, type[i] + "_" + emerald_block[1]);
				GameRegistry.registerBlock((new BlockCompressedCompressedPowered(type_num[i],redstone_block[0], MapColor.tntColor)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 5.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 10.0F)).setStepSound(Block.soundTypeMetal).setBlockName(redstone_block[1]).setBlockTextureName(modid + ":" + type[i] + "/" + redstone_block[0]), ItemBlockCompressed.class, type[i] + "_" + redstone_block[1]);
				GameRegistry.registerBlock((new BlockCompressedOre(type_num[i], coal_ore[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypePiston).setBlockName(coal_ore[1]).setBlockTextureName(modid + ":" + type[i] + "/" + coal_ore[0]), ItemBlockCompressed.class, type[i] + "_" + coal_ore[1]);
				GameRegistry.registerBlock((new BlockCompressedOre(type_num[i], iron_ore[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypePiston).setBlockName(iron_ore[1]).setBlockTextureName(modid + ":" + type[i] + "/" + iron_ore[0]), ItemBlockCompressed.class, type[i] + "_" + iron_ore[1]);
				GameRegistry.registerBlock((new BlockCompressedOre(type_num[i], gold_ore[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypePiston).setBlockName(gold_ore[1]).setBlockTextureName(modid + ":" + type[i] + "/" + gold_ore[0]), ItemBlockCompressed.class, type[i] + "_" + gold_ore[1]);
				GameRegistry.registerBlock((new BlockCompressedOre(type_num[i], diamond_ore[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypePiston).setBlockName(diamond_ore[1]).setBlockTextureName(modid + ":" + type[i] + "/" + diamond_ore[0]), ItemBlockCompressed.class, type[i] + "_" + diamond_ore[1]);
				GameRegistry.registerBlock((new BlockCompressedOre(type_num[i], lapis_ore[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypePiston).setBlockName(lapis_ore[1]).setBlockTextureName(modid + ":" + type[i] + "/" + lapis_ore[0]), ItemBlockCompressed.class, type[i] + "_" + lapis_ore[1]);
				GameRegistry.registerBlock((new BlockCompressedOre(type_num[i], emerald_ore[0], Material.rock)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypePiston).setBlockName(emerald_ore[1]).setBlockTextureName(modid + ":" + type[i] + "/" + emerald_ore[0]), ItemBlockCompressed.class, type[i] + "_" + emerald_ore[1]);
				GameRegistry.registerBlock((new BlockCompressedRedstoneOre(type_num[i], redstone_ore[0], Material.rock, false)).setCreativeTab(tab[i]).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypePiston).setBlockName(redstone_ore[1]).setBlockTextureName(modid + ":" + type[i] + "/" + redstone_ore[0]), ItemBlockCompressed.class, type[i] + "_" + redstone_ore[1]);
				GameRegistry.registerBlock((new BlockCompressedRedstoneOre(type_num[i], "lit_" + redstone_ore[0], Material.rock, true)).setLightLevel(type_num[i] * 0.125F + 0.625F).setHardness((float) (Math.pow(8, type_num[i]) * 3.0F)).setResistance((float) (Math.pow(8, type_num[i]) * 5.0F)).setStepSound(Block.soundTypePiston).setBlockName(redstone_ore[1]).setBlockTextureName(modid + ":" + type[i] + "/" + redstone_ore[0]), ItemBlockCompressed.class, type[i] + "_lit_" + redstone_ore[1]);
			}
		}
		
		/** Crafting items or not */
		if (ConfigEasyCraftingLCB == false)
		{
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, low_adhesive)), "sss", "ses", "sss", 's', (Item)Item.itemRegistry.getObject("slime_ball"), 'e', (Item)Item.itemRegistry.getObject("ender_pearl"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, medium_adhesive)), "mmm", "mem", "mmm", 'm', (Item)Item.itemRegistry.getObject("magma_cream"), 'e', (Item)Item.itemRegistry.getObject("ender_eye"));
			/** Coming soon
			GameRegistry.addShapelessRecipe(new ItemStack(GameRegistry.findItem(Core.modid, little_medium_adhesive)), GameRegistry.findItem(Core.modid, medium_adhesive), (Item)Item.itemRegistry.getObject("blaze_powder"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, very_medium_adhesive)), "sss", "sms", "sss", 's', (Item)Item.itemRegistry.getObject("slime_ball"), 'm', GameRegistry.findItem(Core.modid, little_medium_adhesive));
			*/
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, high_magma_cream)), "bbb", "bsb", "bbb", 'b', (Item)Item.itemRegistry.getObject("blaze_powder"), 's', (Item)Item.itemRegistry.getObject("slime_ball"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, high_magma_cream)), " b ", "bsb", " b ", 'b', (Item)Item.itemRegistry.getObject("blaze_rod"), 's', (Item)Item.itemRegistry.getObject("slime_ball"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, high_magma_cream)), "b b", " s ", "b b", 'b', (Item)Item.itemRegistry.getObject("blaze_rod"), 's', (Item)Item.itemRegistry.getObject("slime_ball"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, high_ender_pearl)), "bbb", "beb", "bbb", 'b', (Item)Item.itemRegistry.getObject("blaze_powder"), 'e', (Item)Item.itemRegistry.getObject("ender_pearl"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, high_ender_pearl)), " b ", "beb", " b ", 'b', (Item)Item.itemRegistry.getObject("blaze_rod"), 'e', (Item)Item.itemRegistry.getObject("ender_pearl"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, high_ender_pearl)), "b b", " e ", "b b", 'b', (Item)Item.itemRegistry.getObject("blaze_rod"), 'e', (Item)Item.itemRegistry.getObject("ender_pearl"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, high_adhesive)), "mmm", "mem", "mmm", 'm', GameRegistry.findItem(modid, high_magma_cream), 'e', GameRegistry.findItem(modid, high_ender_pearl));
			/** Coming soon
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, slime_cream)), "   ", "sbs", "   ", 's', (Item)Item.itemRegistry.getObject("slime_ball"), 'b', (Item)Item.itemRegistry.getObject("blaze_powder"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, slime_cream)), " s ", " b ", " s ", 's', (Item)Item.itemRegistry.getObject("slime_ball"), 'b', (Item)Item.itemRegistry.getObject("blaze_powder"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, slime_cream)), "s  ", " b ", "  s", 's', (Item)Item.itemRegistry.getObject("slime_ball"), 'b', (Item)Item.itemRegistry.getObject("blaze_powder"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, slime_cream)), "  s", " b ", "s  ", 's', (Item)Item.itemRegistry.getObject("slime_ball"), 'b', (Item)Item.itemRegistry.getObject("blaze_powder"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, slime_cream), 2), " s ", "sbs", " s ", 's', (Item)Item.itemRegistry.getObject("slime_ball"), 'b', (Item)Item.itemRegistry.getObject("blaze_rod"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, slime_cream), 2), "s s", " b ", "s s", 's', (Item)Item.itemRegistry.getObject("slime_ball"), 'b', (Item)Item.itemRegistry.getObject("blaze_rod"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, little_high_adhesive)), "bbb", "bhb", "bbb", 'b', (Item)Item.itemRegistry.getObject("blaze_powder"), 'h', GameRegistry.findItem(Core.modid, high_adhesive));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, little_high_adhesive)), " b ", "bhb", " b ", 'b', (Item)Item.itemRegistry.getObject("blaze_rod"), 'h', GameRegistry.findItem(Core.modid, high_adhesive));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, little_high_adhesive)), "b b", " h ", "b b", 'b', (Item)Item.itemRegistry.getObject("blaze_rod"), 'h', GameRegistry.findItem(Core.modid, high_adhesive));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(Core.modid, very_high_adhesive)), "sss", "shs", "sss", 's', GameRegistry.findItem(Core.modid, slime_cream), 'h', GameRegistry.findItem(Core.modid, little_high_adhesive));
			*/
		}
		else
		{
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, high_magma_cream)), "bbb", "bsb", "bbb", 'b', (Item)Item.itemRegistry.getObject("blaze_powder"), 's', (Item)Item.itemRegistry.getObject("slime_ball"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, high_magma_cream)), " b ", "bsb", " b ", 'b', (Item)Item.itemRegistry.getObject("blaze_rod"), 's', (Item)Item.itemRegistry.getObject("slime_ball"));
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findItem(modid, high_magma_cream)), "b b", " s ", "b b", 'b', (Item)Item.itemRegistry.getObject("blaze_rod"), 's', (Item)Item.itemRegistry.getObject("slime_ball"));
		}
		
		/** Crafting Compressed blocks or not */
		for (int i = 1; i <= 3; ++i)
		{
			if ((i == 1 && (ConfigAvailableLCB == false || ConfigCraftingLCB == false)) || (i == 2 && (ConfigAvailableMCB == false || ConfigCraftingMCB == false)) || (i == 1 && (ConfigAvailableHCB == false || ConfigCraftingHCB == false)))
			{
				i += 1;
			}
				
			if (i > 0 && i <= 3)
			{
				for (int j = 1; j < blocks.length; ++j)
				{
					if (blocks[j][0] == null)
					{
						GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2])), "bbb", "bab", "bbb", 'b', i == 1 ? (Block)Block.blockRegistry.getObject(blocks[j][1]) : GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2]), 'a', ConfigEasyCraftingLCB == false ? GameRegistry.findItem(modid, items[i][0]) : (i == 3 ? GameRegistry.findItem(modid, items[i][1]) : (Item)Item.itemRegistry.getObject(items[i][1])));
					}
					else
					{
						for (int k = 0; k < Integer.parseInt(blocks[j][0]); ++k)
						{
							GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), 1, k), "bbb", "bab", "bbb", 'b', i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 1, k) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2]), 1, k), 'a', ConfigEasyCraftingLCB == false ? GameRegistry.findItem(modid, items[i][0]) : (i == 3 ? GameRegistry.findItem(modid, items[i][1]) : (Item)Item.itemRegistry.getObject(items[i][1])));							
						}
					}
				}
			}
		}
		
		/** Uncrafting Compressed blocks or not */
		for (int i = 1; i <= 3; ++i)
		{
			if (i == 1 && ConfigUnCraftingLCB == -1 || i == 2 && ConfigUnCraftingMCB == -1 || i == 1 && ConfigUnCraftingHCB == -1)
			{
				i += 1;
			}
				
			if (i > 0 && i <= 3)
			{
				for (int j = 1; j < blocks.length; ++j)
				{
					if ((i == 1 && ConfigUnCraftingLCB == 0) || (i == 2 && ConfigUnCraftingMCB == 0) || (i == 3 && ConfigUnCraftingHCB == 0))
					{
						if (blocks[j][0] == null)
						{
							GameRegistry.addShapelessRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2])), GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]));
						}
						else
						{
							for (int k = 0; k < Integer.parseInt(blocks[j][0]); ++k)
							{
								GameRegistry.addShapelessRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8, k) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2]), 8, k), new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), 1, k));	
							}
						}
					}
					else if (((i == 1 && ConfigUnCraftingLCB == 1) || (i == 2 && ConfigUnCraftingMCB == 1) || (i == 3 && ConfigUnCraftingHCB == 1)) || (i == 1 && (ConfigUnCraftingLCB != 2 && ConfigUnCraftingLCB != 4 && ConfigUnCraftingLCB != 8)))
					{
						if (blocks[j][0] == null)
						{
							GameRegistry.addShapelessRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2])), GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), (Item)Item.itemRegistry.getObject(items[i][2]));
						}
						else
						{
							for (int k = 0; k < Integer.parseInt(blocks[j][0]); ++k)
							{
								GameRegistry.addShapelessRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8, k) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2]), 8, k), new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), 1, k), (Item)Item.itemRegistry.getObject(items[i][2]));					
							}
						}
					}
					else if ((i == 1 && ConfigUnCraftingLCB == 2) || (i == 2 && ConfigUnCraftingMCB == 2) || (i == 3 && ConfigUnCraftingHCB == 2))
					{
						if (blocks[j][0] == null)
						{
							GameRegistry.addShapelessRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2])), GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), (Item)Item.itemRegistry.getObject(items[i][2]), (Item)Item.itemRegistry.getObject(items[i][2]));
						}
						else
						{
							for (int k = 0; k < Integer.parseInt(blocks[j][0]); ++k)
							{
								GameRegistry.addShapelessRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8, k) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2]), 8, k), new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), 1, k), (Item)Item.itemRegistry.getObject(items[i][2]), (Item)Item.itemRegistry.getObject(items[i][2]));				
							}
						}
					}
					else if (((i == 1 && ConfigUnCraftingLCB == 4) || (i == 2 && ConfigUnCraftingMCB == 4) || (i == 3 && ConfigUnCraftingHCB == 4)) || (i == 2 && (ConfigUnCraftingMCB != 4 && ConfigUnCraftingMCB != 8)))
					{
						if (blocks[j][0] == null)
						{
							GameRegistry.addShapedRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2])), " b ", "bcb", " b ", 'c', GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), 'b', (Item)Item.itemRegistry.getObject(items[i][2]));
							GameRegistry.addShapedRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2])), "b b", " c ", "b b", 'c', GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), 'b', (Item)Item.itemRegistry.getObject(items[i][2]));
						}
						else
						{
							for (int k = 0; k < Integer.parseInt(blocks[j][0]); ++k)
							{
								GameRegistry.addShapedRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8, k) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2]), 8, k), " b ", "bcb", " b ", 'c', new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), 1, k), 'b', (Item)Item.itemRegistry.getObject(items[i][2]));					
								GameRegistry.addShapedRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8, k) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2]), 8, k), "b b", " c ", "b b", 'c', new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), 1, k), 'b', (Item)Item.itemRegistry.getObject(items[i][2]));					
							}
						}
					}
					else if (((i == 1 && ConfigUnCraftingLCB == 8) || (i == 2 && ConfigUnCraftingMCB == 8) || (i == 3 && ConfigUnCraftingHCB == 8)) || (i == 3 && ConfigUnCraftingHCB != 8))
					{
						if (blocks[j][0] == null)
						{
							GameRegistry.addShapedRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2])), " b ", "bcb", " b ", 'c', GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), 'b', (Item)Item.itemRegistry.getObject(items[i][2]));
						}
						else
						{
							for (int k = 0; k < Integer.parseInt(blocks[j][0]); ++k)
							{
								GameRegistry.addShapedRecipe(i == 1 ? new ItemStack((Block)Block.blockRegistry.getObject(blocks[j][1]), 8, k) : new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i-1] + "_" + blocks[j][1] : type[i-1] + "_" + blocks[j][2]), 8, k), " b ", "bcb", " b ", 'c', new ItemStack(GameRegistry.findBlock(modid, blocks[j][2] == null ? type[i] + "_" + blocks[j][1] : type[i] + "_" + blocks[j][2]), 1, k), 'b', (Item)Item.itemRegistry.getObject(items[i][2]));					
							}
						}
					}
				}
			}
		}
		
		/** Smelting compressed blocks or not */
		for (int i = 1; i <= 3; ++i)
		{
			if ((i == 1 && (ConfigAvailableLCB == false || ConfigSmeltingLCB == false)) || (i == 2 && (ConfigAvailableMCB == false || ConfigSmeltingMCB == false)) || (i == 1 && (ConfigAvailableHCB == false || ConfigSmeltingHCB == false)))
			{
				i += 1;
			}
				
			if (i > 0 && i <= 3)
			{
				for (int k = 0; k <= 1; ++k)
				{
					for (int j = 1; j < smelt[k].length; ++j)
					{
						if (smelt[k][j][0] == null)
						{
							GameRegistry.addSmelting(GameRegistry.findBlock(modid, type[i] + "_" + smelt[k][j][1]), k == 0 ? new ItemStack(GameRegistry.findBlock(modid, type[i] + "_" + smelt[k][j][2])) : new ItemStack((Item)Item.itemRegistry.getObject(smelt[k][j][2]), (int) Math.pow(8, type_num[i])), Float.parseFloat(smelt[k][j][3]));
						}
						else
						{
							GameRegistry.addSmelting(GameRegistry.findBlock(modid, type[i] + "_" + smelt[k][j][1]), new ItemStack((Item)Item.itemRegistry.getObject(smelt[k][j][2]), (int) Math.pow(8, type_num[i]), Integer.parseInt(smelt[k][j][0])), Float.parseFloat(smelt[k][j][3]));				
						}
					}
				}
			}
		}
	}
}