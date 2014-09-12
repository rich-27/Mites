package com.richunderscore27.mites.creativetab;

import com.richunderscore27.mites.init.ModItems;
import com.richunderscore27.mites.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMites
{
    public static final CreativeTabs MITES_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.genericMite;
        }
    };
}