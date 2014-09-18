package com.richunderscore27.mites.reference;

import com.richunderscore27.mites.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public final class Textures
{
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    public static final class Model
    {
        private static final String MODEL_TEXTURE_LOCATION = "textures/models/";
        public static final ResourceLocation COLONY = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "colony.png");
    }
}
