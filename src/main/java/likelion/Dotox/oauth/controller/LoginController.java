package likelion.Dotox.oauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import likelion.Dotox.hobby.service.HobbyService;
import likelion.Dotox.oauth.dto.OAuth2Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
@Tag(name = "Login", description = "OAuth2 로그인")
public class LoginController {

    private final HttpSession httpSession;
    private final HobbyService hobbyService;

    @Autowired
    public LoginController(HttpSession httpSession, HobbyService hobbyService) {
        this.httpSession = httpSession;
        this.hobbyService = hobbyService;
    }

    @Operation(
            summary = "네이버 로그인 리다이렉트",
            description = "네이버 로그인"
    )
    @GetMapping("/oauth2/authorization/naver")
    public void naverLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/naver");
    }

    @GetMapping("/oauth2/authorization/google")
    @Operation(
            summary = "구글 로그인 리다이렉트",
            description = "구글 로그인"
    )
    public void googleLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

    @GetMapping("/oauth2/response")
    @Operation(
            summary = "OAuth2 응답 처리",
            description = "OAuth2 응답을 처리하고, 사용자 정보와 초기 사용자 여부를 반환합니다.",
            responses = {
                    @ApiResponse(description = "OAuth2 응답이 성공적으로 처리됨", responseCode = "200"),
                    @ApiResponse(description = "세션에 OAuth2 응답이 없음", responseCode = "400")
            }
    )
    public ResponseEntity<Map<String, Object>> oauth2Response() {
        OAuth2Response oAuth2Response = (OAuth2Response) httpSession.getAttribute("oAuth2Response");
        if (oAuth2Response != null) {
            String accountId = oAuth2Response.getProviderId();
            boolean isInitialUser = hobbyService.initialUser(accountId);

            Map<String, Object> response = new HashMap<>();
            response.put("accountId", accountId);
            response.put("isInitialUser", isInitialUser);

            return ResponseEntity.ok(response);
        } else {
            throw new IllegalArgumentException("No OAuth2 response found in session");
        }
    }
}
