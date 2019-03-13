package it.cnr.iasi.saks.groucho.callback;

public interface ThreadHarness {

	public void enableEnactmentInvivoTestingSession () throws InterruptedException;

	public void incThreads();

	public void decThreads();
	
}
