package it.cnr.iasi.saks.groucho.lsh.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import it.cnr.iasi.saks.groucho.lsh.service.impl.IsunknownApiImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-02T22:49:41.406+02:00")

@Controller
@Slf4j
public class IsunknownApiController implements IsunknownApi {
    @Autowired
    private IsunknownApiImpl isunknownApiImpl;
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    public IsunknownApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> isStateUnknown(
            @ApiParam(value="The Actual Internal Representation as a String of the Considered State", required=true)
            @Valid @RequestBody String body) {
        log.info("isStateUnknown - input: {}", body);
        String accept = request.getHeader("Accept");
        return isunknownApiImpl.isStateUnknown(body);
    }

    public ResponseEntity<Boolean> isStateUnknownLSH(
            @ApiParam(value="LSH String of Considered State.", required=true)
            @PathVariable("stateStringLSH") String stateStringLSH) {
        log.info("isStateUnknownLSH - input {}", stateStringLSH);
        String accept = request.getHeader("Accept");
        return isunknownApiImpl.isStateUnknown(stateStringLSH);
    }
}
