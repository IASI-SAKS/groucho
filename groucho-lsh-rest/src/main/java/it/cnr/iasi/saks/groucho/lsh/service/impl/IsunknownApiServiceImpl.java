package it.cnr.iasi.saks.groucho.lsh.service.impl;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.factoryOLD.StateObserverFactoryOLD;
import it.cnr.iasi.saks.groucho.lsh.service.IsunknownApiService;
import it.cnr.iasi.saks.groucho.lsh.util.StateObserverLSH_InsideUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
@Qualifier("isunknown")
@Slf4j
public class IsunknownApiServiceImpl implements IsunknownApiService {
    private StateObserverFactoryOLD stateObserverFactory = new StateObserverFactoryOLD();
    private StateObserver stateObserver = stateObserverFactory.getStateObserver();
    // TODO next StateObserver -> StateObserverLSH
    private StateObserverLSH_InsideUtil stateObserverLSH = (StateObserverLSH_InsideUtil) stateObserverFactory.getStateObserverLSH();

    public ResponseEntity<Boolean> isStateUnknown(String body) {
        // TODO impl instead
        // TODO if 400 error return httpstatus4
        boolean state;
        try {
            state = stateObserver.isStateUnknown(body);
        } catch (LSHException ex) {
            log.error("ERROR: ", ex);
            // TODO 400 || 404 checkLSHUtil return boolean
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new Boolean(state), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> isStateUnknownLSH(String stateStringLSH) {
        NotImplementedException ex = new NotImplementedException();
        log.error("ERROR: ", ex);
        throw ex;
    }
}
