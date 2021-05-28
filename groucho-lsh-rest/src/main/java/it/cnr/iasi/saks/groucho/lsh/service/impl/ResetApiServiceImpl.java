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
package it.cnr.iasi.saks.groucho.lsh.service.impl;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.StateObserverFactory;
import it.cnr.iasi.saks.groucho.lsh.StateObserverLSH;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
//import it.cnr.iasi.saks.groucho.lsh.factoryOLD.StateObserverFactoryOLD;
import it.cnr.iasi.saks.groucho.lsh.service.ResetApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
@Qualifier("reset")
@Slf4j
public class ResetApiServiceImpl implements ResetApiService {

    public ResponseEntity<Boolean> resetStateObserver() {
        // TODO impl instead
        // TODO if 400 error return httpstatus
        try {
    		StateObserverFactory soFactory = StateObserverFactory.getInstance();
    	    StateObserver stateObserver = soFactory.getStateObserver();
    	    StateObserverLSH stateObserverLSH = soFactory.getStateObserverLSH();

            stateObserver.resetStateObserver();
            stateObserverLSH.resetStateObserver();            
        } catch (LSHException ex) {
            log.error("ERROR: ", ex);
            // TODO 400 || 404 checkLSHUtil return boolean
            return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Boolean>(new Boolean(true), HttpStatus.OK);
    }
}
