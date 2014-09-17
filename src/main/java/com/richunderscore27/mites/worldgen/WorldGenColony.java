package com.richunderscore27.mites.worldgen;

import com.richunderscore27.mites.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenColony extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        for (int xCoord = x - 4; xCoord <= x + 4; ++xCoord)
        {
            for (int zCoord = z - 4; zCoord <= z + 4; ++zCoord)
            {
                if (world.getBlock(xCoord, y, zCoord) != Blocks.grass)
                {
                    return false;
                }
                for (int yCoord = 1; yCoord < 5; ++yCoord)
                {
                    if (!world.getBlock(xCoord, y + yCoord, zCoord).isReplaceable(world, xCoord, yCoord, zCoord))
                    {
                        return false;
                    }
                }
            }
        }

        buildColony(world, random, x, y, z);
        return true;
    }

    private void buildColony(World world, Random random, int x, int y, int z)
    {
        for (int xCoord = x - 4; xCoord <= x + 4; ++xCoord)
        {
            for (int zCoord = z - 4; zCoord <= z + 4; ++zCoord)
            {
                float dist = MathHelper.sqrt_float((float) ((x - xCoord) * (x - xCoord) + (z - zCoord) * (z - zCoord)));

                if (MathHelper.floor_float(dist) == 0)
                {
                    for (int yCoord = y + 3; yCoord >= y; --yCoord)
                    {
                        world.setBlock(xCoord, yCoord, zCoord, ModBlocks.colony);
                    }
                }
                else if ((dist < 3.2) || (dist < 5 && random.nextFloat() < 0.5) || (dist == 5 && random.nextFloat() < 0.2))
                {
                    if (world.getBlock(xCoord, y + 1, zCoord) != Blocks.air)
                    {
                        world.setBlock(xCoord, y + 1, zCoord, Blocks.air);
                    }
                    world.setBlock(xCoord, y, zCoord, ModBlocks.colony);
                }
            }
        }
    }
}
