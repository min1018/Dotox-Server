package likelion.Dotox.oauth.controller;

import jakarta.servlet.http.HttpSession;
import likelion.Dotox.hobby.service.HobbyService;
import likelion.Dotox.oauth.dto.OAuth2Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final HttpSession httpSession;

    @Autowired
    HobbyService hobbyService;

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
            String accountId =  (String) oAuth2Response.getProviderId();
            if (hobbyService.initialUser(accountId)) { //최초 사용자 맞다
                return "redirect:/api/" + accountId + "/userinfo";
            }
            else {
                return "redirect:/";
            }

        }
        else {
            throw new IllegalArgumentException("No OAuth2 response found in session");
        }
    }
}
