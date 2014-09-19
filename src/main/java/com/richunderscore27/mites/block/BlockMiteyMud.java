package com.richunderscore27.mites.block;

import com.richunderscore27.mites.reference.Names;
import net.minecraft.block.material.Material;

public class BlockMiteyMud extends BlockMites
{
    public BlockMiteyMud(Material material)
    {
        super(material);
        this.setBlockName(Names.Blocks.MITEY_MUD);
        this.setHardness(0.5F);
        this.setStepSound(soundTypeGravel);
        this.setHarvestLevel("shovel",0);
    }

    public BlockMiteyMud()
    {
        this(Material.ground);
    }
}
