package com.richunderscore27.mites.tileentity;

import com.richunderscore27.mites.init.ModItems;
import com.richunderscore27.mites.item.ItemWorldMite;
import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.ChunkPosition;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;

// TODO: Add updateEntity with random mite spawns if heat source exist within distance
// TODO: Influence mite spawn chances by blocks of a certain type within distance
// TODO: Add upgrade pool functionality by further stuff being added to pool
// TODO: Slot 0 => larvae, slots ... => mites, slots ... => upgrades
// TODO: Tile entity contents affect tile entity production? Ore mites => ore mites etc

/*
    rich_27: How do I make a config option of a list of blocks with attached values?

    digseraph|afk: rich_27, I might be wrong, but i think the easiest would be
    to use a list of strings, where each string is an easily parsed combination
    of the block and value, something like "block.blockname=42"
 */

public class TileEntityMiteyPool extends TileEntityMites implements ISidedInventory
{
    public ArrayList<ItemStack> inventory;
    public HashMap<ChunkPosition, GameRegistry.UniqueIdentifier> heatSources;
    public HashMap<GameRegistry.UniqueIdentifier, Integer> warmBlocks = new HashMap<GameRegistry.UniqueIdentifier, Integer>();
    public double heatLevel = 0;

    public TileEntityMiteyPool()
    {
        this.inventory = new ArrayList<ItemStack>();
        warmBlocks.put(GameRegistry.findUniqueIdentifierFor(Blocks.lava), 200);
        warmBlocks.put(GameRegistry.findUniqueIdentifierFor(Blocks.lit_furnace), 150);
        //this.warmBlocks = Settings.WarmBlocks.warmBlocks; TODO: make warmBlocks work from config
    }

    public double heatSourceCheck(){
        double heat = 0.0;
        for(int xIter = -3; xIter <= 3; ++xIter)
        {
            int x = this.xCoord + xIter;
            for(int zIter = -3; zIter <= 3; ++zIter)
            {
                int z = this.zCoord + zIter;
                for(int yIter = -3; yIter <= 3; ++yIter)
                {
                    int y = this.yCoord + yIter;

                    GameRegistry.UniqueIdentifier blockUID = GameRegistry.findUniqueIdentifierFor(this.worldObj.getBlock(x, y, z));
                    if(warmBlocks.containsKey(blockUID))
                    {
                        double dist = Math.sqrt((xCoord-x)*(xCoord-x) + (yCoord-y)*(yCoord-y) + (zCoord-z)*(zCoord-z));
                        heat += warmBlocks.get(blockUID)/Math.sqrt(dist);
                    }
                }
            }
        }
        return heat;
    }

    @Override
    public void updateEntity(){
        double heat = heatSourceCheck();
        if (this.heatLevel != heat){
            LogHelper.info("Heat Level: " + heat + ", Old Heat: " + this.heatLevel);
            this.heatLevel = heat;
        }

        super.updateEntity();
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
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

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
