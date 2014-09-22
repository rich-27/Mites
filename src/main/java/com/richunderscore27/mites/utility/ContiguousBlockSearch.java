package com.richunderscore27.mites.utility;

import com.richunderscore27.mites.reference.MiteTarget;
import com.richunderscore27.mites.reference.Settings;
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
    private World world;
    private final ChunkPosition initialSearchPosition;
    private HashMap<GameRegistry.UniqueIdentifier, String> oreLookup;
    private String targetOre;
    private int contiguity;

    private HashSet<ChunkPosition> blocksToSearch = new HashSet<ChunkPosition>();
    private HashSet<ChunkPosition> blocksSearched = new HashSet<ChunkPosition>();
    private List<ChunkPosition> iterator = new ArrayList<ChunkPosition>();
    private final List<ChunkPosition> contiguousBlocks = new ArrayList<ChunkPosition>();

    /**
     * Initializes ContiguousBlockSearch object.
     *
     * Contiguity:
     *      0: Face touching (uses block hard limit)
     *      1: Edge touching (uses radial limit) - not implemented
     *      2: Vertex touching (uses radial limit) - not implemented
     *
     * @param world The current world
     * @param x X Position
     * @param y Y position
     * @param z Z position
     * @param miteTarget Enum containing OreDictionary lookup of search target
     * @param uniqueIdentifier UniqueIdentifier of the target block
     * @param contiguity int to determine required contiguity
     */
    public ContiguousBlockSearch(World world, int x, int y, int z, MiteTarget miteTarget, GameRegistry.UniqueIdentifier uniqueIdentifier, int contiguity)
    {
        this.maxBlocks = Settings.Search.maxSearchBlocks;

        this.world = world;
        this.initialSearchPosition = new ChunkPosition(x, y, z);
        this.oreLookup = miteTarget.validItems;
        this.targetOre = oreLookup.get(uniqueIdentifier) == null ? uniqueIdentifier.name : oreLookup.get(uniqueIdentifier);
        this.contiguity = contiguity;
    }

    public ContiguousBlockSearch(World world, int x, int y, int z, MiteTarget miteTarget, GameRegistry.UniqueIdentifier uniqueIdentifier)
    {
        this(world, x, y, z, miteTarget, uniqueIdentifier, 0);
    }

    public List<ChunkPosition> searchContiguous()
    {
        blocksToSearch.add(initialSearchPosition);
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

                if (contiguity == 0 && contiguousBlocks.size() > maxBlocks)
                {
                    return null;
                }

                blocksSearched.add(pos);

                int x = pos.chunkPosX;
                int y = pos.chunkPosY;
                int z = pos.chunkPosZ;


                Block block = world.getBlock(x, y, z);
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
