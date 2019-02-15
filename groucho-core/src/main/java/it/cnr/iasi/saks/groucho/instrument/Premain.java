package it.cnr.iasi.saks.groucho.instrument;

import java.lang.instrument.Instrumentation;

public class Premain {

    public static Instrumentation instrumenter;
    public static boolean isConfigured = false;

    /**
     * Valid arguments:
     * 
     */
    public static void premain(String args, Instrumentation inst) {
    	System.out.println("Starting the agent");
    	
    	inst.addTransformer(new GrouchoClassTransformer());
        Premain.instrumenter = inst;
		
        Premain.isConfigured = true;

    }
}
