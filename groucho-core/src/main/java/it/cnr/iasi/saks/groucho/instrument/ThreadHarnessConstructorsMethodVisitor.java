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

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ThreadHarnessMethodVisitor extends MethodVisitor {

	private String className;
	private String methodName;
	private Type[] methodSignature;
	
	private TestableInVivoAnnotationVisitor annVisitor;
	
	private boolean isAnnotationValid;
	private String invivoTestClass;
	private String invivoTest;	
	
	public ThreadHarnessMethodVisitor(MethodVisitor mv, String className, String methodName, Type[] signature) {
		super(Opcodes.ASM5, mv);

		this.methodName = methodName;
		this.className = className;
		this.methodSignature = signature;
		
		this.annVisitor = null;
		
//		if (this.methodSignature == null){
//			this.methodSignature = "";
//		}
		
		this.isAnnotationValid = true;
	}
	
//	@Override
//	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
//		// 1. check method for annotation @TestableInVivo
//		// 2. if annotation present, then get important method param indexes
//		return super.visitAnnotation(desc, visible);
//	}

	@Override
	public void visitCode() {
		this.mv.visitCode();
		this.visitLogic();
	}

	private void visitLogic() {
		// 3. if annotation present, add logging to beginning of the method
		if (this.isAnnotationValid){
			try {
//				this.retriveAnnotationValues();
				this.instrumentingMessage();
				this.applyInstrumentation();
			} catch (IllegalStateException e) {
				System.err.println("now here i am");
				e.printStackTrace();
			} catch (Throwable t) {
				System.err.println("here i am");
				t.printStackTrace();
			}	
		}	
	}

	private void applyInstrumentation() {
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "it/cnr/iasi/saks/groucho/instrument/ThreadHarnessCallbackInvoker", "invokeCallback_checkInTheConstructors", "()V", false);
	}

	private void instrumentingMessage(){
		System.out.println(this.getClass().getName() +" is instrumenting: "+this.className + "." + this.methodName);		
	}
		
}