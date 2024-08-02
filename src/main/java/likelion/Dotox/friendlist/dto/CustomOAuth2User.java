package likelion.Dotox.friendlist.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * 사용자의 OAuth2 인증 정보를 담는 CustomOAuth2User 클래스입니다.
 */
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2Response oAuth2Response; // OAuth2 응답 객체
    private final String role; // 사용자 역할

    /**
     * CustomOAuth2User 생성자
     *
     * @param oAuth2Response OAuth2 응답 객체
     * @param role 사용자 역할
     */
    public CustomOAuth2User(OAuth2Response oAuth2Response, String role) {
        this.oAuth2Response = oAuth2Response;
        this.role = role;
    }

    /**
     * OAuth2 사용자의 속성(Map<String, Object>)을 반환하는 메서드
     *
     * @return 속성 맵
     */
    @Override
    public Map<String, Object> getAttributes() {
        return null; // 사용하지 않음
    }

    /**
     * 사용자의 권한 목록을 반환하는 메서드
     *
     * @return 권한 목록(Collection<? extends GrantedAuthority>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> role); // 역할을 GrantedAuthority로 변환하여 반환
        return collection;
    }

    /**
     * 사용자의 이름을 반환하는 메서드
     *
     * @return 사용자 이름
     */
    @Override
    public String getName() {
        return oAuth2Response.getName(); // OAuth2 응답 객체의 이름 반환
    }

    /**
     * 사용자의 유저네임을 반환하는 메서드
     *
     * @return 사용자 유저네임
     */
    public String getUsername() {
        return oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId(); // OAuth2 응답 객체의 제공자와 제공자 ID를 조합하여 반환
    }
}