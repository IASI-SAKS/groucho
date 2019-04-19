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
package it.cnr.iasi.saks.groucho.carvingStateTests.dummyValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import it.cnr.iasi.saks.groucho.carvingStateTests.RandomGenerator;

public class OtherDummyClassWithDummyValues {

	private List<OtherDummyClassPrimitiveTypesWithDummyValues> myList;
	private DummyClassWithDummyValues dc;
	private Vector<Integer> v;
	
	private Vector<String> words;

	private int mySimpleState;
	
	public OtherDummyClassWithDummyValues(DummyClassWithDummyValues dc){
		this.dc = dc;
		
		this.v = RandomGenerator.getInstance().nextIntegerVector();
		
		this.words = RandomGenerator.getInstance().nextStringVector();

		this.mySimpleState = RandomGenerator.getInstance().nextInt();
		
		this.myList = new ArrayList<OtherDummyClassPrimitiveTypesWithDummyValues>();
		int rndSize = RandomGenerator.getInstance().nextInt(10);
		for (int i = 0; i < rndSize; i++) {
			OtherDummyClassPrimitiveTypesWithDummyValues foo = new OtherDummyClassPrimitiveTypesWithDummyValues();
			this.myList.add(foo);
		}
	}

}
