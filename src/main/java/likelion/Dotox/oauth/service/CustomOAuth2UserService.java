package likelion.Dotox.oauth.service;

import jakarta.servlet.http.HttpSession;
import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.friendlist.repository.AccountRepository;
import likelion.Dotox.oauth.dto.OAuth2Response;
import likelion.Dotox.oauth.entity.UserEntity;
import likelion.Dotox.oauth.repository.UserRepository;
import likelion.Dotox.oauth.dto.CustomOAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final HttpSession httpSession;

    public CustomOAuth2UserService(UserRepository userRepository, AccountRepository accountRepository, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.httpSession = httpSession;
    }

    public OAuth2User loadUser(OAuth2Response oAuth2Response) {
        // Save token information in session
        httpSession.setAttribute("oAuth2Response", oAuth2Response);

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        UserEntity existData = userRepository.findByUsername(username);

        String role = "ROLE_USER";
        if (existData == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setRole(role);

            Account accountEntity = new Account();
            accountEntity.setAccountId(oAuth2Response.getProviderId());
            accountEntity.setEmail(oAuth2Response.getEmail());
            userRepository.save(userEntity);
            accountRepository.save(accountEntity);
        } else {
            existData.setUsername(username);
            existData.setEmail(oAuth2Response.getEmail());
            role = existData.getRole();
            userRepository.save(existData);
        }

        return new CustomOAuth2User(oAuth2Response, role);
    }
}
