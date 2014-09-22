package com.richunderscore27.mites.tileentity;

import com.richunderscore27.mites.init.ModItems;
import com.richunderscore27.mites.item.ItemWorldMite;
import com.richunderscore27.mites.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;

// TODO: Add updateEntity with random mite spawns if heat source exist within distance
// TODO: Influence mite spawn chances by blocks of a certain type within distance
// TODO: Add upgrade pool functionality by further stuff being added to pool
// TODO: Slot 0 => larvae, slots ... => mites, slots ... => upgrades
// TODO: Tile entity contents affect tile entity production? Ore mites => ore mites etc

public class TileEntityMiteyPool extends TileEntityMites implements ISidedInventory
{
    public ArrayList<ItemStack> inventory;

    public TileEntityMiteyPool()
    {
        inventory = new ArrayList<ItemStack>();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        NBTTagList tagList = nbt.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
        inventory = new ArrayList<ItemStack>();
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            ItemStack itemStack = ItemStack.loadItemStackFromNBT(tagCompound);
            if (itemStack != null)
            {
                inventory.add(itemStack);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
        super.writeToNBT(p_145841_1_);

        NBTTagList tagList = new NBTTagList();
        for (ItemStack itemStack : inventory)
        {
            NBTTagCompound tagCompound = new NBTTagCompound();
            itemStack.writeToNBT(tagCompound);
            tagList.appendTag(tagCompound);
        }
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.size();
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return inventory.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack slotStack = getStackInSlot(slot);
        if(slotStack != null && amount > 0 && slotStack.stackSize > amount)
        {
            slotStack.splitStack(amount);
            return slotStack;
        }
        inventory.remove(slot);
        return slotStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack itemStack = getStackInSlot(slot);
        inventory.remove(slot);
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        inventory.set(slot, itemStack);
        if(itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    // Slots (bar larvae) available from sides and bottom
    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        if (side == 1)
        {
            return new int[0];
        }
        int accSlots[] = new int[inventory.size() - 1];
        for (int i = 0; i < accSlots.length; ++i)
        {
            accSlots[i] = i + 1;
        }
        return accSlots;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side)
    {
        return slot != 0;
    }

    @Override
    public String getInventoryName()
    {
        return Names.BlockFluids.MITEY_POOL;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return true;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return false;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return (slot == 0 && itemStack.getItem() == ModItems.miteyLarvae) || itemStack.getItem() instanceof ItemWorldMite;
    }
}
