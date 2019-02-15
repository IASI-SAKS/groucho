package it.cnr.iasi.saks.groucho.instrument;

import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

//public class GrouchoClassVisitor extends ClassWriter {
public class GrouchoClassVisitor extends ClassVisitor {
	private String className;
	private ClassWriter cw;
	
//	public GrouchoClassVisitor(ClassWriter cw, String pClassName) {
//		super(cw, ClassWriter.COMPUTE_MAXS);
//		this.className = pClassName;
//	}

	public GrouchoClassVisitor(ClassWriter cw, String pClassName) {
		super(Opcodes.ASM5, cw);
		this.className = pClassName;
		this.cw = cw;
	}

	@Override
	public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
		MethodVisitor mv = super.visitMethod(access, methodName, desc, signature, exceptions);
//		return new PrintMessageMethodVisitor(mv, this.className, methodName);
		 
//		System.out.println("---- " + signature + " **** " + Arrays.toString(Type.getArgumentTypes(desc)));
//		System.out.println("---- " + signature);
		String retrivedSignatureAsAString = Arrays.toString(Type.getArgumentTypes(desc));
		Type[] retrivedSignature = Type.getArgumentTypes(desc);
		return new CrochetMethodVisitor(mv, this.className, methodName, retrivedSignature);
	}

	public byte[] toByteArray() {
		return cw.toByteArray();
	}

}