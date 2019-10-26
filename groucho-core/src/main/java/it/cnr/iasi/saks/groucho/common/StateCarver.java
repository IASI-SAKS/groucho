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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class StateCarver {

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

	public StateCarver(Context context){
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
		String carvedState = "";
		if (this.maxCarvingDepth > 0) {
			carvedState = this.flattenInteralState(this.instrumentedObject, this.maxCarvingDepth);
		} else {
			carvedState = TOKEN_OBJECT_START + TOKEN_OBJECT_END;
		}

		return carvedState;
	}
		    
/**
* Recursive-case
 *
 * about IllegalAccessException @see{@link it.cnr.iasi.saks.groucho.common.dumpFieldValue}
 *
 * @throws IllegalArgumentException 
*/
	private String flattenInteralState(Object flatteingObject, int actualCarvingDepth) throws IllegalArgumentException {
		Field[] fields = null;

		if (flatteingObject != null) {
			System.out.println("Object Type:" + flatteingObject.getClass().getName());
			fields = flatteingObject.getClass().getDeclaredFields();

			/**
			 * TODO Double check this implementation actually works
			 */
			Field[] fieldsOrderedByName = this.orderedFieldByName(fields);
			fields = fieldsOrderedByName;

		} else {
			System.out.println("Object Type: it is null!!!");
		}
		String currentFlattenedState = "";
		if (this.isSpecialComplexType(flatteingObject)) {
			currentFlattenedState = flatteingObject.toString();
		} else {
			if (actualCarvingDepth > 0) {
				currentFlattenedState = TOKEN_OBJECT_START;
				for (Field field : fields) {
					if (this.checkFieldProcessing(field)) {
						Class<?> fieldType = field.getType();
						System.out.println("Processing Field: " + field.getName() + " Object Type:"
								+ fieldType.getCanonicalName());
						Object fieldValue = dumpFieldValue(field, flatteingObject);
						if (fieldValue != null) {
							Class<?> instanceType = fieldValue.getClass();
							if (this.isComplexType(fieldType)) {
								String localFlattening = flattenInteralState(fieldValue, actualCarvingDepth - 1);
								if (localFlattening.isEmpty()) {
									localFlattening = TOKEN_ARRAY_START + TOKEN_ARRAY_END;
								}
								currentFlattenedState = currentFlattenedState + TOKEN_FIELD_SEPARATOR + field.getName()
										+ TOKEN_SEPARATOR_LEVEL_VALUE + localFlattening;
							} else {
								if (fieldType.isArray()) {
									String localFlattening = this.processArrays(fieldValue, actualCarvingDepth);
									currentFlattenedState = currentFlattenedState + TOKEN_FIELD_SEPARATOR
											+ field.getName() + TOKEN_SEPARATOR_LEVEL_VALUE + localFlattening;
								} else {
									currentFlattenedState = currentFlattenedState + TOKEN_FIELD_SEPARATOR
											+ field.getName() + TOKEN_SEPARATOR_LEVEL_VALUE + fieldValue;
								}
							}
						}
					}
				}
				currentFlattenedState = currentFlattenedState.replaceFirst(TOKEN_FIELD_SEPARATOR, "")
						+ TOKEN_OBJECT_END;
			}
		}

		return currentFlattenedState;
	}

	private String processArrays(Object flatteingObject, int actualCarvingDepth) {
		String currentFlattenedState = TOKEN_ARRAY_START;
		if ((actualCarvingDepth > 0) && (flatteingObject != null)) {

			String localFlattening = "";
			int length = Array.getLength(flatteingObject);
			for (int indexCounter = 0; indexCounter < length; indexCounter++) {
				Object obj = Array.get(flatteingObject, indexCounter);
				String tmp = "";
				if (obj != null) {
					Class<?> clazz = obj.getClass();
//					this.processFinalFieldsFlag = true;
					tmp = this.flattenInteralState(obj, actualCarvingDepth - 1);
					localFlattening = localFlattening + TOKEN_ELEMENT_SEPARATOR + tmp;
//					this.processFinalFieldsFlag = false;
				}
			}
			localFlattening = localFlattening.replaceFirst(TOKEN_ELEMENT_SEPARATOR, "");
			currentFlattenedState = currentFlattenedState + localFlattening;
		}
		currentFlattenedState = currentFlattenedState + TOKEN_ARRAY_END;
		return currentFlattenedState;
	}

	private Field[] orderedFieldByName(Field[] fields){
		ArrayList<Field> orderedFieldsList = new ArrayList<Field>();
		HashMap<String, Integer> fieldNamesMap = new HashMap<String, Integer>();
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			fieldNamesMap.put(name, i);
		}
		
		List<String> listOfFieldNames = new ArrayList<String>(fieldNamesMap.keySet());
		Collections.sort(listOfFieldNames);
	
		for (Iterator<String> iterator = listOfFieldNames.iterator(); iterator.hasNext();) {
			String fieldName = iterator.next();
			int originalIndex = fieldNamesMap.get(fieldName); 
			Field f = fields[originalIndex];
			
			orderedFieldsList.add(f);
		}
//		return listOfFieldNames;
		Field[] orderedFields = orderedFieldsList.toArray(new Field[orderedFieldsList.size()]);
		return orderedFields;
	}
	
	private boolean isComplexType(Class<?> c){
		boolean result = false;
		if ( c != null ){
			result = !((c.isPrimitive()) || (c.isEnum()) || (c.isArray()));
		}	
		return result;
	}

	private boolean isSpecialComplexType(Object o){
		boolean result = (o instanceof String) || (o instanceof Number);
		
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
