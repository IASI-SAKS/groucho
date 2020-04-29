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
package it.cnr.iasi.saks.groucho.lab.instrument.test;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import it.cnr.iasi.saks.groucho.lab.instrument.InvivoTestAnnotatorClassVisitor;
import it.cnr.iasi.saks.groucho.lab.instrument.test.utils.SimpleClass;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Ignore;

public class InvivoTestAnnotatorClassVisitorSimpleTest {

	public InvivoTestAnnotatorClassVisitorSimpleTest(){
		
	}
	
	@Test
	@Ignore	
	public void visitTest(){
		ClassWriter myLocalCW = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		InvivoTestAnnotatorClassVisitor cv = new InvivoTestAnnotatorClassVisitor(myLocalCW, SimpleClass.class.getCanonicalName());

		String sutInternalClassName = Type.getInternalName(SimpleClass.class);
		String sutSuperclassInternalClassName = Type.getInternalName(Object.class);

		cv.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,sutInternalClassName,null,sutSuperclassInternalClassName,null);

//		System.out.println(this.expectedResul + " --> " + result);
//		Assert.assertEquals(this.expectedResul, result);
	}

	@Test
	@Ignore
	public void visitMethodTest() throws NoSuchMethodException, SecurityException{
		ClassWriter myLocalCW = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		InvivoTestAnnotatorClassVisitor cv = new InvivoTestAnnotatorClassVisitor(myLocalCW, SimpleClass.class.getCanonicalName());

		String methodName = "dummyMethod";
		Method reflectiveMethod = SimpleClass.class.getMethod(methodName, int.class, Object.class);
		String methodDescriptor = Type.getMethodDescriptor(reflectiveMethod);
		cv.visitMethod(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);

//		System.out.println(this.expectedResul + " --> " + result);
//		Assert.assertEquals(this.expectedResul, result);
	}
	
	@Test
	public void transformationTest(){
		try {
			String sutCanonicalName = SimpleClass.class.getCanonicalName();
			String sutInternalName = Type.getInternalName(SimpleClass.class);
			
			ClassReader reader = new ClassReader(sutCanonicalName);
			ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
			
			InvivoTestAnnotatorClassVisitor annotatorVisitor = new InvivoTestAnnotatorClassVisitor(writer, sutInternalName);

			reader.accept(annotatorVisitor, 0);
			byte[] byteArrayToBeReturned  = annotatorVisitor.toByteArray();
		} catch (Throwable e) {
			Assert.fail(e.getMessage());
		}
		
	}
	
}
