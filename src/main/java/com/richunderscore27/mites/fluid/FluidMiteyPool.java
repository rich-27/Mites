package com.richunderscore27.mites.fluid;

import com.richunderscore27.mites.reference.Names;
import net.minecraft.item.EnumRarity;

public class FluidMiteyPool extends FluidMites
{
    public FluidMiteyPool()
    {
        super(Names.Fluids.MITEY_POOL);

        this.setLuminosity(11);
        this.setDensity(1500);
        this.setViscosity(10000);
        this.setTemperature(330);
        this.setRarity(EnumRarity.rare);
    }
}
