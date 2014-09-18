package com.richunderscore27.mites.block.material;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialMites extends Material
{
    public static final Material toughDirt = new MaterialMites(MapColor.dirtColor);

    public MaterialMites(MapColor p_i2116_1_)
    {
        super(p_i2116_1_);
        this.setRequiresTool();
    }
}
