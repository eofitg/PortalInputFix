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

package dev.rocco.mods.portalinputfix.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PortalFixVisitor extends MethodVisitor {
    public PortalFixVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        super.visitFieldInsn(opcode, owner, name, desc);
        // We hook to the field write to `Minecraft#inGameHasFocus` (boolean)
        if(opcode == Opcodes.PUTFIELD && "Z".equals(desc)) {
            System.out.println("[PortalFix] Found call, hooking.");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "dev/rocco/mods/portalinputfix/ASMAccess", "restoreKeyboardState", "()V", false);
        }
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }
}
