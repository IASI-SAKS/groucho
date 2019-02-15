package it.cnr.iasi.saks.groucho.isolation;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.cnr.iasi.saks.groucho.callback.Context;
import net.jonbell.crij.runtime.CRIJInstrumented;
import net.jonbell.crij.runtime.CheckpointRollbackAgent;

public class RuntimeEnvironmentShield {
	
	private static CheckpointRollbackAgent CROCHET_AGENT;
	public static Set<WeakReference<CRIJInstrumented>> COLLECTED_ROOT_OBJECTS = new HashSet<>();
	
	public RuntimeEnvironmentShield(){
		
	} 
	
	public void applyCheckpointOnContext(Context context) {
		if (context != null){
			int version = CROCHET_AGENT.getNewVersionForCheckpoint();
			
			CRIJInstrumented obj = (CRIJInstrumented)context.getInstrumentedObject(); 
			obj.$$crijCheckpoint(version);
			COLLECTED_ROOT_OBJECTS.add(new WeakReference<CRIJInstrumented>(obj));
						
			for (Object objectInContext : context.getOtherReferencesInContext()) {
				CRIJInstrumented item = (CRIJInstrumented)objectInContext; 
				item.$$crijCheckpoint(version);
				COLLECTED_ROOT_OBJECTS.add(new WeakReference<CRIJInstrumented>(item));
			}
		}
	}

	public void applyRollbackOnContext(Context context) {		
		if (context != null){
			int version = CROCHET_AGENT.getNewVersionForRollback();
			
			CRIJInstrumented obj = (CRIJInstrumented)context.getInstrumentedObject(); 
			obj.$$crijRollback(version);
			
			for (Object objectInContext : context.getOtherReferencesInContext()) {
				CRIJInstrumented item = (CRIJInstrumented)objectInContext; 
				item.$$crijRollback(version);
			}
			COLLECTED_ROOT_OBJECTS.clear();
		}
	}


}
