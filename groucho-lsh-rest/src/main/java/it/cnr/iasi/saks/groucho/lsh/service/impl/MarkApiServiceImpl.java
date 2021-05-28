package it.cnr.iasi.saks.groucho.lsh.service.impl;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.StateObserverFactory;
import it.cnr.iasi.saks.groucho.lsh.StateObserverLSH;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
//import it.cnr.iasi.saks.groucho.lsh.factoryOLD.StateObserverFactoryOLD;
import it.cnr.iasi.saks.groucho.lsh.service.MarkApiService;
//import it.cnr.iasi.saks.groucho.lsh.util.StateObserverLSH_InsideUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
@Qualifier("mark")
@Slf4j
public class MarkApiServiceImpl implements MarkApiService {
//    private StateObserverFactoryOLD stateObserverFactory = new StateObserverFactoryOLD();
//    private StateObserver stateObserver = stateObserverFactory.getStateObserver();
//    // TODO next StateObserver -> StateObserverLSH
//    private StateObserverLSH_InsideUtil stateObserverLSH = (StateObserverLSH_InsideUtil) stateObserverFactory.getStateObserverLSH();

    public ResponseEntity<Boolean> markState(String body) {
        // TODO impl instead
        // TODO if 400 error return httpstatus
        try {
    		StateObserverFactory soFactory = StateObserverFactory.getInstance();
    	    StateObserver stateObserver = soFactory.getStateObserver();

            stateObserver.markState(body);
        } catch (LSHException ex) {
            log.error("ERROR: ", ex);
            // TODO 400 || 404 checkLSHUtil return boolean
            return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> markStateLSH(String stateStringLSH) {
        // TODO impl instead
        // TODO if 400 error return httpstatus
        try {
    		StateObserverFactory soFactory = StateObserverFactory.getInstance();
    	    StateObserverLSH stateObserverLSH = soFactory.getStateObserverLSH();

            stateObserverLSH.markState(stateStringLSH);
        } catch (LSHException ex) {
            log.error("ERROR: ", ex);
            // TODO 400 || 404 checkLSHUtil return boolean
            return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
}
