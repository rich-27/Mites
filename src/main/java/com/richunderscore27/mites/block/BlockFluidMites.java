package com.richunderscore27.mites.block;

import com.richunderscore27.mites.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidMites extends BlockFluidClassic
{
    private IIcon[] blockIcons;

    public BlockFluidMites(Fluid fluid, Material material)
    {
        super(fluid, material);
        this.setHardness(100.0F);
        this.disableStats();
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
        blockIcons = new IIcon[2];
        blockIcons[0] = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
        blockIcons[1] = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_flow");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int metaData, int side)
    {
        return metaData != 0 && metaData != 1 ? this.blockIcons[1] : this.blockIcons[0];
    }
}