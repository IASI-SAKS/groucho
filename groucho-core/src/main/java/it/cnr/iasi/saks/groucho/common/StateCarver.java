package it.cnr.iasi.saks.groucho.common;

public interface StateCarver {

	public String carveAllFields() throws IllegalArgumentException, ClassNotFoundException;
	
	public void updateContext (Context c);
}
