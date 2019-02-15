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
