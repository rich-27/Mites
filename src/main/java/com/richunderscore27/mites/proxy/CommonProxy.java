package com.richunderscore27.mites.proxy;

import com.richunderscore27.mites.handler.ConfigurationHandler;
import com.richunderscore27.mites.reference.Names;
import com.richunderscore27.mites.tileentity.TileEntityColony;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    public void registerEventHandlers()
    {
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityColony.class, Names.Blocks.COLONY);
    }
}
