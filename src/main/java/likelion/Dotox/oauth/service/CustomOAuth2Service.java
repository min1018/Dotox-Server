package likelion.Dotox.oauth.service;

import likelion.Dotox.oauth.dto.GoogleResponse;
import likelion.Dotox.oauth.dto.NaverResponse;
import likelion.Dotox.oauth.dto.OAuth2Response;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


//@PropertySources(@PropertySources("classpath:/application-oauth.properties"))
@Service
public class CustomOAuth2Service {

    private final RestTemplate restTemplate;

    public CustomOAuth2Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OAuth2Response getAccessToken(String code, String provider) {
        String tokenUrl;
        Map<String, String> params = new HashMap<>();
        System.out.println(code +" "+ provider);

        String decode = URLDecoder.decode(code);

        if (provider.equals("google")) {
            tokenUrl = "https://oauth2.googleapis.com/token";
            params.put("code", decode);
            //params.put("client_id", env)
            params.put("client_id", "566890532564-tt49qte30p094a9ar9l78erjjhr899se.apps.googleusercontent.com");
            params.put("client_secret", "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");
            params.put("redirect_uri", "http://localhost:8080/api/redirect");
            //params.put("redirect_uri", "http://localhost:8080/api/oauth2/token");
            params.put("grant_type", "authorization_code");
        } else if (provider.equals("naver")) {
            tokenUrl = "https://nid.naver.com/oauth2.0/token";
            params.put("code", code);
            params.put("client_id", "jW8dWv3ZfWoqr7ZhJuGO");
            params.put("client_secret", "PJ1LrDP3");
            params.put("redirect_uri", "http://localhost:8080/oauth/callback/naver");
            params.put("grant_type", "authorization_code");
        } else {
            throw new IllegalArgumentException("Unknown provider: " + provider);
        }

        Map<String, String> response = restTemplate.postForObject(tokenUrl, params, Map.class);
        String accessToken = response.get("access_token");

        String userInfoUrl;
        if (provider.equals("google")) {
            userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";
        } else {
            userInfoUrl = "https://openapi.naver.com/v1/nid/me";
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);
        Map<String, Object> userInfoResponse = restTemplate.getForObject(userInfoUrl, Map.class, headers);

        if (provider.equals("google")) {
            return new GoogleResponse(userInfoResponse);
        } else {
            return new NaverResponse(userInfoResponse);
        }
    }
}
