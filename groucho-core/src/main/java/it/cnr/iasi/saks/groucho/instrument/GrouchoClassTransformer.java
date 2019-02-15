package it.cnr.iasi.saks.groucho.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class GrouchoClassTransformer implements ClassFileTransformer {

	private boolean isDisabled(String className) {
		return (this.isLocallyIgnored(className) || this.isClassNameIgnoredByCrochet(className));
	}

	private boolean isLocallyIgnored(String className) {
		boolean exitus = className.startsWith("java/");
		exitus = exitus || className.startsWith("sun/");
		exitus = exitus || className.startsWith("it/cnr/iasi/saks/groucho/");
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
