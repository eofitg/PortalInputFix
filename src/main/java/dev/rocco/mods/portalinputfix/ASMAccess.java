/*
 * Copyright 2020 RoccoDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
