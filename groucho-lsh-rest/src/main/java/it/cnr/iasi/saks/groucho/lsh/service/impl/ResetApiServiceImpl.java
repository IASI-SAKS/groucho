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
//    private StateObserverFactoryOLD stateObserverFactory = new StateObserverFactoryOLD();
//    private StateObserver stateObserver = stateObserverFactory.getStateObserver();

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
