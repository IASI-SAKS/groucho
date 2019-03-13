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
