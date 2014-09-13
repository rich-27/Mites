package com.richunderscore27.mites.reference;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public enum MiteTarget
{
    ANY(new ArrayList<ItemStack>()),

    LEAF(OreDictionary.getOres("treeLeaves")),

    LOG(OreDictionary.getOres("logWood")),

    ORE(getAllOres());

    public final ArrayList<ItemStack> validItems;

    private MiteTarget(ArrayList<ItemStack> itemStacks)
    {
        validItems = itemStacks;
    }

    private static ArrayList<ItemStack> getAllOres()
    {
        ArrayList<ItemStack> allOres = new ArrayList<ItemStack>();
        for(String oreName : OreDictionary.getOreNames())
        {
            if (oreName.startsWith("ore"))
            {
                allOres.addAll(OreDictionary.getOres(oreName));
            }
        }
        return allOres;
    }
}
