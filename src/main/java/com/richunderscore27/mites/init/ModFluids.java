package com.richunderscore27.mites.init;

import com.richunderscore27.mites.fluid.FluidEnrichedWater;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids
{
    public static final Fluid enrichedWater = new FluidEnrichedWater();

    public static void init()
    {
        FluidRegistry.registerFluid(enrichedWater);
    }
}
