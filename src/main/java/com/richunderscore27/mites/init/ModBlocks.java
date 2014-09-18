package com.richunderscore27.mites.init;

import com.richunderscore27.mites.block.BlockColony;
import com.richunderscore27.mites.block.BlockMites;
import com.richunderscore27.mites.block.BlockMiteyMud;
import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
    public static final BlockMites colony = new BlockColony();
    public static final BlockMites miteyMud = new BlockMiteyMud();

    public static void init()
    {
        GameRegistry.registerBlock(colony, Names.Blocks.COLONY);
        GameRegistry.registerBlock(miteyMud, Names.Blocks.MITEY_MUD);
    }
}