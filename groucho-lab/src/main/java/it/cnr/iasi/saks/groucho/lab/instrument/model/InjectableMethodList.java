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
