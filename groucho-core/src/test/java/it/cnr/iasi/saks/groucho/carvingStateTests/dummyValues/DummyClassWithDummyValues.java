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
package it.cnr.iasi.saks.groucho.carvingStateTests.dummyValues;

import java.util.Random;

public class DummyClassWithDummyValues {
	
	private int fieldInt;
	private boolean fieldBoolean;
	private String fieldString;
	private char fieldChar;
	private Object fieldObject;

	private OtherDummyClassWithDummyValues dummy;

	private OtherDummyClassPrimitiveTypesWithDummyValues otherDummy;
		
/*
 * This field should not be displayed as it is null
 */
	private Integer foo=null;

	public DummyClassWithDummyValues(){
		this.dummyUpdateOfFieldInt();
		
		this.fieldBoolean = RandomGenerator.getInstance().nextBoolean();
		this.fieldString = RandomGenerator.getInstance().nextString();
		this.fieldChar = RandomGenerator.getInstance().nextChar();
		
		this.fieldObject = new Object();	
		
		if (RandomGenerator.getInstance().nextBoolean()){ 
			this.foo = null;
		}else{
			this.foo = new Integer(RandomGenerator.getInstance().nextInt());
		}	

		switch (RandomGenerator.getInstance().nextInt(5)) {
		case 0:
			this.dummy = new OtherDummyClassWithDummyValues(null);			
			break;

		case 1:
		case 2:
			DummyClassWithDummyValues d = new DummyClassWithDummyValues();
			this.dummy = new OtherDummyClassWithDummyValues(d);						
			break;
		default:
			this.dummy = new OtherDummyClassWithDummyValues(this);
			break;
		}

		if (RandomGenerator.getInstance().nextInt(10) < 3){ 
			this.otherDummy = null;
		}else{
			this.otherDummy = new OtherDummyClassPrimitiveTypesWithDummyValues();			
		}	
		
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

	public void dummyUpdateOfFieldInt() {
		this.fieldInt = RandomGenerator.getInstance().nextInt(10);
	}

	public void dummyMethodWithNoSoCommonPath() {
		if (this.fieldInt == 5){
			System.out.println("This is not so common statement");
		}else{
			System.out.println("This is a very common statement");
		}	
	}
	
}
