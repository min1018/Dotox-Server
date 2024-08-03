package likelion.Dotox.oauth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OAuth2Service {

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String naverClientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String naverClientSecret;

    private final RestTemplate restTemplate;

    public OAuth2Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String getNaverLoginUrl() {
        String redirectUri = "http://localhost:8080/oauth2/callback/naver";
        String state = "state";

        return "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="
                + naverClientId + "&redirect_uri=" + redirectUri + "&state=" + state;
    }

    public Map<String, Object> exchangeNaverCodeForTokens(String code) {
        MultiValueMap<String, String> naverParams = new LinkedMultiValueMap<>();
        naverParams.add("grant_type", "authorization_code");
        naverParams.add("client_id", naverClientId);
        naverParams.add("client_secret", naverClientSecret);
        naverParams.add("code", code);

        String naverTokenUri = "https://nid.naver.com/oauth2.0/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(naverParams, headers);

        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                naverTokenUri,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        return responseEntity.getBody();
    }

    public Map<String, Object> getNaverUserProfile(String accessToken) {
        String profileUri = "https://openapi.naver.com/v1/nid/me";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(profileUri, HttpMethod.GET, entity, Map.class);

        return responseEntity.getBody();
    }
}