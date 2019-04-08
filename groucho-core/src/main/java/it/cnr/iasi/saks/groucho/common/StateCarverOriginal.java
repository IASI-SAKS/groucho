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

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;


public class StateCarverOriginal {

	private static final String JAVA_STRING = "java.lang.String";

	private static final String TOKEN_SEPARATOR_LEVEL_VALUE = "-->";
	private static final String TOKEN_ARRAY_START = "[";
	private static final String TOKEN_ARRAY_END = "]";
	private static final String TOKEN_ELEMENT_SEPARATOR = "ç";
	private static final String TOKEN_OBJECT_START = "{";
	private static final String TOKEN_OBJECT_END = "}";
	private static final String TOKEN_FIELD_SEPARATOR = "%";
	
	private Object instrumentedObject;
	private String invivoTestClassName;
	private int maxCarvingDepth;
	
	private boolean processFinalFieldsFlag;

	public StateCarverOriginal(Context context){
		if (context != null){
			this.invivoTestClassName = context.getInstrumentedClassName();
			this.instrumentedObject = context.getInstrumentedObject();
			this.maxCarvingDepth = context.getMaxCarvingDepth();
		}else{
			this.maxCarvingDepth = 0;			
			this.invivoTestClassName = "--Not-Initialized-Yet--";
		}
		this.processFinalFieldsFlag = false;
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
		if (flatteingObject!=null){
			System.out.println("Name: "+field.getName()+", Object Type:"+flatteingObject.getClass().getName());
		}else{
			System.out.println("Name: "+field.getName()+", Object Type: Object (but it is null!!!)");			
		}	
		String currentFlattenedState = "";
//		if ((actualCarvingDepth > 0) && (field != null) && (! Modifier.isFinal(field.getModifiers()))){
		if ((actualCarvingDepth > 0) && (this.checkFieldProcessing(field))){
//		if (actualCarvingDepth > 0){
//			Class<?> clazz = field.getType();
			Object fieldValue = dumpFieldValue(field, flatteingObject);
			Class<?> clazz=null;
			if (fieldValue != null){
				clazz = fieldValue.getClass();
			}
			else{
				clazz = field.getType();
			}	
			
			if ((this.isComplexType(clazz)) && (fieldValue != null)) {
				String localFlattening = TOKEN_OBJECT_START;
				for (Field f : clazz.getDeclaredFields()) {					
					String tmp = this.flattenInteralState(f, fieldValue, actualCarvingDepth - 1);

					if (!tmp.isEmpty()){
						if (tmp.startsWith(TOKEN_OBJECT_START)){
							tmp = f.getName() + TOKEN_SEPARATOR_LEVEL_VALUE + tmp;
						}	
						localFlattening = localFlattening + TOKEN_FIELD_SEPARATOR + tmp;
					}
				}
				localFlattening = localFlattening.replaceFirst(TOKEN_FIELD_SEPARATOR, "");

				currentFlattenedState = field.getName() + TOKEN_SEPARATOR_LEVEL_VALUE + localFlattening + TOKEN_OBJECT_END;
			} else {
				if ((clazz.isArray()) && (fieldValue != null)){
					String localFlattening = "";					
					try {
						localFlattening = this.flattenInteralState(fieldValue, actualCarvingDepth-1);
					} catch (NoSuchFieldException | SecurityException e) {
						e.printStackTrace();
						localFlattening = null;
					}
					currentFlattenedState = field.getName() + TOKEN_SEPARATOR_LEVEL_VALUE + localFlattening;
				}else{
					currentFlattenedState = field.getName() + TOKEN_SEPARATOR_LEVEL_VALUE + fieldValue;
				}	
			}
		}
		return currentFlattenedState;
	}
	
//	private String flattenInteralState(Object flatteingObject, int actualCarvingDepth) throws IllegalArgumentException {
//		String currentFlattenedState = TOKEN_ARRAY_START;
//		if ((actualCarvingDepth > 0) && (flatteingObject != null)) {			
//			Object[] objects = IntStream.range(0, Array.getLength(flatteingObject)).mapToObj(i -> Array.get(flatteingObject, i)).toArray();
//
//			String localFlattening = "";
//			for (Object obj : objects) {
//				String tmp = "";
//				if (obj != null){
//					Class<?> clazz = obj.getClass();
//					for (Field fObj : clazz.getDeclaredFields()) {
//						tmp = this.flattenInteralState(fObj, obj, actualCarvingDepth - 1);
//						localFlattening = localFlattening + TOKEN_ELEMENT_SEPARATOR + tmp;
//					}
//				}
//			}
//			localFlattening = localFlattening.replaceFirst(TOKEN_ELEMENT_SEPARATOR, "");
//			currentFlattenedState = currentFlattenedState + localFlattening;
//		}
//		currentFlattenedState = currentFlattenedState + TOKEN_ARRAY_END;
//		return currentFlattenedState;
//	}
	
