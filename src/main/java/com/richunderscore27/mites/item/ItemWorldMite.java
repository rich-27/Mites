package com.richunderscore27.mites.item;

import com.richunderscore27.mites.reference.MiteTarget;
import com.richunderscore27.mites.utility.ContiguousBlockSearch;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import java.util.List;

public class ItemWorldMite extends ItemMites
{
    private MiteTarget targetBlockType = MiteTarget.ANY;

    public ItemWorldMite()
    {
        super();
        this.setUnlocalizedName("worldMite");
    }

    public Item setTargetBlockType(MiteTarget miteTarget)
    {
        this.targetBlockType = miteTarget;
        return this;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float posX, float posY, float posZ)
    {
        GameRegistry.UniqueIdentifier targetBlock = GameRegistry.findUniqueIdentifierFor(world.getBlock(x, y, z));
        targetBlock = targetBlock.name.startsWith("lit_") ? new GameRegistry.UniqueIdentifier(targetBlock.modId + ":" + targetBlock.name.substring(4)) : targetBlock;
        boolean isCreative = player.capabilities.isCreativeMode;

        if (player.canPlayerEdit(x, y, z, side, itemStack) && (targetBlockType.validItems.containsKey(targetBlock) || this.targetBlockType == MiteTarget.ANY))
        {
            if (world.isRemote)
            {
                return true;
            }
            else
            {
                ContiguousBlockSearch contiguousBlockSearch = new ContiguousBlockSearch(world, x, y, z, targetBlockType, targetBlock);
                List<ChunkPosition> contiguousBlocks = contiguousBlockSearch.searchContiguous();

                if(contiguousBlocks == null)
                {
                    return false;
                }

                if (!isCreative)
                {
                    --itemStack.stackSize;
                }
                consumeBlocks(world, contiguousBlocks, !isCreative);
                
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public void consumeBlocks(World world, List<ChunkPosition> contiguousBlocks, boolean dropItems)
    {
        for(ChunkPosition pos : contiguousBlocks)
        {
            world.func_147480_a(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, dropItems);
        }




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
