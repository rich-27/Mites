package com.richunderscore27.mites.client.handler;

import com.richunderscore27.mites.client.settings.Keybindings;
import com.richunderscore27.mites.reference.Key;
import com.richunderscore27.mites.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler
{
    private static Key getPressedKeybinding()
    {
        /*
        if (Keybindings.key1.isPressed())
        {
            return Key.KEY_ONE;
        }
        else if (Keybindings.key2.isPressed())
        {
            return Key.KEY_TWO;
        }
        */

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        LogHelper.info(getPressedKeybinding());
    }
}
