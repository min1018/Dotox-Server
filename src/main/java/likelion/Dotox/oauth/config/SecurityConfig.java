package likelion.Dotox.oauth.config;

import likelion.Dotox.oauth.repository.CustomClientRegistrationRepo;
import likelion.Dotox.oauth.service.CustomOAuth2AuthorizedClientService;
import likelion.Dotox.oauth.service.CustomOAuth2UserService;
import likelion.Dotox.oauth.successhandler.CustomSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomClientRegistrationRepo customClientRegistrationRepo;
    private final CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService;
    private final JdbcTemplate jdbcTemplate;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomClientRegistrationRepo customClientRegistrationRepo, CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService, JdbcTemplate jdbcTemplate) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customClientRegistrationRepo = customClientRegistrationRepo;
        this.customOAuth2AuthorizedClientService = customOAuth2AuthorizedClientService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors();
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/api/**", "/oauth2/**", "/login/**","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .clientRegistrationRepository(customClientRegistrationRepo.clientRegistrationRepository())
                        .authorizedClientService(customOAuth2AuthorizedClientService.oAuth2AuthorizedClientService(jdbcTemplate, customClientRegistrationRepo.clientRegistrationRepository()))
                        .userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(customOAuth2UserService)
                        )
                        .successHandler(customSuccessHandler())
//                        .loginPage("/oauth2/authorization")
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }
}

//1. 면접 분위기
//2. 프젝 어떤거 하셨는지 - 데이터 활용한 프젝 하셨는지
//3. 스프링 어떻게 공부하셨는지 - 인강, 책 어떤 순서로 공부하셨는지
//4. 코테 어떻게 준비 -> 다른 기업은 어떻게
