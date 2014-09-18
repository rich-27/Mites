package com.richunderscore27.mites.item;

import com.richunderscore27.mites.creativetab.CreativeTabMites;
import com.richunderscore27.mites.reference.Reference;
import com.richunderscore27.mites.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMites extends Item
{
    public ItemMites()
    {
        super();
        this.setCreativeTab(CreativeTabMites.MITES_TAB);
    }

    @Override
    public String getUnlocalizedName()
    {
        return "item." + Reference.MOD_ID.toLowerCase() + ":" + super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return "item." + Reference.MOD_ID.toLowerCase() +":" + super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }
}
