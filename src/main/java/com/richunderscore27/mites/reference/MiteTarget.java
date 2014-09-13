package com.richunderscore27.mites.reference;

import com.richunderscore27.mites.utility.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

// TODO: Refactor enum to use UniqueIdentifier not UnlocalizedName

public enum MiteTarget
{
    ANY(new ArrayList<ItemStack>()),

    LEAF(OreDictionary.getOres("treeLeaves")),

    LOG(OreDictionary.getOres("logWood")),

    ORE(getAllOres());

    public final ArrayList<String> validItems;

    private MiteTarget(ArrayList<ItemStack> itemStacks)
    {
        validItems = new ArrayList<String>();
        for(ItemStack itemStack : itemStacks)
        {
            validItems.add(itemStack.getUnlocalizedName());
        }
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
