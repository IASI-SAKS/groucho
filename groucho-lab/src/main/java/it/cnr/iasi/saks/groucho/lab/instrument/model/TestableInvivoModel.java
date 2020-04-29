package it.cnr.iasi.saks.groucho.lab.instrument.model;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;

public class TestableInvivoModel{
	private String invivoTestClass;
	private String invivoTest;
	private int carvingDepth;
	private boolean pauseOtherThreads;
	
	public TestableInvivoModel() {
		this("", "", 1, false);
	}

	public TestableInvivoModel(String invivoTestClass, String invivoTest) {
		this(invivoTestClass, invivoTest, 1, false);
	}

	public TestableInvivoModel(String invivoTestClass, String invivoTest, int carvingDepth, boolean pauseOtherThreads) {
		this.invivoTestClass = invivoTestClass;
		this.invivoTest = invivoTest;
		this.carvingDepth = carvingDepth;
		this.pauseOtherThreads = pauseOtherThreads;
	}

	public String getInvivoTestClass() {
		return invivoTestClass;
	}

	public String getInvivoTest() {
		return invivoTest;
	}

	public int getCarvingDepth() {
		return carvingDepth;
	}

	public boolean isPauseOtherThreads() {
		return pauseOtherThreads;
	}
	
//	@Override
//	public String toString(){
//		String str = "@"+TestableInVivo.class.getCanonicalName()+"{ invivoTestClass="+this.invivoTestClass+";invivoTest="+this.invivoTest+";carvingDepth="+this.carvingDepth+";pauseOtherThreads="+this.pauseOtherThreads+"}";
//		return str;
//	}

}
