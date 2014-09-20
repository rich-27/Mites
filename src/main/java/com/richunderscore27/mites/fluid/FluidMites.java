package com.richunderscore27.mites.fluid;

import com.richunderscore27.mites.reference.Reference;
import net.minecraftforge.fluids.Fluid;

public class FluidMites extends Fluid
{

    public FluidMites(String fluidName)
    {
        super(fluidName);
    }

    @Override
    public String getUnlocalizedName()
    {
        return "fluid." + Reference.MOD_ID.toLowerCase() + ":" + super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1);
    }
}
