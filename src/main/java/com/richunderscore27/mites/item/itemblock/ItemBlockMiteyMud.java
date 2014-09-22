package com.richunderscore27.mites.item.itemblock;

import com.richunderscore27.mites.init.ModBlocks;
import com.richunderscore27.mites.utility.FluidRayTrace;
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
        MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(world, player, true);

        FluidRayTrace fluidRayTrace = new FluidRayTrace(itemStack, world, player, Material.water, 0);

        if(fluidRayTrace.rayTrace(position))
        {
            world.setBlock(position.blockX, position.blockY, position.blockZ, ModBlocks.miteyPool);

            if (!player.capabilities.isCreativeMode)
            {
                --itemStack.stackSize;
            }
            return true;
        }

        return false;
    }
}
