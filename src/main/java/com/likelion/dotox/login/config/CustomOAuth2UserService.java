package com.likelion.dotox.login.config;

import com.likelion.dotox.login.dto.OAuthAttributes;
import com.likelion.dotox.login.dto.SessionUser;
import com.likelion.dotox.user.domain.UserInfo;
import com.likelion.dotox.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //로그인 중인 서비스 구분, 구글, 네이버
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest
                .getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        //attribute 담음
        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        UserInfo user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(
                        new SimpleGrantedAuthority(user.getRoleKey())),
                        attributes.getAttributes(),
                        attributes.getNameAttributesKey());

    }

    private UserInfo saveOrUpdate(OAuthAttributes attributes) {
        UserInfo user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
