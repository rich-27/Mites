package com.richunderscore27.mites.init;

import com.richunderscore27.mites.item.*;
import com.richunderscore27.mites.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemMites genericMite = new ItemGenericMite();
    public static final ItemMites woodMite = new ItemWoodMite();
    public static final ItemMites leafMite = new ItemLeafMite();
    public static final ItemMites oreMite = new ItemOreMite();

    public static void init()
    {
        GameRegistry.registerItem(genericMite, "genericMite");
        GameRegistry.registerItem(woodMite, "woodMite");
        GameRegistry.registerItem(leafMite, "leafMite");
        GameRegistry.registerItem(oreMite, "oreMite");
    }
}
