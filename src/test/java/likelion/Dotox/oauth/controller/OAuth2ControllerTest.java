package likelion.Dotox.oauth.controller;

import likelion.Dotox.oauth.controller.OAuth2Controller;
import likelion.Dotox.oauth.service.OAuth2Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OAuth2ControllerTest {

    @Mock
    private OAuth2Service oAuth2Service;

    @InjectMocks
    private OAuth2Controller oAuth2Controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(oAuth2Controller).build();
    }

    @Test
    public void testNaverLogin() throws Exception {
        String expectedUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=actual-naver-client-id&redirect_uri=http://localhost:8080/oauth2/callback/naver&state=state";
        when(oAuth2Service.getNaverLoginUrl()).thenReturn(expectedUrl);

        // MockMvc 요청 및 응답을 ResultActions로 처리
        ResultActions resultActions = mockMvc.perform(get("/oauth2/login/naver"))
                .andDo(MockMvcResultHandlers.print()) // 요청 및 응답을 출력
                .andExpect(status().isOk())
                .andExpect(content().string(expectedUrl));

        // 실제 응답 내용 확인
        String actualUrl = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("*****Actual URL: " + actualUrl);
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testHandleNaverCallback() throws Exception {
        String code = "sample-code";
        Map<String, Object> mockTokenResponse = new HashMap<>();
        mockTokenResponse.put("access_token", "naver-access-token");

        Map<String, Object> mockUserProfile = new HashMap<>();
        mockUserProfile.put("id", "sample-user-id");
        mockUserProfile.put("nickname", "sample-nickname");

        when(oAuth2Service.exchangeNaverCodeForTokens(code)).thenReturn(mockTokenResponse);
        when(oAuth2Service.getNaverUserProfile("naver-access-token")).thenReturn(mockUserProfile);

        // MockMvc 요청 및 응답을 ResultActions로 처리
        ResultActions resultActions = mockMvc.perform(get("/oauth2/callback/naver").param("code", code))
                .andDo(MockMvcResultHandlers.print()) // 요청 및 응답을 출력
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("sample-user-id"))
                .andExpect(jsonPath("$.nickname").value("sample-nickname"));

        // 실제 응답 내용 확인
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("*****Response Content: " + responseContent);
    }
}