package com.richunderscore27.mites;

import com.richunderscore27.mites.handler.ConfigurationHandler;
import com.richunderscore27.mites.init.ModBlocks;
import com.richunderscore27.mites.init.ModItems;
import com.richunderscore27.mites.init.Recipies;
import com.richunderscore27.mites.proxy.IProxy;
import com.richunderscore27.mites.reference.Reference;
import com.richunderscore27.mites.utility.LogHelper;
import com.richunderscore27.mites.worldgen.ModWorldGen;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Mites
{
    // The instance of the mod that Forge uses.
    @Mod.Instance(Reference.MOD_ID)
    public static Mites instance;

    // Defines where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        // proxy.registerKeyBindings();

        ModItems.init();

        ModBlocks.init();

        ModWorldGen modWorldGen = new ModWorldGen();
        GameRegistry.registerWorldGenerator(modWorldGen, 0);
        FMLCommonHandler.instance().bus().register(modWorldGen);

        LogHelper.info("Pre Initialization Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());

        Recipies.init();

        LogHelper.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.info("Post Initialization Complete!");
    }
}
