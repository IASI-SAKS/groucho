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
import java.util.List;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;
import it.cnr.iasi.saks.groucho.callback.AbstractGovernanceManager;
import it.cnr.iasi.saks.groucho.callback.GovernanceManagerFactory;
import it.cnr.iasi.saks.groucho.common.Context;

public class CrochetMethodVisitor extends MethodVisitor {

	private String className;
	private String methodName;
	private Type[] methodSignature;
	
	private TestableInVivoAnnotationVisitor annVisitor;
	
	private boolean isAnnotationValid;
	private String invivoTestClass;
	private String invivoTest;	
	
	public CrochetMethodVisitor(MethodVisitor mv, String className, String methodName, Type[] signature) {
		super(Opcodes.ASM5, mv);

		this.methodName = methodName;
		this.className = className;
		this.methodSignature = signature;
		
		this.annVisitor = null;
		
//		if (this.methodSignature == null){
//			this.methodSignature = "";
//		}
		
		this.isAnnotationValid = false;
	}
	
	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// 1. check method for annotation @TestableInVivo
		// 2. if annotation present, then get important method param indexes
		String targetAnnotationName = Type.getType(TestableInVivo.class).getInternalName();
		String intenalAnnotationType = "L"+ targetAnnotationName + ";";
		if ((intenalAnnotationType.equals(desc)) && (visible)) {
			    this.isAnnotationValid = true;
			    AnnotationVisitor defaultAV = super.visitAnnotation(desc, visible);
			    this.annVisitor = new TestableInVivoAnnotationVisitor(this,defaultAV); 	
			    return this.annVisitor;   	
		}				
		return super.visitAnnotation(desc, visible);
	}

	@Override
	public void visitCode() {
		this.mv.visitCode();
		// 3. if annotation present, add logging to beginning of the method
		if (this.isAnnotationValid){
			try {
				this.retriveAnnotationValues();
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

	private void retriveAnnotationValues() throws IllegalStateException{
/*
 * TODO the body of this method needs some re-factoring in order to remove the implicit dependency
 * given by the constant strings used in getAnnotationValues. A good choice would be to
 * directly access to the name of the annotation as-a-string 		
 */
		this.invivoTestClass = this.annVisitor.getAnnotationValues("invivoTestClass");
		this.invivoTest = this.annVisitor.getAnnotationValues("invivoTest");
	}	

	private void applyInstrumentation() {
		// 3. if annotation present, add logging to beginning of the method
//*****************************************
//****computeFirstAvailableIndexInTheStack*************************************
			int lstIndex = this.computeFirstAvailableIndexInTheStack();
//****injectOtherReferenceListInTheContext*************************************			
			mv.visitTypeInsn(Opcodes.NEW, "java/util/ArrayList");
			mv.visitInsn(Opcodes.DUP);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
			mv.visitVarInsn(Opcodes.ASTORE, lstIndex);
//****injectActualParametersInTheContext*************************************
			this.injectActualParametersInTheContext(lstIndex);
//*****************************************
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitLdcInsn(this.className);
			mv.visitLdcInsn(this.methodName);
			String signatureAsString = Arrays.toString(this.methodSignature);
			mv.visitLdcInsn(signatureAsString);
			mv.visitLdcInsn(this.invivoTestClass);
			mv.visitLdcInsn(this.invivoTest);
			mv.visitVarInsn(Opcodes.ALOAD, lstIndex);			
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "it/cnr/iasi/saks/groucho/instrument/CrochetMethodVisitor", "invokeCallback", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", false);
	}

	private void injectActualParametersInTheContext(int lstIndex) {
		
		final String METHOD_VALUEOF = "valueOf";
		
		int slotIndex = 1;
		for (Type type : this.methodSignature) {
			mv.visitVarInsn(Opcodes.ALOAD, lstIndex);
			switch (type.getSort()) {
			case Type.BOOLEAN:
// CASE ILOAD				
				mv.visitVarInsn(Opcodes.ILOAD, slotIndex);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", METHOD_VALUEOF, "(Z)Ljava/lang/Boolean;", false);
				slotIndex++;
				break;
			case Type.BYTE:
// CASE ILOAD				
				mv.visitVarInsn(Opcodes.ILOAD, slotIndex);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", METHOD_VALUEOF, "(B)Ljava/lang/Byte;", false);
				slotIndex++;
				break;
			case Type.CHAR:
// CASE ILOAD				
				mv.visitVarInsn(Opcodes.ILOAD, slotIndex);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", METHOD_VALUEOF, "(C)Ljava/lang/Character;", false);					slotIndex++;
				break;
			case Type.SHORT:
// CASE ILOAD				
				mv.visitVarInsn(Opcodes.ILOAD, slotIndex);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", METHOD_VALUEOF, "(S)Ljava/lang/Short;", false);
				slotIndex++;
				break;
			case Type.INT:
// CASE ILOAD				
				mv.visitVarInsn(Opcodes.ILOAD, slotIndex);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", METHOD_VALUEOF, "(I)Ljava/lang/Integer;", false);
				slotIndex++;
				break;
			case Type.LONG:
// CASE LLOAD										
				mv.visitVarInsn(Opcodes.LLOAD, slotIndex);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", METHOD_VALUEOF, "(J)Ljava/lang/Long;", false);
				slotIndex+=2;
				break;
			case Type.FLOAT:
// CASE FLOAD										
				mv.visitVarInsn(Opcodes.FLOAD, slotIndex);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", METHOD_VALUEOF, "(F)Ljava/lang/Float;", false);
				slotIndex++;
				break;
			case Type.DOUBLE:
// CASE DLOAD										
				mv.visitVarInsn(Opcodes.DLOAD, slotIndex);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", METHOD_VALUEOF, "(D)Ljava/lang/Double;", false);
				slotIndex+=2;
				break;
			default:
// CASE ALOAD										
				mv.visitVarInsn(Opcodes.ALOAD, slotIndex);
				slotIndex++;
				break;
			}
			mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
			mv.visitInsn(Opcodes.POP);
		}
	}

	private int computeFirstAvailableIndexInTheStack() {
		int lstIndex = 1;
		for (Type type : this.methodSignature) {
			switch (type.getSort()) {
			case Type.BOOLEAN:
			case Type.BYTE:
			case Type.CHAR:
			case Type.SHORT:
			case Type.INT:
// CASE ILOAD				
				lstIndex++;
				break;
			case Type.LONG:
// CASE LLOAD										
				lstIndex+=2;
				break;
			case Type.FLOAT:
// CASE FLOAD										
				lstIndex++;
				break;
			case Type.DOUBLE:
// CASE DLOAD										
				lstIndex+=2;
				break;
			default:
// CASE ALOAD										
				lstIndex++;
				break;
			}
		}
		return lstIndex;
	}

	private void instrumentingMessage(){
		System.out.println(this.getClass().getName() +" is instrumenting: "+this.className + "." + this.methodName);		
	}
	
	public static void invokeCallback(Object instObj, String instClass, String instMethod, String instMethodSignature, String testClass, String testMethod) {
		System.out.println("Enabling callback for: "+ instObj.toString() + ", " + instClass + ", " + instMethod + "( "+instMethodSignature+")");
		System.out.println("\t testing with: "+ testClass + ", " + testMethod);
		
		Context context = new Context(instObj, instClass, instMethod, testClass, testMethod);

		AbstractGovernanceManager gm = GovernanceManagerFactory.getInstance().getGovernanceManager();		
//		if (gm.evaluateActivation(context)){
			gm.runInvivoTestingSession(context);
//		}	
	}
	
	public static void invokeCallback(Object instObj, String instClass, String instMethod, String instMethodSignature, String testClass, String testMethod, List<Object> lstOtherReferencesInContext) {
		System.out.println("Enabling callback for: "+ instObj.toString() + ", " + instClass + ", " + instMethod + "( "+instMethodSignature+")");
		System.out.println("\t testing with: "+ testClass + ", " + testMethod);
		
		Context context = new Context(instObj, instClass, instMethod, testClass, testMethod);
		context.setOtherReferencesInContext(lstOtherReferencesInContext);

		AbstractGovernanceManager gm = GovernanceManagerFactory.getInstance().getGovernanceManager();		
//		if (gm.evaluateActivation(context)){
			gm.runInvivoTestingSession(context);
//		}	
	}
}