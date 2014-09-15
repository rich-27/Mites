package com.richunderscore27.mites.reference;

import com.richunderscore27.mites.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;

public enum MiteTarget
{
    ANY(""),

    LEAF("treeLeaves"),

    LOG("logWood"),

    ORE("ore");

    public final HashMap<GameRegistry.UniqueIdentifier, String> validItems;

    private MiteTarget(String string)
    {
        validItems = new HashMap<GameRegistry.UniqueIdentifier, String>();
        for (String oreName : OreDictionary.getOreNames())
        {
            if (oreName.contains(string))
            {
                ArrayList<ItemStack> itemStacks = OreDictionary.getOres(oreName);
                for(ItemStack itemStack : itemStacks)
                {
                    LogHelper.info("Key: " + GameRegistry.findUniqueIdentifierFor(itemStack.getItem()));
                    LogHelper.info("Value: " + oreName);
                    validItems.put(GameRegistry.findUniqueIdentifierFor(itemStack.getItem()), oreName);
                }
            }
        }
    }
}