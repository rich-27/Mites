package com.richunderscore27.mites.init;

import com.richunderscore27.mites.item.*;
import com.richunderscore27.mites.item.itemblock.ItemBlockMiteyMud;
import com.richunderscore27.mites.reference.MiteTarget;
import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ModItems
{
    public static final Item woodMite = new ItemWorldMite().setTargetBlockType(MiteTarget.LOG).setUnlocalizedName("woodMite").setTextureName("woodMite");
    public static final Item leafMite = new ItemWorldMite().setTargetBlockType(MiteTarget.LEAF).setUnlocalizedName("leafMite").setTextureName("leafMite");
    public static final Item oreMite = new ItemWorldMite().setTargetBlockType(MiteTarget.ORE).setUnlocalizedName("oreMite").setTextureName("oreMite");
    public static final Item advancedMite = new ItemWorldMite().setUnlocalizedName("advancedMite").setTextureName("advancedMite");
    public static final Item miteyLarvae = new ItemMiteyLarvae();

    public static void init()
    {
        // TODO: Convert to Names.Items.WOOD_MITE, etc - convert to metadata?
        GameRegistry.registerItem(woodMite, "woodMite");
        GameRegistry.registerItem(leafMite, "leafMite");
        GameRegistry.registerItem(oreMite, "oreMite");
        GameRegistry.registerItem(advancedMite, "advancedMite");
        GameRegistry.registerItem(miteyLarvae, "miteyLarvae");
    }
}
