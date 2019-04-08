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

public class OtherDummyClassPrimitiveTypes {
	private int fieldIntOther;
	private boolean fieldBooleanOther;
	private String fieldStringOther;
	private char fieldCharOther;
	private ThisIsEnum fieldEnum;
	
	public OtherDummyClassPrimitiveTypes(){
		this.fieldIntOther=88;
		this.fieldBooleanOther=false;
		this.fieldStringOther="thisIsFoo";
		this.fieldCharOther='c';
		this.fieldEnum=ThisIsEnum.three;
	}

}
