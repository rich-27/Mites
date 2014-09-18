package com.richunderscore27.mites.block;

import com.richunderscore27.mites.reference.Names;
import net.minecraft.block.material.Material;

public class BlockMiteyMud extends BlockMites
{
    public BlockMiteyMud(Material material)
    {
        super(material);
        this.setBlockName(Names.Blocks.MITEY_MUD);
        this.setHarvestLevel("shovel",0);
        // Set silk touch required - on block break either drop colony w/ silk touch or a few Mites with no silk touch
        // int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, ItemStack);
    }

    public BlockMiteyMud()
    {
        this(Material.ground);
    }
}
