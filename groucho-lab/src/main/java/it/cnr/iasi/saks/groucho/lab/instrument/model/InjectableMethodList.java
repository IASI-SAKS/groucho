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

import java.util.ArrayList;
import java.util.List;

public class InjectableMethodList {

	List<AnnotatedMethodModel> lst;
	
	public InjectableMethodList(){
		this(new ArrayList<AnnotatedMethodModel>());
	}

	public InjectableMethodList(ArrayList<AnnotatedMethodModel> lst){
		this.lst = lst;
	}

	public List<AnnotatedMethodModel> getLst() {
		return lst;
	}

	public void setLst(List<AnnotatedMethodModel> lst) {
		this.lst = lst;
	}
	
	public void addAnnotatedMethod(AnnotatedMethodModel m) {
		if (this.lst == null) {
			this.lst = new ArrayList<AnnotatedMethodModel>();
		}
		this.lst.add(m);
	}
}
