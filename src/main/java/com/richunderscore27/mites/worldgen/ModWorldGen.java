package com.richunderscore27.mites.worldgen;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class ModWorldGen implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.dimensionId)
        {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateEnd(World world, Random random, int x, int z)
    {

    }

    private void generateSurface(World world, Random random, int x, int z)
    {
        if (random.nextFloat() < 0.2)
        {
            int posX = x + random.nextInt(16);
            int posZ = z + random.nextInt(16);
            int posY = world.getHeightValue(posX, posZ) - 1;
            new WorldGenColony().generate(world, random, posX, posY, posZ);
        }
    }

    private void generateNether(World world, Random random, int x, int z)
    {

    }
}
