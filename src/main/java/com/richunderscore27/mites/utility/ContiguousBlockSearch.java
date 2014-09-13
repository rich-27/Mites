package com.richunderscore27.mites.utility;

import com.google.common.annotations.VisibleForTesting;
import com.richunderscore27.mites.handler.ConfigurationHandler;
import com.richunderscore27.mites.reference.MiteTarget;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ContiguousBlockSearch
{
    private final int maxBlocks;
    private World worldObj;
    private final ChunkPosition initSearchPos;
    private ArrayList<String> targetBlocks;

    private HashSet<ChunkPosition> blocksToSearch = new HashSet<ChunkPosition>();
    private HashSet<ChunkPosition> blocksSearched = new HashSet<ChunkPosition>();
    private List<ChunkPosition> iterator = new ArrayList<ChunkPosition>();
    private final List<ChunkPosition> contiguousBlocks = new ArrayList<ChunkPosition>();

    public ContiguousBlockSearch(World world, int x, int y, int z, MiteTarget miteTarget)
    {
        maxBlocks = ConfigurationHandler.maxSearchBlocks;

        worldObj = world;
        initSearchPos = new ChunkPosition(x, y, z);
        targetBlocks = miteTarget.validItems;
    }

    // TODO: Make search run off specific ore dictionary entry for block, not whole miteTarget category

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

                /*
                Block block = worldObj.getBlock(x, y, z);
                String registryName = Block.blockRegistry.getNameForObject(block);

                if (registryName.startsWith("lit_"))
                {
                    block = (Block)Block.blockRegistry.getObject(registryName.substring(4));
                }
                */


                Block block = worldObj.getBlock(x, y, z);
                GameRegistry.UniqueIdentifier uniqueId = GameRegistry.findUniqueIdentifierFor(block);

                if(uniqueId.name.startsWith("lit_"))
                {
                    block = GameRegistry.findBlock(uniqueId.modId, uniqueId.name.substring(4));
                }


                /*
                block.getPickBlock()

                blockRegistry.addObject(
                        73,
                        "redstone_ore",
                        (new BlockRedstoneOre(false))
                                .setHardness(3.0F)
                                .setResistance(5.0F)
                                .setStepSound(soundTypePiston)
                                .setBlockName("oreRedstone")
                                .setCreativeTab(CreativeTabs.tabBlock)
                                .setBlockTextureName("redstone_ore")
                );
                blockRegistry.addObject(
                        74,
                        "lit_redstone_ore",
                        (new BlockRedstoneOre(true))
                                .setLightLevel(0.625F)
                                .setHardness(3.0F)
                                .setResistance(5.0F)
                                .setStepSound(soundTypePiston)
                                .setBlockName("oreRedstone")
                                .setBlockTextureName("redstone_ore")
                );
                */

                if (block == Blocks.air || !targetBlocks.contains(new ItemStack(block).getUnlocalizedName()))
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
