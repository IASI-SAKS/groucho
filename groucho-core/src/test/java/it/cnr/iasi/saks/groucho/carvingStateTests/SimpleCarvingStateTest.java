package it.cnr.iasi.saks.groucho.carvingStateTests;

import org.junit.Test;

import it.cnr.iasi.saks.groucho.callback.AbstractGovernanceManager;
import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.common.StateCarver;

public class SimpleCarvingStateTest {
	private DummyClass dummyObject;
	
	private String instrumentedClassName;
	private String instrumentedMethodName;
	
	private String invivoTestClass;	
	private String invivoTest;
	
	public SimpleCarvingStateTest() {
		this.dummyObject = new DummyClass();
		
		this.instrumentedClassName =  this.dummyObject.getClass().getCanonicalName();
		this.instrumentedMethodName = "dummyMethodUnderInvivoSession";
		
		this.invivoTestClass = DummyInvivoTestClass.class.getCanonicalName();		
		this.invivoTest = "dummyInvivoTest";
	}

	@Test
	public void basicTest(){
		Context testingContext = new Context(this.dummyObject, this.instrumentedClassName, this.instrumentedMethodName, this.invivoTestClass, this.invivoTest);

		StateCarver carver = new StateCarver(testingContext);
		try {
			carver.carveAllFields();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void otherTest(){
		Context testingContext = new Context(this.dummyObject, this.instrumentedClassName, this.instrumentedMethodName, this.invivoTestClass, this.invivoTest);

		AbstractGovernanceManager gm = new TestGovernanceManager_DenyActivation();
		gm.runInvivoTestingSession(testingContext);

	}
}
