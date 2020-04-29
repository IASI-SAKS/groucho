package it.cnr.iasi.saks.groucho.lab.instrument.model;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;

public class AnnotatedMethodModel {

	private String className;
	private String methodName;
	private TestableInvivoModel annotation;
	
	public AnnotatedMethodModel(){
		this("", "", null);
	}

	public AnnotatedMethodModel(String className, String methodName){
		this(className, methodName, null);
	}
	
	public AnnotatedMethodModel(String className, String methodName, TestableInvivoModel annotation){
		this.className = className;
		this.methodName = methodName;
		this.annotation = annotation;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public TestableInvivoModel getAnnotation() {
		return annotation;
	}

	public void setAnnotation(TestableInvivoModel annotation) {
		this.annotation = annotation;
	}
	
}
