package com.richunderscore27.mites.client.renderer.item;

import com.richunderscore27.mites.client.renderer.model.ModelColony;
import com.richunderscore27.mites.reference.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererColony implements IItemRenderer
{
    private final ModelColony modelColony;

    public ItemRendererColony()
    {
        modelColony = new ModelColony();
    }

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType itemRenderType)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data)
    {
        switch (itemRenderType)
        {
            case ENTITY:
            {
                renderColony(-0.5F, -0.38F, 0.5F);
                return;
            }
            case EQUIPPED:
            {
                renderColony(0.0F, 0.0F, 1.0F);
                return;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                renderColony(0.0F, 0.0F, 1.0F);
                return;
            }
            case INVENTORY:
            {
                renderColony(-1.0F, -0.9F, 0.0F);
                return;
            }
            default:
        }
    }

    private void renderColony(float x, float y, float z)
    {
        GL11.glPushMatrix();

        // Scale, Translate, Rotate
        GL11.glScalef(1F, 1F, 1F);
        GL11.glTranslatef(x, y, z);

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Model.COLONY);

        // Render
        modelColony.render();

        GL11.glPopMatrix();
    }
}
