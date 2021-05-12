package it.cnr.iasi.saks.groucho.lsh.service.impl;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.factory.StateObserverFactory;
import it.cnr.iasi.saks.groucho.lsh.util.StateObserverLSH;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
@Slf4j
public class MarkApiImpl {
    private StateObserverFactory stateObserverFactory = new StateObserverFactory();
    private StateObserver stateObserver = stateObserverFactory.getStateObserver();
    // TODO next StateObserver -> StateObserverLSH
    private StateObserverLSH stateObserverLSH = (StateObserverLSH) stateObserverFactory.getStateObserverLSH();

    public ResponseEntity<Boolean> markState(String body) {
        // TODO impl instead
        // TODO if 400 error return httpstatus
        try {
            stateObserver.markState(body);
        } catch (LSHException ex) {
            log.error("ERROR: ", ex);
            // TODO 400 || 404 checkLSHUtil return boolean
            return new ResponseEntity(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> markStateLSH(String stateStringLSH) {
        NotImplementedException ex = new NotImplementedException();
        log.error("ERROR: ", ex);
        throw ex;
    }
}
