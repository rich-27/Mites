package com.richunderscore27.mites.init;

import com.richunderscore27.mites.block.*;
import com.richunderscore27.mites.item.itemblock.ItemBlockMiteyMud;
import com.richunderscore27.mites.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks
{
    public static final Block colony = new BlockColony();
    public static final Block miteyMud = new BlockMiteyMud();
    public static final Block miteyPool = new BlockMiteyPool();
    public static final Block enrichedWater = new BlockFluidEnrichedWater();

    public static void init()
    {
        GameRegistry.registerBlock(colony, Names.Blocks.COLONY);
        GameRegistry.registerBlock(miteyMud, ItemBlockMiteyMud.class, Names.Blocks.MITEY_MUD);
        GameRegistry.registerBlock(miteyPool, Names.Blocks.MITEY_POOL);
        GameRegistry.registerBlock(enrichedWater, Names.BlockFluids.ENRICHED_WATER);
    }
}