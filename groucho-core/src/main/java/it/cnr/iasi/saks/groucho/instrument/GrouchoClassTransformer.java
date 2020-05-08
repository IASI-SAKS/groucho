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

import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class GrouchoClassTransformer extends AbstractClassTranformer {
	
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
			
			GrouchoClassVisitor grouchoVisitor = new GrouchoClassVisitor(writer, className);

			reader.accept(grouchoVisitor, 0);
			byteArrayToBeReturned  = grouchoVisitor.toByteArray();
		} catch (Throwable e) {
			System.err.println("Something bad happened ...");
			e.printStackTrace();
		}
		return byteArrayToBeReturned;
	}
	
}
