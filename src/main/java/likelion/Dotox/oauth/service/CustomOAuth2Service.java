package likelion.Dotox.oauth.service;

import likelion.Dotox.oauth.dto.GoogleResponse;
import likelion.Dotox.oauth.dto.NaverResponse;
import likelion.Dotox.oauth.dto.OAuth2Response;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomOAuth2Service {

    private final RestTemplate restTemplate;

    public CustomOAuth2Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OAuth2Response getAccessToken(String code, String provider) {
        String tokenUrl;
        Map<String, String> params = new HashMap<>();

        if (provider.equals("google")) {
            tokenUrl = "https://oauth2.googleapis.com/token";
            params.put("code", code);
            params.put("client_id", "YOUR_GOOGLE_CLIENT_ID");
            params.put("client_secret", "YOUR_GOOGLE_CLIENT_SECRET");
            params.put("redirect_uri", "YOUR_GOOGLE_REDIRECT_URI");
            params.put("grant_type", "authorization_code");
        } else if (provider.equals("naver")) {
            tokenUrl = "https://nid.naver.com/oauth2.0/token";
            params.put("code", code);
            params.put("client_id", "YOUR_NAVER_CLIENT_ID");
            params.put("client_secret", "YOUR_NAVER_CLIENT_SECRET");
            params.put("redirect_uri", "YOUR_NAVER_REDIRECT_URI");
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
