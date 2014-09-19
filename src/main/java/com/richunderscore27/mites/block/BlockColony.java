package com.richunderscore27.mites.block;

import com.richunderscore27.mites.block.material.MaterialMites;
import com.richunderscore27.mites.init.ModItems;
import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.reference.RenderIds;
import com.richunderscore27.mites.tileentity.TileEntityColony;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/*
    TODO: fix raytrace, getblock, setblock, etc. Placeholder block for y+1, y+2?
    judos: hello there, i have a question: how do i get the break animation (black pixels) onto my tileentity block?
    Ordinastie: it's complicated
    Ordinastie: you need to reflectively get the damaged blocks list and the icons and test if the pos of you TE is in that list
    rich_27: On that note, can I override raytracing and stuff so the two blocks above my tile entity act as if they contain the block also? Or would that be the job of a multiblock?
    Ordinastie: place fake blocks
    judos: Ordinastie, ok reflection on that stuff is no problem, but do you know where is the list of the damaged blocks?
    Ordinastie: judos, https://github.com/Ordinastie/MalisisCore/blob/master/source/net/malisis/core/renderer/BaseRenderer.java#L1008
 */

// TODO: Implement drops - silktouch, larvae, infomite

public class BlockColony extends BlockMites implements ITileEntityProvider
{
    public BlockColony(Material material)
    {
        super(material);
        this.setBlockName(Names.Blocks.COLONY);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundTypeGravel);
        this.setHarvestLevel("shovel", 2);

        this.setBlockBounds(0.0994601F, 0F, 0.0979286F, 0.9000001F, 2.789F, 0.9182186F);
    }

    public BlockColony()
    {
        this(MaterialMites.toughDirt);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityColony();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderIds.colony;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return ModItems.miteyLarvae;
    }
}
