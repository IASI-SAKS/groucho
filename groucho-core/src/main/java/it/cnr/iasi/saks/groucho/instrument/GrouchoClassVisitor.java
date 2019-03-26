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
	private boolean isConstructorsInstrumentationEnabled;
	
	private final String constructorInByteCode = "<init>";
			
//	public GrouchoClassVisitor(ClassWriter cw, String pClassName) {
//		super(cw, ClassWriter.COMPUTE_MAXS);
//		this.className = pClassName;
//	}

	public GrouchoClassVisitor(ClassWriter cw, String pClassName) {
		this(cw, pClassName, false);
	}
	
	public GrouchoClassVisitor(ClassWriter cw, String pClassName, boolean enableConstructorInstrumentation) {
		super(Opcodes.ASM5, cw);
		this.className = pClassName;
		this.cw = cw;
		
		this.isConstructorsInstrumentationEnabled = enableConstructorInstrumentation;
	}

	@Override
	public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
		MethodVisitor mv = super.visitMethod(access, methodName, desc, signature, exceptions);
//		return new PrintMessageMethodVisitor(mv, this.className, methodName);
		
//		System.out.println("---- " + signature + " **** " + Arrays.toString(Type.getArgumentTypes(desc)));
//		System.out.println("---- " + signature);
		String retrivedSignatureAsAString = Arrays.toString(Type.getArgumentTypes(desc));
		Type[] retrivedSignature = Type.getArgumentTypes(desc);

		MethodVisitor resultingMV;
		if ((this.isConstructorsInstrumentationEnabled) && (this.isContructor(methodName))){
			ThreadHarnessMethodVisitor thMV = new ThreadHarnessMethodVisitor(mv, this.className, methodName, retrivedSignature);
			resultingMV = new CrochetMethodVisitor(thMV, this.className, methodName, retrivedSignature);			
		}else{
			resultingMV = new CrochetMethodVisitor(mv, this.className, methodName, retrivedSignature);
		}
		
		return resultingMV;
	}

	public byte[] toByteArray() {
		return cw.toByteArray();
	}

/**
 * Possibly this method is useless.
 * Such a check has been moved in the @see {@link it.cnr.iasi.saks.groucho.instrument.GrouchoClassTransformer}
 * adding a further constructor in this class.
 */
	protected boolean hasExplicitSuperclass(String className){		
		boolean result = false;
		try {
			System.out.println("GenericSuperclass for "+className+"--> "+Class.forName(className, false, ClassLoader.getSystemClassLoader()).getGenericSuperclass());
			java.lang.reflect.Type superclass = Class.forName(className, false, ClassLoader.getSystemClassLoader()).getGenericSuperclass();
//			System.out.println("GenericSuperclass for "+className+"--> "+Class.forName(className).getGenericSuperclass());
//			java.lang.reflect.Type superclass = Class.forName(className).getGenericSuperclass();
			if (superclass != null){
				result = !(superclass.equals(Object.class));
			}	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	protected boolean isContructor(String methodName){		
		String result = this.constructorInByteCode;
		
//		System.out.println(methodName + " ???? " + result);		
		return methodName.equals(result);
	}

	protected boolean isContructorBySourceName(String methodName){		
		String result = this.extractContructorSourceName();
		
//		System.out.println(methodName + " ???? " + result);		
		return methodName.equals(result);
	}

	protected String extractContructorSourceName(){
		String result = "";
		int nameStartsAt = this.className.lastIndexOf('/')+1;
		if (nameStartsAt > 0){
			result = this.className.substring(nameStartsAt);			
		} else {
			result = this.className;
		}
		
		return result;
	}
}