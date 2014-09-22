package com.richunderscore27.mites.utility;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class FluidRayTrace
{
    ItemStack itemStack;
    World world;
    EntityPlayer player;
    Material targetMaterial;
    Block targetBlock;
    int metaData;
    boolean ignoreMeta = true;

    public FluidRayTrace(ItemStack itemStack, World world, EntityPlayer player, Material material)
    {
        this.itemStack = itemStack;
        this.world = world;
        this.player = player;
        this.targetMaterial = material;
    }

    public FluidRayTrace(ItemStack itemStack, World world, EntityPlayer player, Material material, int metaData)
    {
        this(itemStack, world, player, material);
        this.metaData = metaData;
        this.ignoreMeta = false;
    }

    public FluidRayTrace(ItemStack itemStack, World world, EntityPlayer player, Block block)
    {
        this.itemStack = itemStack;
        this.world = world;
        this.player = player;
        this.targetBlock = block;
    }

    public FluidRayTrace(ItemStack itemStack, World world, EntityPlayer player, Block block, int metaData)
    {
        this(itemStack, world, player, block);
        this.metaData = metaData;
        this.ignoreMeta = false;
    }

    public boolean rayTrace(MovingObjectPosition position)
    {
        if (position == null)
        {
            return false;
        }
        else
        {
            if (position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int i = position.blockX;
                int j = position.blockY;
                int k = position.blockZ;
                Block block = world.getBlock(i, j, k);

                if (!world.canMineBlock(player, i, j, k))
                {
                    return false;
                }

                if (!player.canPlayerEdit(i, j, k, position.sideHit, itemStack))
                {
                    return false;
                }

                if ((block == targetBlock || block.getMaterial() == targetMaterial) && (ignoreMeta || world.getBlockMetadata(i, j, k) == metaData))
                {
                    return true;
                }
            }

            return false;
        }
    }
}
