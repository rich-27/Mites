package com.richunderscore27.mites.utility;

import com.richunderscore27.mites.handler.ConfigurationHandler;
import com.richunderscore27.mites.reference.MiteTarget;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ContiguousBlockSearch
{
    private final int maxBlocks;
    private World worldObj;
    private final ChunkPosition initSearchPos;
    private HashMap<GameRegistry.UniqueIdentifier, String> oreLookup;
    private String targetOre;

    private HashSet<ChunkPosition> blocksToSearch = new HashSet<ChunkPosition>();
    private HashSet<ChunkPosition> blocksSearched = new HashSet<ChunkPosition>();
    private List<ChunkPosition> iterator = new ArrayList<ChunkPosition>();
    private final List<ChunkPosition> contiguousBlocks = new ArrayList<ChunkPosition>();

    public ContiguousBlockSearch(World world, int x, int y, int z, MiteTarget miteTarget, GameRegistry.UniqueIdentifier uniqueIdentifier)
    {
        maxBlocks = ConfigurationHandler.maxSearchBlocks;

        worldObj = world;
        initSearchPos = new ChunkPosition(x, y, z);
        oreLookup = miteTarget.validItems;
        targetOre = oreLookup.get(uniqueIdentifier) == null ? uniqueIdentifier.name : oreLookup.get(uniqueIdentifier);
    }

    // TODO: Make leaf search diagonal

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
                GameRegistry.UniqueIdentifier uniqueId = GameRegistry.findUniqueIdentifierFor(block);
                uniqueId = uniqueId.name.startsWith("lit_") ? new GameRegistry.UniqueIdentifier(uniqueId.modId + ":" + uniqueId.name.substring(4)) : uniqueId;

                if (block == Blocks.air || !targetOre.equals(oreLookup.get(uniqueId) == null ? uniqueId.name : oreLookup.get(uniqueId)))
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
