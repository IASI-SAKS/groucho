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
