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
