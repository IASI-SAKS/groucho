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
package it.cnr.iasi.saks.groucho.carvingStateTests;


public class DummyClass {
	
	private int fieldInt;
	private boolean fieldBoolean;
	private String fieldString;
	private char fieldChar;
	private Object fieldObject;

	private OtherDummyClass dummy;

	private OtherDummyClassPrimitiveTypes otherDummy;
	
/*
 * This field should not be displayed as it is null
 */
	private Integer foo=null;

	public DummyClass(){
		this.fieldInt = -1;
		this.fieldBoolean = true;
		this.fieldString = "deafult";
		this.fieldChar = 'd';
		this.fieldObject = new Object();	
		
		this.foo = null;

		this.dummy = new OtherDummyClass(this);
		this.otherDummy = new OtherDummyClassPrimitiveTypes();
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
