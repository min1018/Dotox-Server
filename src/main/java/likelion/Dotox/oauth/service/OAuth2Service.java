package likelion.Dotox.oauth.service;

import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.friendlist.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OAuth2Service {
    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String redirectUri;

    public OAuth2Service(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.restTemplate = new RestTemplate();
    }

    public String getAccessToken(String code, String state) {
        String url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&code=" + code
                + "&state=" + state
                + "&redirect_uri=" + redirectUri;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        Map<String, Object> responseBody = response.getBody();

        if (responseBody.containsKey("error")) {
            throw new RuntimeException("Error fetching access token: " + responseBody.get("error_description"));
        }

        return (String) responseBody.get("access_token");
    }

    public Account getUserInfo(String accessToken) {
        String url = "https://openapi.naver.com/v1/nid/me";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        Map<String, Object> userAttributes = (Map<String, Object>) response.getBody().get("response");

        String accountId = (String) userAttributes.get("id");
        String email = (String) userAttributes.get("email");
        String nickName = (String) userAttributes.get("nickname");
        String gender = (String) userAttributes.get("gender");
        String age = (String) userAttributes.get("age");

        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            account = new Account();
            account.setAccountId(accountId);
        }
        account.setEmail(email);
        account.setNickName(nickName);
        account.setGender(gender);
        account.setAge(age);

        return accountRepository.save(account);
    }
}