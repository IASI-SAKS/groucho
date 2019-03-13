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
package it.cnr.iasi.saks.groucho.callback;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.objectweb.asm.Type;

public class SimpleGovernanceManager extends AbstractGovernanceManager {

	@Override
	public boolean evaluateActivation(Context context) {
		return true;
	}

	@Override
	protected void doInvivoTestingSession(Context context) {
		System.out.println("... doing something here, between the checkpoint and the rollback");
		
		try {
/*
 * TODO here we may think to support method with parameters
 */
			Class<?>[] parametersList = this.convertSignature(null);
			
			String invivoTestClassName = context.getInvivoTestClass();
			String invivoTestName = context.getInvivoTest();
			Class<?> clazz = Class.forName(invivoTestClassName, false, ClassLoader.getSystemClassLoader()); 
			Method method = clazz.getMethod(invivoTestName, Context.class);
			
			Object reflectiveObject = clazz.newInstance();
			
			method.invoke(reflectiveObject, context);
//					if (method != null){
//							TestableInVivo annotation = method.getAnnotation(TestableInVivo.class);
//							this.invivoTest = annotation.invivoTest();
//							this.invivoTestClass = annotation.invivoTestClass();
//					}					
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private Class<?>[] convertSignature(Type[] methodSignature) throws ClassNotFoundException{
		Class<?>[] parametes = null; 

		if (methodSignature != null){
			ArrayList<Class<?>> parList = new ArrayList<Class<?>>();
			for (Type param : methodSignature) {
				Class<?> p;
				switch (param.getSort()) {
					case Type.ARRAY:					
						break;
					case Type.BOOLEAN:
						p = boolean.class;
						parList.add(p);				
						break;
					case Type.CHAR:					
						p = char.class;
						parList.add(p);				
						break;
					case Type.DOUBLE:
						p = double.class;
						parList.add(p);				
						break;
					case Type.FLOAT:
						p = float.class;
						parList.add(p);				
						break;
					case Type.INT:
						p = int.class;
						parList.add(p);				
						break;
					case Type.LONG:
						p = long.class;
						parList.add(p);				
						break;
					case Type.SHORT:
						p = short.class;
						parList.add(p);				
						break;
					case Type.VOID:					
						break;
					default:
						p = Class.forName(param.getClassName(), false, ClassLoader.getSystemClassLoader());
						parList.add(p);				
					break;
				}
			}
			int size = parList.size();
			parametes = new Class<?>[size];
			for (int index = 0; index < parametes.length; index++) {
				parametes[index] = parList.get(index);				
			}
		}
		return parametes;
	}
	
	
}
