package com.richunderscore27.mites.item.itemblock;

import com.richunderscore27.mites.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBlockMiteyMud extends ItemBlockMites
{
    public ItemBlockMiteyMud(Block block)
    {
        super(block);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float sideX, float sideY, float sideZ)
    {
        return createMiteyPool(itemStack, world, player) || super.onItemUse(itemStack, player, world, x, y, z, side, sideX, sideY, sideZ);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        createMiteyPool(itemStack, world, player);
        return itemStack;
    }

    public boolean createMiteyPool(ItemStack itemStack, World world, EntityPlayer player)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

        if (movingobjectposition == null)
        {
            return false;
        }
        else
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!world.canMineBlock(player, i, j, k))
                {
                    return false;
                }

                if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack))
                {
                    return false;
                }

                if (world.getBlock(i, j, k).getMaterial() == Material.water && world.getBlockMetadata(i, j, k) == 0)
                {
                    world.setBlock(i, j, k, ModBlocks.miteyPool);

                    if (!player.capabilities.isCreativeMode)
                    {
                        --itemStack.stackSize;
                    }
                    return true;
                }
            }

            return false;
        }
    }
}
