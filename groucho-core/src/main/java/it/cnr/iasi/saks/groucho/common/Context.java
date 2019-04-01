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

import java.util.ArrayList;
import java.util.List;

public class Context {
	
	private Object instrumentedObject;
	private String instrumentedClassName;
	private String instrumentedMethodName;
	
	private String invivoTestClass;
	private String invivoTest;
	private boolean pauseOtherThreadsFlag;
	private int maxCarvingDepth;

	private List<Object> otherReferencesInContext;
	
	public Context(Object instrumentedObject, String instrumentedClassName, String instrumentedMethodName, String invivoTestClass, String invivoTest) {
		this(instrumentedObject, instrumentedClassName, instrumentedMethodName, invivoTestClass, invivoTest, true, 1);
	}

	public Context(Object instrumentedObject, String instrumentedClassName, String instrumentedMethodName, String invivoTestClass, String invivoTest, boolean pauseOtherThreads) {
		this(instrumentedObject, instrumentedClassName, instrumentedMethodName, invivoTestClass, invivoTest, pauseOtherThreads, 1);
	}

	public Context(Object instrumentedObject, String instrumentedClassName, String instrumentedMethodName, String invivoTestClass, String invivoTest, int maxCarving) {
		this(instrumentedObject, instrumentedClassName, instrumentedMethodName, invivoTestClass, invivoTest, true, maxCarving);
	}

	public Context(Object instrumentedObject, String instrumentedClassName, String instrumentedMethodName, String invivoTestClass, String invivoTest, boolean pauseOtherThreads, int maxCarvingDepth) {
		this.instrumentedObject = instrumentedObject;
		this.instrumentedClassName = instrumentedClassName;
		this.instrumentedMethodName = instrumentedMethodName;
		this.invivoTestClass = invivoTestClass;
		this.invivoTest = invivoTest;
		this.pauseOtherThreadsFlag = pauseOtherThreads;
		this.maxCarvingDepth = maxCarvingDepth;
		
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

	public boolean checkPauseForOtherThreads() {
		return pauseOtherThreadsFlag;
	}

	public int getMaxCarvingDepth() {
		return maxCarvingDepth;
	}

}
