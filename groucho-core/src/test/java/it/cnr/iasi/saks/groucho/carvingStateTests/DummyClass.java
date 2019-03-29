package it.cnr.iasi.saks.groucho.carvingStateTests;

import java.util.Vector;

public class DummyClass {
	
	private int fieldInt;
	private boolean fieldBoolean;
	private String fieldString;
	private char fieldChar;
	private Object fieldObject;
	private OtherDummyClass dummy;
	
	public DummyClass(){
		this.fieldInt = -1;
		this.fieldBoolean = true;
		this.fieldString = "deafult";
		this.fieldChar = 'd';
		this.fieldObject = new Object();		

		this.dummy = new OtherDummyClass(this);
	}

	public int getFieldInt() {
		return fieldInt;
	}
	public void setFieldInt(int fieldInt) {
		this.fieldInt = fieldInt;
	}
	public boolean isFieldBoolean() {
		return fieldBoolean;
	}
	public void setFieldBoolean(boolean fieldBoolean) {
		this.fieldBoolean = fieldBoolean;
	}
	public String getFieldString() {
		return fieldString;
	}
	public void setFieldString(String fieldString) {
		this.fieldString = fieldString;
	}
	public char getFieldChar() {
		return fieldChar;
	}
	public void setFieldChar(char fieldChar) {
		this.fieldChar = fieldChar;
	}
	public Object getFieldObject() {
		return fieldObject;
	}
	public void setFieldObject(Object fieldObject) {
		this.fieldObject = fieldObject;
	}

	public void dummyMethodUnderInvivoSession() {
		System.out.println("this is the DummyMethod under an Invivo session");
	}

}
