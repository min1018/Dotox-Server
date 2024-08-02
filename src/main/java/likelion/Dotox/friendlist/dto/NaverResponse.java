package likelion.Dotox.friendlist.dto;

import java.util.Map;

/**
 * Naver OAuth2 응답 정보를 담는 클래스인 NaverResponse입니다.
 * OAuth2Response 인터페이스를 구현하였습니다.
 */
public class NaverResponse implements OAuth2Response {

    private final Map<String, Object> attribute; // OAuth2 응답 속성을 담는 맵

    /**
     * NaverResponse 생성자
     *
     * @param attribute OAuth2 응답 속성 맵에서 "response" 키의 값
     */
    public NaverResponse(Map<String, Object> attribute) {
        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    /**
     * 제공자(provider) 이름을 반환하는 메서드
     *
     * @return 제공자 이름 ("naver"로 고정)
     */
    @Override
    public String getProvider() {
        return "naver";
    }

    /**
     * 제공자 ID(providerId)를 반환하는 메서드
     *
     * @return 제공자 ID (attribute 맵에서 "id" 키의 값)
     */
    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    /**
     * 이메일을 반환하는 메서드
     *
     * @return 이메일 (attribute 맵에서 "email" 키의 값)
     */
    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    /**
     * 사용자 이름을 반환하는 메서드
     *
     * @return 사용자 이름 (attribute 맵에서 "name" 키의 값)
     */
    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}