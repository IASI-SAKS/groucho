package it.cnr.iasi.saks.groucho.lsh.service;

import org.springframework.http.ResponseEntity;

public interface IsunknownApiService {
    ResponseEntity<Boolean> isStateUnknown(String body);
    ResponseEntity<Boolean> isStateUnknownLSH(String stateStringLSH);
}
