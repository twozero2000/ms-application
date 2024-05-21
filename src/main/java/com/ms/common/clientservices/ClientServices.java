package com.ms.common.clientservices;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClientServices {
    private final ClientServicesUri clientServicesUri;
    private final RestTemplate restTemplate;

    public String getRandomName(String nameType, int quantity) throws Exception {
        String randomNameUri = clientServicesUri.getRandomName();
        UriComponents builder = UriComponentsBuilder
                .fromHttpUrl(randomNameUri)
                .queryParam("nameType", nameType)
                .queryParam("quantity", quantity)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", "77a20282431f466e965c862c724bfb05");
        headers.set("Accept", "application/json");

        ResponseEntity<List<String>> requestEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {
        });

        if(Objects.isNull(requestEntity.getBody().get(0))){
            throw new Exception("Unable to get random name");
        }

        return requestEntity.getBody().get(0);
    }
}