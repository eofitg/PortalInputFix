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
