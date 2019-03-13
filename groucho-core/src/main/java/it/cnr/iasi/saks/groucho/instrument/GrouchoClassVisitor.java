/* 
 * This file is part of the GROUCHO project.
 * 
 * GROUCHO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GROUCHO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GROUCHO.  If not, see <https://www.gnu.org/licenses/>
 *
 */
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