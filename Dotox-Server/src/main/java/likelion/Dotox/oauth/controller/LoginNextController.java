package likelion.Dotox.oauth.controller;

import jakarta.servlet.http.HttpSession;
import likelion.Dotox.friendlist.dto.OAuth2Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginNextController {

    private final HttpSession httpSession;

    public LoginNextController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/login/loginNext")
    public String loginNextPage() {
        OAuth2Response oAuth2Response = (OAuth2Response) httpSession.getAttribute("oAuth2Response");

        if (oAuth2Response != null) {
            System.out.println("Token Info: ");
            System.out.println("Provider: " + oAuth2Response.getProvider());
            System.out.println("Provider ID: " + oAuth2Response.getProviderId());
            System.out.println("Email: " + oAuth2Response.getEmail());
            System.out.println("Name: " + oAuth2Response.getName());
        } else {
            System.out.println("No OAuth2 response found in session.");
        }

        return "loginNext";
    }
}