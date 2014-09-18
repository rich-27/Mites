package com.richunderscore27.mites.client.renderer.tileentity;

import com.richunderscore27.mites.client.renderer.model.ModelColony;
import com.richunderscore27.mites.reference.Textures;
import com.richunderscore27.mites.tileentity.TileEntityColony;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityRendererColony extends TileEntitySpecialRenderer
{

    private final ModelColony modelColony = new ModelColony();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if (tileEntity instanceof TileEntityColony)
        {
            GL11.glPushMatrix();

            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x + 0.0F, (float) y + 0.0F, (float) z + 1.0F);

            // Bind texture
            this.bindTexture(Textures.Model.COLONY);

            // Render
            modelColony.render();

            GL11.glPopMatrix();
        }
    }
}
