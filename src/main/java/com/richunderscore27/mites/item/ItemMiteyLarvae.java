package com.richunderscore27.mites.item;

import com.richunderscore27.mites.init.ModBlocks;
import com.richunderscore27.mites.init.ModItems;
import com.richunderscore27.mites.reference.Materials;
import com.richunderscore27.mites.tileentity.TileEntityMiteyPool;
import com.richunderscore27.mites.utility.FluidRayTrace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemMiteyLarvae extends ItemMites
{
    public ItemMiteyLarvae()
    {
        super();
        this.setUnlocalizedName("miteyLarvae");
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(world, player, true);

        FluidRayTrace fluidRayTrace = new FluidRayTrace(itemStack, world, player, ModBlocks.enrichedWater, 0);

        if(fluidRayTrace.rayTrace(position))
        {
            world.setBlock(position.blockX, position.blockY, position.blockZ, ModBlocks.miteyPool);
            TileEntityMiteyPool miteyPool = (TileEntityMiteyPool) world.getTileEntity(position.blockX, position.blockY, position.blockZ);

            miteyPool.setInventorySlotContents(0, new ItemStack(ModItems.miteyLarvae));
            /* TODO: fix
                Caused by: java.lang.NullPointerException
	                at com.richunderscore27.mites.item.ItemMiteyLarvae.onItemRightClick(ItemMiteyLarvae.java:38) ~[ItemMiteyLarvae.class:?]
             */

            if (!player.capabilities.isCreativeMode)
            {
                --itemStack.stackSize;
            }
        }
        return itemStack;
    }
}
