package it.cnr.iasi.saks.groucho.carvingStateTests;

import java.util.Vector;

public class OtherDummyClass {
	private Vector<Integer> v;
	private DummyClass dc;
	
	public OtherDummyClass(DummyClass dc){
		v = new Vector();
		v.add(new Integer(10));
		v.add(new Integer(20));
		v.add(new Integer(30));
		v.add(new Integer(40));
		v.add(new Integer(50));
		
		this.dc = dc;
	}

}
