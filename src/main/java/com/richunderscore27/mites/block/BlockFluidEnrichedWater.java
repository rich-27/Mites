package com.richunderscore27.mites.block;

import com.richunderscore27.mites.reference.Materials;
import com.richunderscore27.mites.init.ModFluids;
import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidEnrichedWater extends BlockFluidClassic
{
    private IIcon[] blockIcons;

    public BlockFluidEnrichedWater()
    {
        this(ModFluids.enrichedWater, Materials.solidWater);
    }

    public BlockFluidEnrichedWater(Fluid fluid, Material material)
    {
        super(fluid, material);
        this.setHardness(100.0F);
        this.disableStats();
        this.setBlockName(Names.BlockFluids.ENRICHED_WATER);
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
    public IIcon getIcon(int metaData, int side)
    {
        return metaData != 0 && metaData != 1 ? this.blockIcons[1] : this.blockIcons[0];
    }
}