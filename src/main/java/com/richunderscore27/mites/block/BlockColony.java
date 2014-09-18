package com.richunderscore27.mites.block;

import com.richunderscore27.mites.block.material.MaterialMites;
import com.richunderscore27.mites.init.ModItems;
import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.reference.RenderIds;
import com.richunderscore27.mites.tileentity.TileEntityColony;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

// TODO: fix raytrace, getblock, setblock, etc. Placeholder block for y+1, y+2?

// TODO: Implement drops - silktouch, larvae, infomite

public class BlockColony extends BlockMites implements ITileEntityProvider
{
    public BlockColony(Material material)
    {
        super(material);
        this.setBlockName(Names.Blocks.COLONY);
        this.setBlockBounds(0.0994601F, 0F, 0.0979286F, 0.9000001F, 2.789F, 0.9182186F);

        this.setHarvestLevel("shovel", 2);
        // Set silk touch required - on block break either drop colony w/ silk touch or a few Mites with no silk touch
        // int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, ItemStack);
    }

    public BlockColony()
    {
        this(MaterialMites.toughDirt);
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

    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return ModItems.miteyLarvae;
    }
}
