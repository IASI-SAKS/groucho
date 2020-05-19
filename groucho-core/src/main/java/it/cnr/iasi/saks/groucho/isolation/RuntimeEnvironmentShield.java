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
import java.util.Set;

import it.cnr.iasi.saks.groucho.common.Context;
import net.jonbell.crij.runtime.CRIJInstrumented;
import net.jonbell.crij.runtime.CheckpointRollbackAgent;
import net.jonbell.crij.runtime.wrapper.ArrayWrapper;
import net.jonbell.crij.runtime.wrapper.ObjectWrapper;

public class RuntimeEnvironmentShield{
	
	private static CheckpointRollbackAgent CROCHET_AGENT;
	private static Set<WeakReference<CRIJInstrumented>> COLLECTED_ROOT_OBJECTS = new HashSet<>();
	
	public RuntimeEnvironmentShield(){
		
	} 
	
	public void applyCheckpointOnContext(Context context) {
		if (context != null){
			int version = CROCHET_AGENT.getNewVersionForCheckpoint();
			
			this.performCheckpoint(context.getInstrumentedObject(), version);
						
			for (Object objectInContext : context.getOtherReferencesInContext()) {
				this.performCheckpoint(objectInContext, version);
			}
		}
	}

	public void applyRollbackOnContext(Context context) {		
		if (context != null){
			int version = CROCHET_AGENT.getNewVersionForRollback();
			
			this.performRollback(context.getInstrumentedObject(), version);

			for (Object objectInContext : context.getOtherReferencesInContext()) {
				this.performRollback(objectInContext, version);
			}
			COLLECTED_ROOT_OBJECTS.clear();
		}
	}

	private void performCheckpoint(Object subject, int version) {
		try {
			CRIJInstrumented obj = (CRIJInstrumented)subject; 
			obj.$$crijCheckpoint(version);
			COLLECTED_ROOT_OBJECTS.add(new WeakReference<CRIJInstrumented>(obj));
		} catch (java.lang.ClassCastException e) {
			System.out.println("Crochet Wrapper needed!!");
			
			if (subject.getClass().isArray()) {
				ArrayWrapper.propagateCheckpoint(subject, version);					
			}else {
				ObjectWrapper.propagateCheckpoint(subject, version);					
			}				
		}
	}

	private void performRollback(Object subject, int version) {
		try {
			CRIJInstrumented obj = (CRIJInstrumented)subject; 
			obj.$$crijRollback(version);
		} catch (java.lang.ClassCastException e) {
			System.out.println("Crochet Wrapper needed!!");
			
			if (subject.getClass().isArray()) {
				ArrayWrapper.propagateRollback(subject, version);					
			}else {
				ObjectWrapper.propagateRollback(subject, version);					
			}				
		}
	}

}
