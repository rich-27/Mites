package com.richunderscore27.mites.item;

import com.richunderscore27.mites.utility.ContiguousBlockSearch;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ItemGenericMite extends ItemMites
{
    public ItemGenericMite()
    {
        super();
        this.setUnlocalizedName("genericMite");
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float posX, float posY, float posZ)
    {
        Block block = world.getBlock(x, y, z);
        int metadata = world.getBlockMetadata(x, y, z);

        if (player.canPlayerEdit(x, y, z, side, itemStack)) // && block == Blocks.end_portal_frame && !BlockEndPortalFrame.isEnderEyeInserted(i1)
        {
            if (world.isRemote)
            {
                return true;
            }
            else
            {
                ContiguousBlockSearch contiguousBlockSearch = new ContiguousBlockSearch(world, x, y, z, block, metadata);
                List<ChunkPosition> contiguousBlocks = contiguousBlockSearch.searchContiguous();

                if(contiguousBlocks == null)
                {
                    return false;
                }

                --itemStack.stackSize;
                consumeVein(contiguousBlocks);
                
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public void consumeVein(List<ChunkPosition> contiguousBlocks)
    {
        /* Spawn smoke around block clicked
        for (j1 = 0; j1 < 16; ++j1)
        {
            double d0 = (double)((float)x + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
            double d1 = (double)((float)y + 0.8125F);
            double d2 = (double)((float)z + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            world.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
        }

        Furnace:
        p_149734_1_.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
        p_149734_1_.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);

        Redstone:
        spawnParticle("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
        */
    }
}
