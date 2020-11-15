package it.cnr.iasi.saks.groucho.lsh.rest.impl;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MarkApiImpl {
    private static StateObserver stateObserver = (StateObserver) (new ClassPathXmlApplicationContext("applicationContext.xml")).getBean("stateObserver");

    private MarkApiImpl() {}

    public static ResponseEntity<Boolean> markState(String body) {
        return markStateLSH(CommonImplFunctions.toLSH(body));
    }

    public static ResponseEntity<Boolean> markStateLSH(String stateStringLSH) {
        boolean state;
        try {
            state = stateObserver.markState(stateStringLSH);
        } catch (LSHException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new Boolean(state), HttpStatus.OK);
    }
}
