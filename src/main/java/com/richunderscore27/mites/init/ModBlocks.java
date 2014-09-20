package com.richunderscore27.mites.init;

import com.richunderscore27.mites.block.*;
import com.richunderscore27.mites.item.itemblock.ItemBlockMiteyMud;
import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
    public static final BlockMites colony = new BlockColony();
    public static final BlockMites miteyMud = new BlockMiteyMud();
    public static final BlockFluidMites miteyPool = new BlockFluidMiteyPool();

    public static void init()
    {
        GameRegistry.registerBlock(colony, Names.Blocks.COLONY);
        GameRegistry.registerBlock(miteyMud, ItemBlockMiteyMud.class, Names.Blocks.MITEY_MUD);
        GameRegistry.registerBlock(miteyPool, Names.BlockFluids.MITEY_POOL);
    }
}