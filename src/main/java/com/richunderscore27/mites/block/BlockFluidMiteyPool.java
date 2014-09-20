package com.richunderscore27.mites.block;

import com.richunderscore27.mites.reference.Materials;
import com.richunderscore27.mites.init.ModFluids;
import com.richunderscore27.mites.reference.Names;

public class BlockFluidMiteyPool extends BlockFluidMites
{

    public BlockFluidMiteyPool()
    {
        super(ModFluids.miteyPool, Materials.solidWater);
        this.setBlockName(Names.BlockFluids.MITEY_POOL);
    }
}
