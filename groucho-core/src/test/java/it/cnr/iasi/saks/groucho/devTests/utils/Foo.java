package it.cnr.iasi.saks.groucho.devTests.utils;

import it.cnr.iasi.saks.groucho.instrument.ThreadHarnessCallbackInvoker;

public class Foo {

	public Foo(){
		ThreadHarnessCallbackInvoker.invokeCallback_checkInTheConstructors();
	}
	
	public void method(){
		ThreadHarnessCallbackInvoker.invokeCallback_checkInTheConstructors();		
	}
	
}
