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
package it.cnr.iasi.saks.groucho.lab.instrument;

import java.util.Arrays;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;
import it.cnr.iasi.saks.groucho.lab.instrument.config.InstrumentModelStore;
import it.cnr.iasi.saks.groucho.lab.instrument.model.AnnotatedMethodModel;
import it.cnr.iasi.saks.groucho.lab.instrument.model.TestableInvivoModel;

public class InvivoTestAnnotatorMethodVisitor extends MethodVisitor {

	private String className;
	private String methodName;
	private Type[] methodSignature;
		
	private String targetAnnotationName;
	private String internalAnnotationType;
    private boolean isAnnotationPresent;
	
	private String invivoTestClass;
	private String invivoTest;	
	
	private AnnotatedMethodModel annotatedMethodModel;
	
	public InvivoTestAnnotatorMethodVisitor(MethodVisitor mv, String className, String methodName, Type[] signature) {
		super(Opcodes.ASM5, mv);

		this.methodName = methodName;
		this.className = className;
		this.methodSignature = signature;
				
		this.targetAnnotationName = Type.getType(TestableInVivo.class).getInternalName();
		this.internalAnnotationType = "L"+ targetAnnotationName + ";";

		this.isAnnotationPresent = false;
		this.annotatedMethodModel = null;
	}
	
	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// 1. check method for annotation @TestableInVivo
		// 2. if annotation is already present, the flag is marked
		
		if ((this.internalAnnotationType.equals(desc)) && (visible)) {
		    this.isAnnotationPresent = true;
		}				
		return super.visitAnnotation(desc, visible);
	}

	public boolean isTestableInvivoAnnotationPresent() {
		return this.isAnnotationPresent;
	}
	
	@Override
	public void visitCode() {
		this.mv.visitCode();
		// 3. if annotation is not present, add it.
		boolean injectAnnotation = (!this.isTestableInvivoAnnotationPresent()) && (this.isMethodSubjectToInvivo());
		if (injectAnnotation){
			try {
				this.retriveAnnotationValues();
				this.instrumentingMessage();
				this.applyInstrumentation();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Throwable t) {
				t.printStackTrace();
			}	
		}	
	}

	private void retriveAnnotationValues() throws IllegalStateException{
		TestableInvivoModel annotation = this.annotatedMethodModel.getAnnotation();
		
		this.invivoTestClass = annotation.getInvivoTestClass();
		this.invivoTest = annotation.getInvivoTest();
/*
 * TODO the body of this method needs to be completed in order to consider also
 * the other params of an annotation
 */
	}	
	
	
	private void applyInstrumentation() {
		AnnotationVisitor av;
		{
			av = mv.visitAnnotation(this.internalAnnotationType, true);
/*
 * TODO the following statements of this method needs re-factoring in order to get
 * the name of the field by means of some reflection ....		
*/
			av.visit("invivoTestClass", this.invivoTestClass);
			av.visit("invivoTest", this.invivoTest);
			
			av.visitEnd();
		}
	}

	private void instrumentingMessage() {
		String retrivedSignatureAsAString = Arrays.toString(this.methodSignature);
		System.out.println("["+this.getClass().getName() +"] Annotating as "+TestableInVivo.class.getName() + " - " + this.className + "." + this.methodName +"("+ retrivedSignatureAsAString +")");		
	}

	private boolean isMethodSubjectToInvivo() {		
		this.annotatedMethodModel = InstrumentModelStore.getInstance().retreiveMethodInstrumentable(this.className, this.methodName);
		
		return this.annotatedMethodModel != null;
	}

}