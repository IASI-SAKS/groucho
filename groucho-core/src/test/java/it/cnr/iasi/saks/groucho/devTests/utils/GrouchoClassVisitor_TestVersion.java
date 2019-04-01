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
package it.cnr.iasi.saks.groucho.devTests.utils;

import org.objectweb.asm.ClassWriter;

import it.cnr.iasi.saks.groucho.instrument.GrouchoClassVisitor;

public class GrouchoClassVisitor_TestVersion extends GrouchoClassVisitor {

	private String pClassName;
	
	public GrouchoClassVisitor_TestVersion(ClassWriter cw, String pClassName) {
		super(cw, pClassName);
		this.pClassName = pClassName;
	}

	public String extractContructorSourceName(){
		return super.extractContructorSourceName();
	}	

	public boolean hasExplicitSuperclass(String classname){
		return super.hasExplicitSuperclass(classname);
	}	

	public boolean isImplementingRunnableSomehow(){			
	        this.retrieveInheritedInterfaceNames(this.pClassName);
			return super.isImplementingRunnableSomehow();		
	}
		
}
