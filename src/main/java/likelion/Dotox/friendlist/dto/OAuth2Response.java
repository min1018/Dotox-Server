package likelion.Dotox.friendlist.dto;


/**
 * OAuth2 인증 응답에 대한 기본 인터페이스인 OAuth2Response입니다.
 * 구현체는 각 제공자(Ex. naver, google)의 인증 응답에서 필요한 정보를 제공합니다.
 */
public interface OAuth2Response {

    /**
     * 제공자(provider) 이름을 반환하는 메서드
     *
     * @return 제공자 이름
     */
    String getProvider();

    /**
     * 제공자에서 발급해주는 아이디(번호)를 반환하는 메서드
     *
     * @return 제공자에서 발급한 아이디(번호)
     */
    String getProviderId();

    /**
     * 사용자 이메일을 반환하는 메서드
     *
     * @return 사용자 이메일
     */
    String getEmail();

    /**
     * 사용자 실명(설정한 이름)을 반환하는 메서드
     *
     * @return 사용자 실명(설정한 이름)
     */
    String getName();
}
