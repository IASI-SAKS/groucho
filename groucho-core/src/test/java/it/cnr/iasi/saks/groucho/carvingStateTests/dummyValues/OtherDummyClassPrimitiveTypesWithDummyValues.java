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

import it.cnr.iasi.saks.groucho.carvingStateTests.ThisIsEnum;

public class OtherDummyClassPrimitiveTypesWithDummyValues {
	private int fieldIntOther;
	private boolean fieldBooleanOther;
	private String fieldStringOther;
	private char fieldCharOther;
	private ThisIsEnum fieldEnum;
	
	public OtherDummyClassPrimitiveTypesWithDummyValues(){
		this.fieldIntOther=RandomGenerator.getInstance().nextInt();
		this.fieldBooleanOther=RandomGenerator.getInstance().nextBoolean();
		this.fieldStringOther=RandomGenerator.getInstance().nextString();
		this.fieldCharOther=RandomGenerator.getInstance().nextChar();
		this.fieldEnum=RandomGenerator.getInstance().nextEnumItem();
	}

}
