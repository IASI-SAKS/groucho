package it.cnr.iasi.saks.groucho.lsh.rest.impl;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResetApiImpl {
    private static StateObserver stateObserver = (StateObserver) (new ClassPathXmlApplicationContext("applicationContext.xml")).getBean("stateObserver");

    private ResetApiImpl() {}

    public static ResponseEntity<Boolean> resetStateObserver() {
        try {
            stateObserver.resetStateObserver();
        } catch (LSHException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
    }
}
