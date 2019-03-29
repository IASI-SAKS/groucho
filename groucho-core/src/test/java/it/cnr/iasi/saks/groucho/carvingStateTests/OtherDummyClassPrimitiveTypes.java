package it.cnr.iasi.saks.groucho.carvingStateTests;

import java.util.Vector;

public class OtherDummyClassPrimitiveTypes {
	private Vector v;
	private DummyClass dc;
	
	public OtherDummyClassPrimitiveTypes(DummyClass dc){
		v = new Vector();
		v.add(10);
		v.add(20);
		v.add(30);
		v.add(40);
		
		this.dc = dc;
	}

}
