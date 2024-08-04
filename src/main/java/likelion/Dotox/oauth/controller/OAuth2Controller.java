package likelion.Dotox.oauth.controller;

import jakarta.servlet.http.HttpSession;
import likelion.Dotox.hobby.service.HobbyService;
import likelion.Dotox.oauth.service.OAuth2Service;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @Autowired
    public OAuth2Controller(OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }


    @GetMapping("/login/naver")
    public ResponseEntity<String> getNaverLoginUrl() {
        String url = oAuth2Service.getNaverLoginUrl();
        return ResponseEntity.ok(url);
    }

    @GetMapping("/callback/naver")
    public ResponseEntity<Map<String, Object>> handleNaverCallback(@RequestParam("code") String code) {
        Map<String, Object> tokenResponse = oAuth2Service.exchangeNaverCodeForTokens(code);
        String accessToken = (String) tokenResponse.get("access_token");
        Map<String, Object> userProfile = oAuth2Service.getNaverUserProfile(accessToken);

        return ResponseEntity.ok(userProfile);
    }
}