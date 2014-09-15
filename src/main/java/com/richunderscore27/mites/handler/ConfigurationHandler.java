package com.richunderscore27.mites.handler;

import com.richunderscore27.mites.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;
    public static int maxSearchBlocks = 500;

    public static void init(File configFile)
    {
        // Create the configuration object from the given configuration file
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        maxSearchBlocks = configuration.getInt("maxSearchBlocks", Configuration.CATEGORY_GENERAL, 500, 0, 10000, "Maximum number of blocks in a given Mite search");

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}