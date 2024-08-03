package likelion.Dotox.oauth.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OAuth2ServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OAuth2Service oAuth2Service;

    @BeforeEach
    public void setUp() {
        // Test values 설정
        ReflectionTestUtils.setField(oAuth2Service, "naverClientId", "test-naver-client-id");
        ReflectionTestUtils.setField(oAuth2Service, "naverClientSecret", "test-naver-client-secret");
    }

    @Test
    public void testGetNaverLoginUrl() {
        // 실제 naverClientId 값으로 변경
        String expectedUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=test-naver-client-id&redirect_uri=http://localhost:8080/oauth2/callback/naver&state=state";

        // 실제 URL을 가져오는 부분
        String actualUrl = oAuth2Service.getNaverLoginUrl();

        assertEquals(expectedUrl, actualUrl);
    }


    @Test
    public void testExchangeNaverCodeForTokens() {
        String code = "sample-code";
        Map<String, Object> mockTokenResponse = new HashMap<>();
        mockTokenResponse.put("access_token", "naver-access-token");

        MultiValueMap<String, String> naverParams = new LinkedMultiValueMap<>();
        naverParams.add("grant_type", "authorization_code");
        naverParams.add("client_id", "test-naver-client-id");
        naverParams.add("client_secret", "test-naver-client-secret");
        naverParams.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(naverParams, headers);

        // Mock 설정
        when(restTemplate.exchange(
                eq("https://nid.naver.com/oauth2.0/token"),
                eq(HttpMethod.POST),
                eq(requestEntity),
                eq(new ParameterizedTypeReference<Map<String, Object>>() {})))
                .thenReturn(new ResponseEntity<>(mockTokenResponse, HttpStatus.OK));

        // 실제 호출
        Map<String, Object> actualResponse = oAuth2Service.exchangeNaverCodeForTokens(code);

        // Assertion
        assertEquals("naver-access-token", actualResponse.get("access_token"));
    }

    @Test
    public void testGetNaverUserProfile() {
        String accessToken = "naver-access-token";
        Map<String, Object> mockUserProfile = new HashMap<>();
        mockUserProfile.put("id", "sample-user-id");
        mockUserProfile.put("nickname", "sample-nickname");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Mock 설정
        when(restTemplate.exchange(
                eq("https://openapi.naver.com/v1/nid/me"),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Map.class)))
                .thenReturn(new ResponseEntity<>(mockUserProfile, HttpStatus.OK));

        // 실제 호출
        Map<String, Object> actualUserProfile = oAuth2Service.getNaverUserProfile(accessToken);

        // Assertion
        assertEquals("sample-user-id", actualUserProfile.get("id"));
        assertEquals("sample-nickname", actualUserProfile.get("nickname"));
    }
}