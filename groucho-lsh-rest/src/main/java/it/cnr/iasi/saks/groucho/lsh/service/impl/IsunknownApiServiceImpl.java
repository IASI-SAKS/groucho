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
import it.cnr.iasi.saks.groucho.lsh.ConcurrentStateObserverFactory;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.rest.api.impl.IsunknownApiController;
//import it.cnr.iasi.saks.groucho.lsh.factoryOLD.StateObserverFactoryOLD;
import it.cnr.iasi.saks.groucho.lsh.service.IsunknownApiService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import it.cnr.iasi.saks.groucho.lsh.util.StateObserverLSH_InsideUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
//@Qualifier("isunknown")
@Qualifier("IsUnknownApiService")
public class IsunknownApiServiceImpl implements IsunknownApiService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public ResponseEntity<Boolean> isStateUnknown(String body) {
		// TODO impl instead
        // TODO if 400 error return httpstatus4
        boolean response;
        try {
//    		StateObserverFactory soFactory = StateObserverFactory.getInstance();
    		ConcurrentStateObserverFactory soFactory = ConcurrentStateObserverFactory.getInstance();
    	    StateObserver stateObserver = soFactory.getStateObserver();

            response = stateObserver.isStateUnknown(body);
        } catch (LSHException ex) {
            logger.error("ERROR: ", ex);
            // TODO 400 || 404 checkLSHUtil return boolean
            return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Boolean>(new Boolean(response), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> isStateUnknownLSH(String stateStringLSH) {
		// TODO impl instead
        // TODO if 400 error return httpstatus4
        boolean response;
        try {
//    		StateObserverFactory soFactory = StateObserverFactory.getInstance();
    		ConcurrentStateObserverFactory soFactory = ConcurrentStateObserverFactory.getInstance();
    	    StateObserverLSH stateObserverLSH = soFactory.getStateObserverLSH();

            response = stateObserverLSH.isStateUnknown(stateStringLSH);
        } catch (LSHException ex) {
            logger.error("ERROR: ", ex);
            // TODO 400 || 404 checkLSHUtil return boolean
            return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Boolean>(new Boolean(response), HttpStatus.OK);
    }
}
