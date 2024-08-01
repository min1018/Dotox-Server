package likelion.Dotox.oauth.controller;

import jakarta.servlet.http.HttpSession;
import likelion.Dotox.oauth.dto.OAuth2Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final HttpSession httpSession;

    public LoginController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/oauth2/authorization/naver")
    public String naverLogin() {
        return "redirect:/oauth2/response";
    }

    @GetMapping("/oauth2/authorization/google")
    public String googleLogin() {
        return "redirect:/oauth2/response";
    }


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
