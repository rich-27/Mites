package com.richunderscore27.mites.block;

import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.reference.RenderIds;
import com.richunderscore27.mites.tileentity.TileEntityColony;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockColony extends BlockMites implements ITileEntityProvider
{
    public BlockColony(Material material)
    {
        super(material);
        this.setBlockName(Names.Blocks.COLONY);
        this.setBlockBounds(0.0994601F, 0F, 0.0979286F, 0.9000001F, 2.789F, 0.9182186F);

        this.setHarvestLevel("shovel",0);
        // Set silk touch required - on block break either drop colony w/ silk touch or a few Mites with no silk touch
        // int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, ItemStack);
    }

    public BlockColony()
    {
        this(Material.ground);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityColony();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderIds.colony;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
}
