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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import it.cnr.iasi.saks.groucho.common.InterfacesMapStore;

//public class GrouchoClassVisitor extends ClassWriter {
public class GrouchoClassVisitor extends ClassVisitor {
	private final static String JAVA_LANG_OBJECT = "java/lang/Object";
	private final static String JAVA_LANG_RUNNABLE = "java/lang/Runnable";

	private String className;
	private ClassWriter cw;
	
	private boolean isConstructorsInstrumentationEnabled;
	private boolean isRunnableInstrumentationEnabled;
	
	private final String CONSTRUCTOR_IN_BYTECODE = "<init>";
	private final String RUN_METHOD_IN_BYTECODE = "run";
			
//	public GrouchoClassVisitor(ClassWriter cw, String pClassName) {
//		super(cw, ClassWriter.COMPUTE_MAXS);
//		this.className = pClassName;
//	}

	public GrouchoClassVisitor(ClassWriter cw, String pClassName) {
		super(Opcodes.ASM5, cw);
		this.className = pClassName;
		this.cw = cw;
		
		this.isConstructorsInstrumentationEnabled = false;
		this.isRunnableInstrumentationEnabled = false;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superClassName, String[] interfaces){
		super.visit(version, access, name, signature, superClassName, interfaces);
		System.out.println("GenericSuperclass for "+this.className+" --> "+superClassName);
		this.isConstructorsInstrumentationEnabled = superClassName.equalsIgnoreCase(JAVA_LANG_OBJECT);
		
        this.retrieveInheritedInterfaceNames(this.className);
		
		this.isRunnableInstrumentationEnabled = this.isImplementingRunnableSomehow();		
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
			ThreadHarnessConstructorsMethodVisitor thC_MV = new ThreadHarnessConstructorsMethodVisitor(mv, this.className, methodName, retrivedSignature);
			resultingMV = new CrochetMethodVisitor(thC_MV, this.className, methodName, retrivedSignature);			
		}else{
			if((this.isRunnableInstrumentationEnabled) && (this.isRun(methodName, signature))){
				ThreadHarnessRunMethodVisitor thR_MV = new ThreadHarnessRunMethodVisitor(mv, this.className, methodName, retrivedSignature);
				resultingMV = new CrochetMethodVisitor(thR_MV, this.className, methodName, retrivedSignature);			
			}else{
				resultingMV = new CrochetMethodVisitor(mv, this.className, methodName, retrivedSignature);
			}	
		}
		
		return resultingMV;
	}

	public byte[] toByteArray() {
		return cw.toByteArray();
	}

	
	protected boolean isImplementingRunnableSomehow(){
		boolean result = InterfacesMapStore.getInstance().isImplemented(this.className, JAVA_LANG_RUNNABLE);
        System.out.println(" \t isImplementingRunnableSomehow? \n\t Exitus: "+result);
		for (String interfaceItem : InterfacesMapStore.getInstance().dumpListOfAllInterfaces(this.className)) {
            System.out.println(" \t  \t interface:"+interfaceItem);
		};
		return result;
	}
	
/**
 * 	
 * Base-case
 */
    protected void retrieveInheritedInterfaceNames(String localClassName)
    {
		List<String> interfaceList = new ArrayList<String>();
        this.retrieveInheritedInterfaceNames(this.className, interfaceList);
    }    
    
/**
* Recursive-case
*/
    protected void retrieveInheritedInterfaceNames(String localClassName, List<String> interfaceList)
    {
    	ClassReader cr=null;
    	try {
    		cr = new ClassReader(localClassName);
    		String superName = cr.getSuperName();
       //System.out.println("[superName]"+superName);
    		if(superName!=null && !superName.equals(JAVA_LANG_OBJECT))
    		{
    			String superClass = superName.replace('.', '/');
    			this.retrieveInheritedInterfaceNames(superClass, interfaceList);
    		}

   			String[] interfaces = cr.getInterfaces();
   			if(interfaces!=null && interfaces.length > 0){
   				for(int k=0;k<interfaces.length;k++){
   					String inface = interfaces[k];
   					interfaceList.add(inface);
   				}
   			}

   			for (String superInterfaceItem : interfaceList) {
   				InterfacesMapStore.getInstance().put(localClassName, superInterfaceItem);				
   			}                
        
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
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
		String result = this.CONSTRUCTOR_IN_BYTECODE;
		
//		System.out.println(methodName + " ???? " + result);		
		return methodName.equals(result);
	}

	protected boolean isRun(String methodName, String signature){		
		String result = this.RUN_METHOD_IN_BYTECODE;
		
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