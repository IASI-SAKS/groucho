package it.cnr.iasi.saks.groucho.devTests.utils;

import org.objectweb.asm.ClassWriter;

import it.cnr.iasi.saks.groucho.instrument.GrouchoClassVisitor;

public class GrouchoClassVisitor_TestVersion extends GrouchoClassVisitor {

	public GrouchoClassVisitor_TestVersion(ClassWriter cw, String pClassName) {
		super(cw, pClassName);
	}

	public String extractContructorName(){
		return super.extractContructorName();
	}	
}
