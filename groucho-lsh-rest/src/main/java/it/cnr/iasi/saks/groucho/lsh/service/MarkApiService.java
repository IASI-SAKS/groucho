package it.cnr.iasi.saks.groucho.lsh.service;

import org.springframework.http.ResponseEntity;

public interface MarkApiService {
    ResponseEntity<Boolean> markState(String body);
    ResponseEntity<Boolean> markStateLSH(String stateStringLSH);
}
