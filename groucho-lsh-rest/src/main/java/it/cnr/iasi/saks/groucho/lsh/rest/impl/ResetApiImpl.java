package it.cnr.iasi.saks.groucho.lsh.rest.impl;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.factory.StateObserverFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResetApiImpl {
    private StateObserverFactory stateObserverFactory = new StateObserverFactory();
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
