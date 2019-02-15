package it.cnr.iasi.saks.groucho.instrument;

public class Instrumenter {

	private String classe;
	private String metodo;

	public Instrumenter(){
		this.classe = "valore della variabile";
		this.metodo = "valore dell'altra variabile"; 
	}
	
	public void fooOne(){
		String nomeClasse;
		String nomeMetodo;
		
		nomeClasse = this.classe;
		nomeMetodo = this.metodo;
		
		PrintMessageMethodVisitor.message(nomeClasse, nomeMetodo);		
	}
	
	public void fooTwo(){
		PrintMessageMethodVisitor.message(this, this.classe, this.metodo);		
	}

	public static void main (String agrs[]){
		System.out.println("Currently this is empty!!");
		
		Instrumenter inst = new Instrumenter();
		inst.classe = "classe";
		inst.metodo = "metodo";  
		inst.fooOne();
		inst.fooTwo();
	}
}
