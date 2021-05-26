package it.cnr.iasi.saks.groucho.lsh.service.impl;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.factoryOLD.StateObserverFactoryOLD;
import it.cnr.iasi.saks.groucho.lsh.service.ResetApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
@Qualifier("reset")
@Slf4j
public class ResetApiServiceImpl implements ResetApiService {
    private StateObserverFactoryOLD stateObserverFactory = new StateObserverFactoryOLD();
    private StateObserver stateObserver = stateObserverFactory.getStateObserver();

    public ResponseEntity<Boolean> resetStateObserver() {
        // TODO impl instead
        // TODO if 400 error return httpstatus
        try {
            stateObserver.resetStateObserver();
        } catch (LSHException ex) {
            log.error("ERROR: ", ex);
            // TODO 400 || 404 checkLSHUtil return boolean
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new Boolean(true), HttpStatus.OK);
    }
}
