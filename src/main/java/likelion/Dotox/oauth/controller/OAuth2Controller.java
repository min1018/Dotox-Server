package likelion.Dotox.oauth.controller;

import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.oauth.service.OAuth2Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {
    private final OAuth2Service oAuth2Service;

    public OAuth2Controller(OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }

    @PostMapping("/callback")
    public ResponseEntity<?> login(@RequestParam String code, @RequestParam String state) {
        try {
            String accessToken = oAuth2Service.getAccessToken(code, state);
            Account account = oAuth2Service.getUserInfo(accessToken);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}