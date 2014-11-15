package com.richunderscore27.mites.proxy;

import com.richunderscore27.mites.client.renderer.item.ItemRendererColony;
import com.richunderscore27.mites.client.renderer.tileentity.TileEntityRendererColony;
import com.richunderscore27.mites.init.ModBlocks;
import com.richunderscore27.mites.reference.RenderIds;
import com.richunderscore27.mites.tileentity.TileEntityColony;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();
        // FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
    }

    @Override
    public void registerKeyBindings()
    {
        /*
        ClientRegistry.registerKeyBinding(Keybindings.key1);
        ClientRegistry.registerKeyBinding(Keybindings.key2);
        */
    }

    @Override
    public void initRenderingAndTextures()
    {
        RenderIds.colony = RenderingRegistry.getNextAvailableRenderId();

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.colony), new ItemRendererColony());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityColony.class, new TileEntityRendererColony());
    }
}
