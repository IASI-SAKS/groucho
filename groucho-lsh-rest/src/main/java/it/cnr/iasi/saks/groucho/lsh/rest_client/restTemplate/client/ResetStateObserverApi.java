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
@Component("it.cnr.iasi.saks.groucho.lsh.rest_client.client.ResetStateObserverApi")
public class ResetStateObserverApi {
    private ApiClient apiClient;

    public ResetStateObserverApi() {
        this(new ApiClient());
    }

    @Autowired
    public ResetStateObserverApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Suggest to Restore the State of the Observer to the Default Configurations.
     * Suggest to Restore the State of the Observer to the Default Configurations. It is only a suggestion, there is no commitment from the reciver.
     * <p><b>200</b> - Successful Operation.
     * <p><b>400</b> - Reset Not Allowed.
     * <p><b>404</b> - Reset Exception.
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object resetStateObserver() throws RestClientException {
        return resetStateObserverWithHttpInfo().getBody();
    }

    /**
     * Suggest to Restore the State of the Observer to the Default Configurations.
     * Suggest to Restore the State of the Observer to the Default Configurations. It is only a suggestion, there is no commitment from the reciver.
     * <p><b>200</b> - Successful Operation.
     * <p><b>400</b> - Reset Not Allowed.
     * <p><b>404</b> - Reset Exception.
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> resetStateObserverWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/reset").build().toUriString();

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
