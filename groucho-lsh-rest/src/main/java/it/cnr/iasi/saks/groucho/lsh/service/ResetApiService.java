package it.cnr.iasi.saks.groucho.lsh.service;

import org.springframework.http.ResponseEntity;

public interface ResetApiService {
    ResponseEntity<Boolean> resetStateObserver();
}
