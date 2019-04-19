package it.cnr.iasi.saks.groucho.carvingStateTests.dummyValues;

import java.util.Random;
import java.util.Vector;

import it.cnr.iasi.saks.groucho.carvingStateTests.ThisIsEnum;

public class RandomGenerator extends Random {

	protected static RandomGenerator INSTANCE = null;
	
	private static final String CHARS_POOL_SET = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	protected RandomGenerator(){
	} 
	
	public synchronized static RandomGenerator getInstance(){
		if (INSTANCE == null){
			INSTANCE = new RandomGenerator();
		}
		
		return INSTANCE;
	}
	
	public Vector<Integer> nextIntegerVector(){
		Vector<Integer> v = new Vector<Integer>();
		int size = this.nextInt(20);
		for (int i = 0; i < size; i++) {
			v.add(this.nextInt());
		}
		return v;		
	}
	
	public Vector<String> nextStringVector(){
		Vector<String> v = new Vector<String>();
		int size = this.nextInt(20);
		for (int i = 0; i < size; i++) {
			v.add(this.nextString());
		}
		return v;		
	}
	
	public String nextString(){
		int rndLength = this.nextInt(20);
		return this.nextString(rndLength);
	}
	
	public String nextString(int length){
		StringBuffer randStr = new StringBuffer(length);
		for( int i = 0; i < length; i++ ){
			char c = this.nextChar();
			randStr.append(c);
		}	
		return randStr.toString();
	}

	public char nextChar() {
		int rndIndex = this.nextInt(CHARS_POOL_SET.length());
		char c = CHARS_POOL_SET.charAt(rndIndex);
		return c;
	}
	
	public ThisIsEnum nextEnumItem() {
		ThisIsEnum[] values = ThisIsEnum.values();
		int rndIndex = this.nextInt(values.length);
		return values[rndIndex];
	}
}
