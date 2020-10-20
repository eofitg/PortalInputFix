package dev.rocco.mods.portalinputfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class ASMAccess {
    public static void restoreKeyboardState() {
        if(Minecraft.isRunningOnMac) return; // Not sure why, but Minecraft 1.12 seems to check this before updating the state
        for(KeyBinding keyBinding : Minecraft.getMinecraft().gameSettings.keyBindings) {
            try {
                KeyBinding.setKeyBindState(keyBinding.getKeyCode(), keyBinding.getKeyCode() < 256 && Keyboard.isKeyDown(keyBinding.getKeyCode()));
            } catch (IndexOutOfBoundsException ignored) {
                // MC 1.12 ignores this exception, but it doesn't seem it can be thrown by anything in the call stack.
                // Anyway, better safe than sorry LULW
            }
        }
    }
}
