package com.richunderscore27.mites.reference;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;

public class Materials
{
    public static final Material toughDirt = (new Material(MapColor.dirtColor)
    {
        @Override
        public boolean isToolNotRequired()
        {
            return false;
        }
    });

    public static final Material solidWater = (new MaterialLiquid(MapColor.waterColor)
    {
        @Override
        public int getMaterialMobility()
        {
            return 2;
        }
    });
}
