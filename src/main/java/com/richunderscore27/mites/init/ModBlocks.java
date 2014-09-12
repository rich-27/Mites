package com.richunderscore27.mites.init;

import com.richunderscore27.mites.block.BlockColony;
import com.richunderscore27.mites.block.BlockMites;
import com.richunderscore27.mites.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockMites colony = new BlockColony();

    public static void init()
    {
        GameRegistry.registerBlock(colony, "colony");
    }
}