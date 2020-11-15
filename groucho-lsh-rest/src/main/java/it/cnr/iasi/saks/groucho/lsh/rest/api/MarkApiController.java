package it.cnr.iasi.saks.groucho.lsh.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import it.cnr.iasi.saks.groucho.lsh.rest.impl.MarkApiImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-02T22:49:41.406+02:00")

@Controller
public class MarkApiController implements MarkApi {

    private static final Logger log = LoggerFactory.getLogger(MarkApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public MarkApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> markState(@ApiParam(value = "The Actual Internal Representation as a String of the Considered State." ,required=true )  @Valid @RequestBody String body) {
        String accept = request.getHeader("Accept");
        return MarkApiImpl.markState(body);
    }

    public ResponseEntity<Boolean> markStateLSH(@ApiParam(value = "LSH String of Considered State",required=true) @PathVariable("stateStringLSH") String stateStringLSH) {
        String accept = request.getHeader("Accept");
        return MarkApiImpl.markStateLSH(stateStringLSH);
    }

}