	private String flattenInteralState(Object flatteingObject, int actualCarvingDepth) throws IllegalArgumentException, NoSuchFieldException, SecurityException {
		String currentFlattenedState = TOKEN_ARRAY_START;
		if ((actualCarvingDepth > 0) && (flatteingObject != null)) {			

			String localFlattening = "";
			int length = Array.getLength(flatteingObject);
			for (int indexCounter = 0; indexCounter < length; indexCounter++) {
				Object obj = Array.get(flatteingObject, indexCounter);
				String tmp = "";
				if (obj != null){
					Class<?> clazz = obj.getClass();
//					Field fObj = clazz.getDeclaredField("value");
					this.processFinalFieldsFlag = true;
					for (Field fObj : clazz.getDeclaredFields()) {
						tmp = this.flattenInteralState(fObj, obj, actualCarvingDepth - 1);
						localFlattening = localFlattening + TOKEN_ELEMENT_SEPARATOR + tmp;
					}
					this.processFinalFieldsFlag = false;
				}				
			}
			localFlattening = localFlattening.replaceFirst(TOKEN_ELEMENT_SEPARATOR, "");
			currentFlattenedState = currentFlattenedState + localFlattening;
		}
		currentFlattenedState = currentFlattenedState + TOKEN_ARRAY_END;
		return currentFlattenedState;
	}

	private List<String> orderedFieldByName(Field[] fields){
		List<String> listOfFieldNames = new ArrayList<String>();
		Collections.sort(listOfFieldNames);
		return listOfFieldNames;
	}
	
	private boolean isComplexType(Class<?> c){
		boolean result = false;
		if ( c != null ){
			result = !((c.isPrimitive()) || (c.isEnum()) || (c.getTypeName().equalsIgnoreCase(JAVA_STRING)) || (c.isArray()));
		}	
		return result;
	}

	/**
	 * The processing of a field is subject to an implication between its final modifier and the flag that enables the processing of final attributes.
	 * More specifically:
	 * processIt := isFinal --> this.processFinalFieldsFlag
	 *    true		 false				false
	 *    true		 false				true
	 *    false		 true				false
	 *    true		 true				true
	 **/
	private boolean checkFieldProcessing(Field f){
		boolean isFinal = (f != null) && (Modifier.isFinal(f.getModifiers())); 

		/**
		 * processIt := isFinal --> this.processFinalFieldsFlag
		 */
		boolean processIt = (! isFinal) || this.processFinalFieldsFlag; 
		
		return processIt;
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
		boolean wasPublic = true;
		// if (Modifier.isPrivate(f.getModifiers())) {
		if (!Modifier.isPublic(f.getModifiers())) {
			wasPublic = false;
			f.setAccessible(true);
		}

		try {
			fieldValue = f.get(instace);
		} catch (NullPointerException | IllegalAccessException e) {
			e.printStackTrace();
			fieldValue = null;
		}

		if (!wasPublic) {
			f.setAccessible(false);
		}

		return fieldValue;
	}
	
}
