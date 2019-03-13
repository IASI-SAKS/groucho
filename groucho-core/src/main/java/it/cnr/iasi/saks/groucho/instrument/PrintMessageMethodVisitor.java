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

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;

public class PrintMessageMethodVisitor extends MethodVisitor {

	private String methodName;
	private String className;

	private boolean isAnnotationValid;
	
	public PrintMessageMethodVisitor(MethodVisitor mv, String className, String methodName) {
		super(Opcodes.ASM5, mv);

		this.methodName = methodName;
		this.className = className;
		this.isAnnotationValid = false;
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// 1. check method for annotation @ImportantLog
		// 2. if annotation present, then get important method param indexes
		String targetAnnotationName = Type.getType(TestableInVivo.class).getInternalName();
		String intenalAnnotationType = "L"+ targetAnnotationName + ";";
		if (intenalAnnotationType.equals(desc)) {
			    this.isAnnotationValid = true;
		}
		
		
		return super.visitAnnotation(desc, visible);
	}

	@Override
	public void visitCode() {
		// // 3. if annotation present, add logging to beginning of the method
		// mv.visitMethodInsn(Opcodes.INVOKESTATIC,
		// "it/cnr/iasi/saks/groucho/instrument/PrintMessageMethodVisitor",
		// "message", "()V", false);
		if (this.isAnnotationValid){
			try {
				this.instrumentingMessage();
//				visitFoo();
//				visitFooWithParams();
				visitFooWithParamsAndRefernceToThis();
			} catch (Throwable t) {
				System.err.println("here i am");
				t.printStackTrace();
			}
		}	
	}

	private void visitFoo() {
		// 3. if annotation present, add logging to beginning of the method
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "it/cnr/iasi/saks/groucho/instrument/PrintMessageMethodVisitor", "message", "()V", false);
	}

	private void visitFooWithParams() {
		// 3. if annotation present, add logging to beginning of the method
		mv.visitLdcInsn(this.className);
		mv.visitLdcInsn(this.methodName);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "it/cnr/iasi/saks/groucho/instrument/PrintMessageMethodVisitor", "message", "(Ljava/lang/String;Ljava/lang/String;)V", false);
	}

	private void visitFooWithParamsAndRefernceToThis() {
		// 3. if annotation present, add logging to beginning of the method
		if (this.methodName.equalsIgnoreCase("foo") || this.methodName.equalsIgnoreCase("getID")){
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitLdcInsn(this.className);
			mv.visitLdcInsn(this.methodName);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "it/cnr/iasi/saks/groucho/instrument/PrintMessageMethodVisitor", "message", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V", false);
		}
	}

	private void instrumentingMessage(){
		System.out.println("Instrumenting: "+this.className + "." + this.methodName);		
	}
	
	public static void message() {
		System.out.println("Hello world");
	}

	public static void message(String c, String m) {
		System.out.println("Hello world: " + c + ", " + m);
	}

	public static void message(Object o, String c, String m) {
		System.out.println("Hello world: "+ o.toString() + ", " + c + ", " + m);
	}
}