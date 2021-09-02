/* 
 * This file is part of the GROUCHO project.
 * 
 * GROUCHO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GROUCHO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GROUCHO.  If not, see <https://www.gnu.org/licenses/>
 *
 */
package it.cnr.iasi.saks.groucho.lsh.rest.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import it.cnr.iasi.saks.groucho.lsh.rest.api.IsunknownApi;
import it.cnr.iasi.saks.groucho.lsh.service.IsunknownApiService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Generated(value="io.swagger.codegen.languages.SpringCodegen", date="2020-07-02T22:49:41.406+02:00")
@Controller
public class IsunknownApiController implements IsunknownApi {
    @Autowired @Qualifier("IsUnknownApiService")
    private IsunknownApiService isunknownApiService;
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    protected Logger logger = LoggerFactory.getLogger(IsunknownApiController.class);

    
    @Autowired
    public IsunknownApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> isStateUnknown(
            @ApiParam(value="The Actual Internal Representation as a String of the Considered State", required=true)
            @Valid @RequestBody String body) {
        logger.info("isStateUnknown - input: {}", body);
        String accept = request.getHeader("Accept");
        return isunknownApiService.isStateUnknown(body);
    }

    public ResponseEntity<Boolean> isStateUnknownLSH(
            @ApiParam(value="LSH String of Considered State.", required=true)
            @PathVariable("stateStringLSH") String stateStringLSH) {
        logger.info("isStateUnknownLSH - input {}", stateStringLSH);
        String accept = request.getHeader("Accept");
        return isunknownApiService.isStateUnknown(stateStringLSH);
    }
}
