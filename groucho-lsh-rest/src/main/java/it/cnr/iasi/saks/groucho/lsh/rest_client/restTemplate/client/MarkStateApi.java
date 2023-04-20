package it.cnr.iasi.saks.groucho.lsh.rest_client.restTemplate.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import it.cnr.iasi.saks.groucho.lsh.rest_client.restTemplate.ApiClient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-04-17T15:15:59.684+02:00")
@Component("it.cnr.iasi.saks.groucho.lsh.rest_client.client.MarkStateApi")
public class MarkStateApi {
    private ApiClient apiClient;

    public MarkStateApi() {
        this(new ApiClient());
    }

    @Autowired
    public MarkStateApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Assert that a given state has been observed.
     * Assert that a given state has been observed. It return TRUE is the state has been marked, FALSE otherwise.
     * <p><b>200</b> - Successful Operation.
     * <p><b>400</b> - Invalid State Supplied.
     * <p><b>404</b> - Mark Exception.
     * @param body The Actual Internal Representation as a String of the Considered State. (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object markState(String body) throws RestClientException {
        return markStateWithHttpInfo(body).getBody();
    }

    /**
     * Assert that a given state has been observed.
     * Assert that a given state has been observed. It return TRUE is the state has been marked, FALSE otherwise.
     * <p><b>200</b> - Successful Operation.
     * <p><b>400</b> - Invalid State Supplied.
     * <p><b>404</b> - Mark Exception.
     * @param body The Actual Internal Representation as a String of the Considered State. (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> markStateWithHttpInfo(String body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling markState");
        }
        
        String path = UriComponentsBuilder.fromPath("/mark").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Assert that a given state has been observed.
     * Assert that a given state has been observed. It return TRUE is the state has been marked, FALSE otherwise. The input parameter is supposed to be a codification in LSH of the actual internal state representation.
     * <p><b>200</b> - Successful Operation.
     * <p><b>400</b> - Invalid LSH State Supplied.
     * <p><b>404</b> - Mark Exception.
     * @param stateStringLSH LSH String of Considered State (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object markStateLSH(String stateStringLSH) throws RestClientException {
        return markStateLSHWithHttpInfo(stateStringLSH).getBody();
    }

    /**
     * Assert that a given state has been observed.
     * Assert that a given state has been observed. It return TRUE is the state has been marked, FALSE otherwise. The input parameter is supposed to be a codification in LSH of the actual internal state representation.
     * <p><b>200</b> - Successful Operation.
     * <p><b>400</b> - Invalid LSH State Supplied.
     * <p><b>404</b> - Mark Exception.
     * @param stateStringLSH LSH String of Considered State (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> markStateLSHWithHttpInfo(String stateStringLSH) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'stateStringLSH' is set
        if (stateStringLSH == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'stateStringLSH' when calling markStateLSH");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("stateStringLSH", stateStringLSH);
        String path = UriComponentsBuilder.fromPath("/mark/{stateStringLSH}").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
