package com.richunderscore27.mites.block;

import com.richunderscore27.mites.reference.Materials;
import com.richunderscore27.mites.init.ModFluids;
import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.tileentity.TileEntityMiteyPool;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Random;

// TODO: Implement 'fake' fluid for static block with tile entity, switches to this when flowing conditions met, dropping inventory.

public class BlockFluidMiteyPool extends BlockFluidMites
{
    public BlockFluidMiteyPool()
    {
        super(ModFluids.miteyPool, Materials.solidWater);
        this.setBlockName(Names.BlockFluids.MITEY_POOL);
    }

    /*
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return (metadata & 2) != 0;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return (metadata & 2) == 0 ? new TileEntityMiteyPool() : null;
    }
    */

    /*
    public void breakBlock(World world, int x, int y, int z, Block block, int metaData)
    {
        if ((metaData & 2) != 0)
        {
            dropInventory(world, x, y, z);
            world.removeTileEntity(x, y, z);
        }
        super.breakBlock(world, x, y, z, block, metaData);
    }
    */

    /*
    public boolean onBlockEventReceived(World world, int x, int y, int z, int eventNo, int eventArg)
    {
        super.onBlockEventReceived(world, x, y, z, eventNo, eventArg);

        if ((world.getBlockMetadata(x, y, z) & 2) != 0)
        {
            TileEntity tileentity = world.getTileEntity(x, y, z);
            return tileentity != null && tileentity.receiveClientEvent(eventNo, eventArg);
        }

        return false;
    }
    */

    /*
    protected void dropInventory(World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if(!(tileEntity instanceof IInventory)) return;

        IInventory inventory = (IInventory)tileEntity;
        Random rand = new Random();

        for(int i = 0; i < inventory.getSizeInventory(); ++i)
        {
            ItemStack itemStack = inventory.getStackInSlot(i);

            if(itemStack != null && itemStack.stackSize > 0)
            {
                EntityItem entityItem = new EntityItem(
                        world, x + 0.1F + 0.8F * rand.nextFloat(),
                        y + 0.1F + 0.8F * rand.nextFloat(),
                        z + 0.1F + 0.8F * rand.nextFloat(),
                        new ItemStack(itemStack.getItem(), itemStack.stackSize, itemStack.getItemDamage())
                );

                if(itemStack.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
                }

                entityItem.motionX = rand.nextGaussian() * 0.05F;
                entityItem.motionY = rand.nextGaussian() * 0.05F + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * 0.05F;

                world.spawnEntityInWorld(entityItem);

                itemStack.stackSize = 0;
            }
        }
    }
    */
}