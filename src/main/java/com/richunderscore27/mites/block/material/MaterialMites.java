package com.richunderscore27.mites.block.material;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialMites extends Material
{
    public static final Material toughDirt = new MaterialMites(MapColor.dirtColor).setRequiresTool();

    public MaterialMites(MapColor mapColor)
    {
        super(mapColor);
    }
}
