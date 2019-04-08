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
package it.cnr.iasi.saks.groucho.carvingStateTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class OtherDummyClass {

	private List<OtherDummyClassPrimitiveTypes> myList;
	private DummyClass dc;
	private Vector<Integer> v;
	
	private Vector<String> words;

	private int mySimpleState;
	
	public OtherDummyClass(DummyClass dc){
		v = new Vector();
		v.add(new Integer(10));
		v.add(new Integer(20));
		v.add(new Integer(30));
		v.add(new Integer(40));
		v.add(new Integer(50));
		
		words = new Vector();
		words.add("Foo");
		words.add("Boo");

		this.dc = dc;
		
		this.mySimpleState = 999;
		
		this.myList = new ArrayList<OtherDummyClassPrimitiveTypes>();
		OtherDummyClassPrimitiveTypes foo = new OtherDummyClassPrimitiveTypes();
		for (int i = 0; i < 5; i++) {
			this.myList.add(foo);
		}
	}

}
