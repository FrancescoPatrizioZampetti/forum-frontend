package com.blackphoenixproductions.forumfrontend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Links;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BackendRestAPITest {

    private static final String BACKEND_PATH = "http://localhost:8083";

    @Test
    public void test_api_getTotalUsers(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.ALL_VALUE);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<EntityModel<Long>> response = restTemplate.exchange(BACKEND_PATH + "/api/getTotalUsers", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<EntityModel<Long>>(){});
        Links links = response.getBody().getLinks();

    }
}
