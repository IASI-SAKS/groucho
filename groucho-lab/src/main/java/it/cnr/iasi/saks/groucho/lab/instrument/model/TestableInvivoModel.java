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
package it.cnr.iasi.saks.groucho.lab.instrument.model;

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
