# How the mod works (for mod developers)
The mod uses [ASM](https://asm.ow2.io/) to manipulate the bytecode of the `net.minecraft.client.Minecraft`
class (`ave` in the 1.8.9 jar).

The method we want to manipulate is `setIngameFocus()V` (L1415 in forgeSrc-1.8.9-11.15.1.1722).

After the `Minecraft.inGameHasFocus` field is set to `true`, the mod calls the static method
`dev.rocco.mods.portalinputfix.ASMAccess.restoreKeyboardState()V`.

This iterates through the key bindings in the game settings and calls `KeyBinding.setKeyBindState(IZ)V` on each of them.
This basically refreshes the state of all the keybinds when the game gets focused.

This behavior can be found in Minecraft 1.12 in the `KeyBinding.updateKeyBindState()V` method.