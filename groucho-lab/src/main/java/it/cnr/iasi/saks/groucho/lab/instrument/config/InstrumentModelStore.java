package it.cnr.iasi.saks.groucho.lab.instrument.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import it.cnr.iasi.saks.groucho.config.PropertyUtil;
import it.cnr.iasi.saks.groucho.lab.instrument.model.AnnotatedMethodModel;
import it.cnr.iasi.saks.groucho.lab.instrument.model.InjectableMethodList;

public class InstrumentModelStore {
 
	protected static InstrumentModelStore INSTANCE = null;

	private static String LAB_INSTRUMENT_MODEL_JSON_DEFAULT_FILE = "/tmp/model.json"; 
			
	private InjectableMethodList list;
	
    protected InstrumentModelStore() {
    		String fileName = PropertyUtil.getInstance().getProperty(PropertyUtil.LAB_INSTRUMENT_MODEL_JSON_FILE_LABEL, LAB_INSTRUMENT_MODEL_JSON_DEFAULT_FILE);
    		FileInputStream stream;
			try {
				stream = new FileInputStream(fileName);
				list = InstrumentModelJSONMapper.loadFromJSON(stream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public synchronized static InstrumentModelStore getInstance(){
		if ( INSTANCE == null) {
			INSTANCE = new InstrumentModelStore();

		}
		return INSTANCE;
	}
	
	public boolean checkMethodInstrumentable(String classname, String methodName){
		AnnotatedMethodModel annotatedMethod = this.retreiveMethodInstrumentable(classname, methodName);
		return annotatedMethod!=null;
	}

	public AnnotatedMethodModel retreiveMethodInstrumentable(String className, String methodName){
		boolean found = false;
		AnnotatedMethodModel annotatedMethod = null;
		
		for (Iterator<AnnotatedMethodModel> iterator = this.list.getLst().iterator(); (iterator.hasNext() && !found);) {
			annotatedMethod = (AnnotatedMethodModel) iterator.next();
			String alteratedClassName = className.replaceAll("/", ".");
			String alteratedmethodName = methodName.replaceAll("/", ".");

			boolean chk1 = annotatedMethod.getClassName().equals(className) && annotatedMethod.getMethodName().equals(methodName);
			boolean chk2 = annotatedMethod.getClassName().equals(alteratedClassName) && annotatedMethod.getMethodName().equals(alteratedmethodName);
			found = chk1 || chk2;
		}
		
		if (!found) {
			annotatedMethod = null;
		}
		
		return annotatedMethod;
	}

}
