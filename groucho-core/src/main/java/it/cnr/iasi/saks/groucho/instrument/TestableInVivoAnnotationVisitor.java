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
package it.cnr.iasi.saks.groucho.instrument;

import java.util.HashMap;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;

public class TestableInVivoAnnotationVisitor extends AnnotationVisitor{

	private HashMap<String, String> values;
	
	private CrochetMethodVisitor cmv;
	
	public TestableInVivoAnnotationVisitor(CrochetMethodVisitor cmv, AnnotationVisitor av) {
		super(Opcodes.ASM5, av);	
		
		this.values = new HashMap<String, String>();
		
		this.cmv = cmv;
	}
	
	@Override
	public void visit(String name, Object value) {
		this.values.put(name, value.toString());
		super.visit(name, value);
	}
	
//	@Override
//	public AnnotationVisitor visitArray(String name) {
//  			if ("fields".equals(name)) {
//  				return new AnnotationVisitor(Opcodes.ASM5, super.visitArray(name)) { 
//  							@Override
//  							public void visit(String name, Object value) {
//  								System.out.println("$$$$$$$$$$$$$$$$$ " + name + " " + value.toString());			                  
//  								super.visit(name, value);
//  							}
//  				};
//  			} else {
//  				return super.visitArray(name);
//  			}
//  		}
	
//	new AnnotationVisitor(Opcodes.ASM5,
//	        super.visitAnnotation(desc, visible)) {
//	}	
	
//	public AnnotationVisitor visitArray(String name, Object value) {
//		if ("fields".equals(name)) {
//	          return new AnnotationVisitor(Opcodes.ASM5, super.visitArray(name)) { 
//	        	  public void visit(String name, Object value) {
//	        		  parameterIndexes.add((String) value);
//	        		  cmv.
//	        		  super.visit(name, value);
//	            }
//	          };
//	        } else {
//	          return super.visitArray(name);
//	        }
//	 }
	
		public String getAnnotationValues(String name) throws IllegalStateException{
			String value = this.values.get(name);
			if (value == null){
				throw new IllegalStateException("Attributes for " + TestableInVivo.class.getName() + "cannot be null");
			} 
			return value;
		}
	
}
