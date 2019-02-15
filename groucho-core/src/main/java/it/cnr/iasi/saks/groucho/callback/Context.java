package it.cnr.iasi.saks.groucho.callback;

import java.util.ArrayList;
import java.util.List;

public class Context {
	
	private Object instrumentedObject;
	private String instrumentedClassName;
	private String instrumentedMethodName;
	
	private String invivoTestClass;
	private String invivoTest;
	
	private List<Object> otherReferencesInContext;
	
	public Context(Object instrumentedObject, String instrumentedClassName, String instrumentedMethodName, String invivoTestClass, String invivoTest) {
		super();
		this.instrumentedObject = instrumentedObject;
		this.instrumentedClassName = instrumentedClassName;
		this.instrumentedMethodName = instrumentedMethodName;
		this.invivoTestClass = invivoTestClass;
		this.invivoTest = invivoTest;
		
		this.otherReferencesInContext = new ArrayList<Object>();
	}

	public List<Object> getOtherReferencesInContext() {
		return otherReferencesInContext;
	}

	public void setOtherReferencesInContext(List<Object> otherReferencesInContext) {
		this.otherReferencesInContext = otherReferencesInContext;
	}

	public Object getInstrumentedObject() {
		return instrumentedObject;
	}

	public String getInstrumentedClassName() {
		return instrumentedClassName;
	}

	public String getInstrumentedMethodName() {
		return instrumentedMethodName;
	}

	public String getInvivoTestClass() {
		return invivoTestClass;
	}

	public String getInvivoTest() {
		return invivoTest;
	}


}
