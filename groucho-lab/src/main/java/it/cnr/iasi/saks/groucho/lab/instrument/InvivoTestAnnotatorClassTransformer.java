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
package it.cnr.iasi.saks.groucho.lab.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Iterator;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import it.cnr.iasi.saks.groucho.config.PropertyUtil;

public class InvivoTestAnnotatorClassTransformer implements ClassFileTransformer {
	
	private boolean isDisabled(String className) {
		return (this.isClassNameIgnoredByCrochet(className) || this.isLocallyIgnored(className));
	}

	private boolean isLocallyIgnored(String className) {
		boolean exitus = className.startsWith("java/");
		exitus = exitus || className.startsWith("sun/");
		exitus = exitus || className.startsWith("it/cnr/iasi/saks/groucho/");
		exitus = exitus || className.startsWith("org/junit/");
		exitus = exitus || className.startsWith("org/apache/maven/");
		exitus = exitus || className.startsWith("org/eclipse/jdt/internal/junit/");
		
		if (!exitus) {
			List<String> excludeClassesList = PropertyUtil.getInstance().getClassesToExcludeDuringTransformation();
			for (Iterator<String> iterator = excludeClassesList.iterator(); iterator.hasNext() && !exitus;) {
				String classToExclude = (String) iterator.next();
				exitus = exitus || className.startsWith(classToExclude);

			}
		}
		return exitus;
	}

	private boolean isClassNameIgnoredByCrochet(String className) {
		 boolean exitus = net.jonbell.crij.instrument.Instrumenter.isIgnoredClass(className);
		 exitus = exitus || net.jonbell.crij.instrument.Instrumenter.isIgnoredClassWithDummyMethods(className);
		 return exitus;
	}

	/*
	 * (non-Javadoc) It is invoked for every class loaded into the JVM. It
	 * provides the byte array of the class as input to the method, which then
	 * returns the modified byte array. If the class transformer decides not to
	 * modify the bytes of the specific class, then it can return null.
	 * 
	 * @see java.lang.instrument.ClassFileTransformer#transform(java.lang.
	 * ClassLoader, java.lang.String, java.lang.Class,
	 * java.security.ProtectionDomain, byte[])
	 */
	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		if (this.isDisabled(className)) {
			return null;
		}

		System.out.println("Currently applying the tranformation on: " + className);
		
		byte[] byteArrayToBeReturned = null;
		
		try {
			ClassReader reader = new ClassReader(classfileBuffer);
			ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
			
			InvivoTestAnnotatorClassVisitor annotatorVisitor = new InvivoTestAnnotatorClassVisitor(writer, className);

			reader.accept(annotatorVisitor, 0);
			byteArrayToBeReturned  = annotatorVisitor.toByteArray();
		} catch (Throwable e) {
			System.err.println("Something bad happened ...");
			e.printStackTrace();
		}
		return byteArrayToBeReturned;
	}
	
}
