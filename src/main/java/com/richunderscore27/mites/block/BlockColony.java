package com.richunderscore27.mites.block;

import net.minecraft.block.material.Material;

public class BlockColony extends BlockMites
{
    public BlockColony(Material material)
    {
        super(material);
        this.setBlockName("colony");
        this.setBlockTextureName("colony");
        this.setHarvestLevel("shovel",0);
        // Set silk touch required - on block break either drop colony w/ silk touch or a few Mites with no silk touch
        // int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, ItemStack);
    }

    public BlockColony()
    {
        this(Material.ground);
    }
}
