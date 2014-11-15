package com.richunderscore27.mites.block;

import com.richunderscore27.mites.creativetab.CreativeTabMites;
import com.richunderscore27.mites.init.ModBlocks;
import com.richunderscore27.mites.init.ModFluids;
import com.richunderscore27.mites.reference.Materials;
import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.reference.Reference;
import com.richunderscore27.mites.tileentity.TileEntityMiteyPool;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO: Fix rendering
//TODO: Add functionality

public class BlockMiteyPool extends BlockContainer implements IFluidBlock
{
    private IIcon[] blockIcons;

    protected int density = 1;
    protected int densityDir = -1;
    protected int temperature = 295;

    protected int renderPass = 1;
    protected int maxScaledLight = 0;

    protected final String fluidName;
    protected FluidStack stack;

    protected List<Block> flowBlockers = new ArrayList<Block>();

    public BlockMiteyPool(Fluid fluid, Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTabMites.MITES_TAB);
        this.setHardness(100.0F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setTickRandomly(true);
        this.disableStats();
        this.setBlockName(Names.Blocks.MITEY_POOL);
        this.fluidName = fluid.getName();
        stack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);

        this.density = fluid.getDensity();
        this.temperature = fluid.getTemperature();
        this.maxScaledLight = fluid.getLuminosity();
        this.densityDir = fluid.getDensity() > 0 ? -1 : 1;

        flowBlockers.add(Blocks.wooden_door);
        flowBlockers.add(Blocks.iron_door);
        flowBlockers.add(Blocks.standing_sign);
        flowBlockers.add(Blocks.wall_sign);
        flowBlockers.add(Blocks.reeds);
    }

    public BlockMiteyPool()
    {
        this(ModFluids.enrichedWater, Materials.solidWater);
    }

    @Override
    public String getUnlocalizedName()
    {
        return "tile." + Reference.MOD_ID.toLowerCase() + ":" + super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        String iconString = this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1);
        this.blockIcons = new IIcon[] {iconRegister.registerIcon(iconString), iconRegister.registerIcon(iconString + "_flow")};
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int meta, int side)
    {
        return meta != 0 && meta != 1 ? this.blockIcons[1] : this.blockIcons[0];
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityMiteyPool();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metaData)
    {
        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, metaData);
    }

    protected void dropInventory(World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory)) return;

        IInventory inventory = (IInventory) tileEntity;
        Random rand = new Random();

        for (int i = 0; i < inventory.getSizeInventory(); ++i)
        {
            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0)
            {
                EntityItem entityItem = new EntityItem(
                        world, x + 0.1F + 0.8F * rand.nextFloat(),
                        y + 0.1F + 0.8F * rand.nextFloat(),
                        z + 0.1F + 0.8F * rand.nextFloat(),
                        new ItemStack(itemStack.getItem(), itemStack.stackSize, itemStack.getItemDamage())
                );

                if (itemStack.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                entityItem.motionX = rand.nextGaussian() * 0.05F;
                entityItem.motionY = rand.nextGaussian() * 0.05F + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * 0.05F;

                world.spawnEntityInWorld(entityItem);

                itemStack.stackSize = 0;
            }
        }
    }

    /* Fluid Stuff Start */

    /**
     * Returns whether this block is collideable based on the arguments passed in n@param par1 block metaData n@param
     * par2 whether the player right-clicked while holding a boat
     */
    @Override
    public boolean canCollideCheck(int meta, boolean fullHit)
    {
        return fullHit;
    }

    public void replaceWithFluid(World world, int x, int y, int z)
    {
        dropInventory(world, x, y, z);
        world.removeTileEntity(x, y, z);
        world.setBlock(x, y, z, ModBlocks.enrichedWater, 0, 3);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        // Flow vertically if possible
        if (canFlow(world, x, y, z))
        {
            replaceWithFluid(world, x, y, z);
            // return;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if (canFlow(world, x, y, z))
        {
            replaceWithFluid(world, x, y, z);
        }
    }

    // Used to prevent updates on chunk generation
    @Override
    public boolean func_149698_L()
    {
        return false;
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return null;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (maxScaledLight == 0)
        {
            return super.getLightValue(world, x, y, z);
        }
        int data = 8 - world.getBlockMetadata(x, y, z) - 1;
        return (int) (data / 8.0f * maxScaledLight);
    }

    public int getMaxRenderHeightMeta()
    {
        return 0;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType()
    {
        return FluidRegistry.renderIdFluid;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * How bright to render this block based on the light its receiving. Args: iBlockAccess, x, y, z
     */
    @Override
    public int getMixedBrightnessForBlock(IBlockAccess world, int x, int y, int z)
    {
        int lightThis = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
        int lightUp = world.getLightBrightnessForSkyBlocks(x, y + 1, z, 0);
        int lightThisBase = lightThis & 255;
        int lightUpBase = lightUp & 255;
        int lightThisExt = lightThis >> 16 & 255;
        int lightUpExt = lightUp >> 16 & 255;
        return (lightThisBase > lightUpBase ? lightThisBase : lightUpBase) |
                ((lightThisExt > lightUpExt ? lightThisExt : lightUpExt) << 16);
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    @Override
    public int getRenderBlockPass()
    {
        return renderPass;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        Block block = world.getBlock(x, y, z);
        if (block != this)
        {
            return !block.isOpaqueCube();
        }
        return block.getMaterial() != this.getMaterial() && super.shouldSideBeRendered(world, x, y, z, side);
    }

    /* FLUID FUNCTIONS */
    @SuppressWarnings("static-access")
    public int getDensity(IBlockAccess world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        if (!(block instanceof BlockFluidBase))
        {
            return Integer.MAX_VALUE;
        }
        return ((BlockFluidBase) block).getDensity(world, x, y, z);
    }

    @SuppressWarnings("static-access")
    public int getTemperature(IBlockAccess world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        if (!(block instanceof BlockFluidBase))
        {
            return Integer.MAX_VALUE;
        }
        return ((BlockFluidBase) block).getTemperature(world, x, y, z);
    }

    protected boolean canFlow(World world, int x, int y, int z)
    {
        for (int i = 0; i < 6; ++i)
        {
            if (i == 1)
                continue;

            ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[i];

            int fx = x + dir.offsetX;
            int fy = y + dir.offsetY;
            int fz = z + dir.offsetZ;

            Block block = world.getBlock(x, y, z);
            Material material = block.getMaterial();

            if (block.isAir(world, x, y, z) || !flowBlockers.contains(block) || !(material.blocksMovement() || material.isReplaceable()))
            {
                return true;
            }
        }

        return false;
    }

    /* Fluid Stuff End */

    /* IFluidBlock Implementation Start */

    @Override
    public Fluid getFluid()
    {
        return FluidRegistry.getFluid(fluidName);
    }

    @Override
    public FluidStack drain(World world, int x, int y, int z, boolean doDrain)
    {
        return null;
    }

    @Override
    public boolean canDrain(World world, int x, int y, int z)
    {
        return false;
    }

    @Override
    public float getFilledPercentage(World world, int x, int y, int z)
    {
        return 1.0f;
    }

    /* IFluidBlock Implementation End */
}

    

    



