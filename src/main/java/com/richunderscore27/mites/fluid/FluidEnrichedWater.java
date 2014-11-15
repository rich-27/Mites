package com.richunderscore27.mites.fluid;

import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.reference.Reference;
import net.minecraft.item.EnumRarity;
import net.minecraftforge.fluids.Fluid;

public class FluidEnrichedWater extends Fluid
{

    public FluidEnrichedWater(String fluidName)
    {
        super(fluidName);
    }

    public FluidEnrichedWater()
    {
        this(Names.Fluids.ENRICHED_WATER);

        this.setLuminosity(11);
        this.setDensity(1500);
        this.setViscosity(10000);
        this.setTemperature(330);
        this.setRarity(EnumRarity.rare);
    }

    @Override
    public String getUnlocalizedName()
    {
        return "fluid." + Reference.MOD_ID.toLowerCase() + ":" + super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1);
    }
}