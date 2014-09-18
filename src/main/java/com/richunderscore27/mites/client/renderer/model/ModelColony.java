package com.richunderscore27.mites.client.renderer.model;

import com.richunderscore27.mites.reference.Models;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelColony
{
    private IModelCustom modelColony;

    public ModelColony() { modelColony = AdvancedModelLoader.loadModel(Models.COLONY); }

    public void render()
    {
        modelColony.renderAll();
    }
}
