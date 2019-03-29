package it.cnr.iasi.saks.groucho.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.objectweb.asm.ClassReader;

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
	
	//Access all private fields of the class.
	public void carveAllFields() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
			Class<?> clazz = Class.forName(this.invivoTestClassName, false, ClassLoader.getSystemClassLoader()); 
            Field[] fields = clazz.getDeclaredFields();
            
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
     	    
            System.out.println("Carved State: "+ carvedState);
	}
		    
/**
* Recursive-case
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
*/
	private String flattenInteralState(Field field, Object flatteingObject, int actualCarvingDepth) throws IllegalArgumentException, IllegalAccessException {
		String currentFlattenedState = "";
		if (actualCarvingDepth > 0) {
			Class<?> clazz = field.getType();
			if (this.isComplexType(clazz)) {
				String localFlattening = TOKEN_OBJECT_START;
				for (Field f : clazz.getDeclaredFields()) {

					Object fieldValue = dumpFieldValue(f, flatteingObject);
					String tmp = this.flattenInteralState(f, fieldValue, actualCarvingDepth - 1);

					tmp = f.getName() + TOKEN_SEPARATOR_LAVEL_VALUE + tmp;
					localFlattening = localFlattening + TOKEN_FIELD_SEPARATOR + tmp;
				}
				localFlattening = localFlattening.replaceFirst(TOKEN_FIELD_SEPARATOR, "");

				currentFlattenedState = field.getName() + TOKEN_SEPARATOR_LAVEL_VALUE + localFlattening + TOKEN_OBJECT_END; 
			} else {
				Object fieldValue = dumpFieldValue(field, flatteingObject);
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
	
	private Object dumpFieldValue(Field f, Object instace) throws IllegalArgumentException, IllegalAccessException{
		Object fieldValue = null; 
		boolean wasPrivate = false;
        if (Modifier.isPrivate(f.getModifiers())) {
        	wasPrivate = true;
     	    f.setAccessible(true);
        }

        fieldValue = f.get(instace);

        if (wasPrivate) {
     	    f.setAccessible(false);
        }
        
        return fieldValue; 
	}
	
}
