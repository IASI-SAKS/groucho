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
package it.cnr.iasi.saks.groucho.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StateCarver {

	private static final String JAVA_STRING = "java.lang.String";

	private static final String TOKEN_SEPARATOR_LAVEL_VALUE = "-->";
	private static final String TOKEN_OBJECT_START = "[";
	private static final String TOKEN_OBJECT_END = "]";
	private static final String TOKEN_FIELD_SEPARATOR = "%";
	
	private Object instrumentedObject;
	private String invivoTestClassName;
	private int maxCarvingDepth;
	
	public StateCarver(Context context){
		if (context != null){
			this.invivoTestClassName = context.getInstrumentedClassName();
			this.instrumentedObject = context.getInstrumentedObject();
			this.maxCarvingDepth = context.getMaxCarvingDepth();
		}
	}
	
/**
 * Access all private fields of the class.
 * 
 * about IllegalAccessException @see{@link it.cnr.iasi.saks.groucho.common.dumpFieldValue}
 * 
 *
 * @throws IllegalArgumentException
 * @throws ClassNotFoundException
 */
	public String carveAllFields() throws IllegalArgumentException, ClassNotFoundException {
			Class<?> clazz = Class.forName(this.invivoTestClassName, false, ClassLoader.getSystemClassLoader()); 
            Field[] fields = clazz.getDeclaredFields();
            
/**
 * TODO We may consider to order the attribute of a class by name
 */
          List<String> orderedFieldNames = this.orderedFieldByName(fields);

        	String carvedState = TOKEN_OBJECT_START;
            synchronized (this.instrumentedObject) {
                if (this.maxCarvingDepth > 0){
                	for (Field field : fields) {
                		carvedState = carvedState + TOKEN_FIELD_SEPARATOR + this.flattenInteralState(field, this.instrumentedObject, this.maxCarvingDepth);
                	}
                }
            }
            carvedState = carvedState.replaceFirst(TOKEN_FIELD_SEPARATOR, "") + TOKEN_OBJECT_END;
     	    
            return carvedState;
	}
		    
/**
* Recursive-case
 *
 * about IllegalAccessException @see{@link it.cnr.iasi.saks.groucho.common.dumpFieldValue}
 *
 * @throws IllegalArgumentException 
*/
	private String flattenInteralState(Field field, Object flatteingObject, int actualCarvingDepth) throws IllegalArgumentException {
		System.out.println("Name: "+field.getName()+", Object Type:"+flatteingObject.getClass().getName());
		String currentFlattenedState = "";
		if (actualCarvingDepth > 0) {
			Class<?> clazz = field.getType();
			Object fieldValue = dumpFieldValue(field, flatteingObject);
			if (this.isComplexType(clazz)) {
				String localFlattening = TOKEN_OBJECT_START;
				for (Field f : clazz.getDeclaredFields()) {					
					String tmp = this.flattenInteralState(f, fieldValue, actualCarvingDepth - 1);

					if (!tmp.isEmpty()){
						if (tmp.startsWith(TOKEN_OBJECT_START)){
							tmp = f.getName() + TOKEN_SEPARATOR_LAVEL_VALUE + tmp;
						}	
						localFlattening = localFlattening + TOKEN_FIELD_SEPARATOR + tmp;
					}
				}
				localFlattening = localFlattening.replaceFirst(TOKEN_FIELD_SEPARATOR, "");

				currentFlattenedState = field.getName() + TOKEN_SEPARATOR_LAVEL_VALUE + localFlattening + TOKEN_OBJECT_END;
			} else {
				currentFlattenedState = field.getName() + TOKEN_SEPARATOR_LAVEL_VALUE + fieldValue;
			}
		}
		return currentFlattenedState;
	}
	
	private List<String> orderedFieldByName(Field[] fields){
		List<String> listOfFieldNames = new ArrayList<String>();
		Collections.sort(listOfFieldNames);
		return listOfFieldNames;
	}
	
	private boolean isComplexType(Class<?> c){
		boolean result = !((c.isPrimitive()) || (c.isEnum()) || (c.getTypeName().equalsIgnoreCase(JAVA_STRING)));

		return result;
	}
	
/**
 * Note that is IllegalAccessException is catch and handled so that the returned Object is null	
 * 
 * @param f
 * @param instace
 * @return
 * @throws IllegalArgumentException
 */
	private Object dumpFieldValue(Field f, Object instace) throws IllegalArgumentException{
		Object fieldValue = null; 
		boolean wasPrivate = false;
        if (Modifier.isPrivate(f.getModifiers())) {
        	wasPrivate = true;
     	    f.setAccessible(true);
        }

        try {
			fieldValue = f.get(instace);
		} catch (IllegalAccessException e) {
			fieldValue = null;
		}

        if (wasPrivate) {
     	    f.setAccessible(false);
        }
        
        return fieldValue; 
	}
	
}
