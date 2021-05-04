package it.cnr.iasi.saks.groucho.lsh.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.cnr.iasi.saks.groucho.lsh.rest.impl.ResetApiImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
@Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-02T22:49:41.406+02:00")

@Controller
@Slf4j
public class ResetApiController implements ResetApi {
    @Autowired
    private ResetApiImpl resetApiImpl;
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    public ResetApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> resetStateObserver() {
        log.info("resetStateObserver - input: NO");
        String accept = request.getHeader("Accept");
        return resetApiImpl.resetStateObserver();
    }
}
