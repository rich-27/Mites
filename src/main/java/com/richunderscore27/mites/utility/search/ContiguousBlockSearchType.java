package com.richunderscore27.mites.utility.search;

import com.richunderscore27.mites.handler.ConfigurationHandler;
import com.richunderscore27.mites.reference.MiteTarget;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ContiguousBlockSearchType
{
    private final int maxBlocks;
    private World worldObj;
    private final ChunkPosition initSearchPos;
    private MiteTarget targetBlockType;

    private HashSet<ChunkPosition> blocksToSearch = new HashSet<ChunkPosition>();
    private HashSet<ChunkPosition> blocksSearched = new HashSet<ChunkPosition>();
    private List<ChunkPosition> iterator = new ArrayList<ChunkPosition>();
    private final List<ChunkPosition> contiguousBlocks = new ArrayList<ChunkPosition>();

    public ContiguousBlockSearchType(World world, int x, int y, int z, MiteTarget miteTarget)
    {
        maxBlocks = ConfigurationHandler.maxSearchBlocks;

        worldObj = world;
        initSearchPos = new ChunkPosition(x, y, z);
        targetBlockType = miteTarget;
    }

    public List<ChunkPosition> searchContiguous()
    {
        blocksToSearch.add(initSearchPos);
        while (!blocksToSearch.isEmpty())
        {
            iterator.addAll(blocksToSearch);
            for (ChunkPosition pos : iterator)
            {
                blocksToSearch.remove(pos);

                if (blocksSearched.contains(pos))
                {
                    continue;
                }

                if (contiguousBlocks.size() > maxBlocks)
                {
                    return null;
                }

                blocksSearched.add(pos);

                int x = pos.chunkPosX;
                int y = pos.chunkPosY;
                int z = pos.chunkPosZ;

                Block block = worldObj.getBlock(x, y, z);
                int blockMeta = worldObj.getBlockMetadata(x, y, z);

                if (!targetBlockType.validItems.contains(new ItemStack(block)))
                {
                    continue;
                }

                contiguousBlocks.add(pos);

                for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
                {
                    blocksToSearch.add(new ChunkPosition(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ));
                }
            }
        }
        return contiguousBlocks;
    }
}
