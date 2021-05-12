package it.cnr.iasi.saks.groucho.lsh.rest.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import it.cnr.iasi.saks.groucho.lsh.rest.api.MarkApi;
import it.cnr.iasi.saks.groucho.lsh.service.MarkApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Generated(value="io.swagger.codegen.languages.SpringCodegen", date="2020-07-02T22:49:41.406+02:00")
@Controller
@Slf4j
public class MarkApiController implements MarkApi {
    @Autowired @Qualifier("MarkApiService")
    private MarkApiService markApiService;
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    public MarkApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> markState(
            @ApiParam(value="The Actual Internal Representation as a String of the Considered State.", required=true)
            @Valid @RequestBody String body) {
        log.info("markState - input: {}", body);
        String accept = request.getHeader("Accept");
        return markApiService.markState(body);
    }

    public ResponseEntity<Boolean> markStateLSH(
            @ApiParam(value="LSH String of Considered State", required=true)
            @PathVariable("stateStringLSH") String stateStringLSH) {
        log.info("markStateLSH - input: {}", stateStringLSH);
        String accept = request.getHeader("Accept");
        return markApiService.markStateLSH(stateStringLSH);
    }

}
