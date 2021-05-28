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
import it.cnr.iasi.saks.groucho.lsh.rest.api.ResetApi;
import it.cnr.iasi.saks.groucho.lsh.service.ResetApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;

@Generated(value="io.swagger.codegen.languages.SpringCodegen", date="2020-07-02T22:49:41.406+02:00")
@Controller
@Slf4j
public class ResetApiController implements ResetApi {
    @Autowired @Qualifier("ResetApiService")
    private ResetApiService resetApiService;
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
        return resetApiService.resetStateObserver();
    }
}
