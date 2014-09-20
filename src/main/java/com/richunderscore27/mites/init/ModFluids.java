package com.richunderscore27.mites.init;

import com.richunderscore27.mites.fluid.FluidMites;
import com.richunderscore27.mites.fluid.FluidMiteyPool;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids
{
    public static final FluidMites miteyPool = new FluidMiteyPool();

    public static void init()
    {
        FluidRegistry.registerFluid(miteyPool);
    }
}
