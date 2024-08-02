package likelion.Dotox.oauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import likelion.Dotox.oauth.dto.OAuth2Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "OAuth2 Login", description = "OAuth2 로그인 관련 API")
@RestController
public class LoginController {

    private final HttpSession httpSession;

    public LoginController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Operation(summary = "네이버 로그인", description = "네이버 로그인 요청을 처리합니다.")
    @GetMapping("/oauth2/authorization/naver")
    public String naverLogin() {
        return "redirect:/oauth2/response";
    }

    @Operation(summary = "구글 로그인", description = "구글 로그인 요청을 처리합니다.")
    @GetMapping("/oauth2/authorization/google")
    public String googleLogin() {
        return "redirect:/oauth2/response";
    }

    @Operation(summary = "OAuth2 응답 처리", description = "OAuth2 로그인 후 응답을 처리하고 최초 접속자일 경우 취미 선택 페이지로  리다이렉션을 수행합니다.")
    @GetMapping("/oauth2/response")
    public String oauth2Response() {
        OAuth2Response oAuth2Response = (OAuth2Response) httpSession.getAttribute("oAuth2Response");
        if (oAuth2Response != null) {
            return (String) oAuth2Response.getProviderId();
        }
        else {
            throw new IllegalArgumentException("No OAuth2 response found in session");
        }
    }
}
