package com.likelion.dotox.login.config;


import com.likelion.dotox.user.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) ->
                        csrfConfig.disable())
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable())
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/","/css/**", "/images/**","/js/**", "/h2-console/**").permitAll()
                                .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                                .anyRequest().authenticated()
                )
                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/"))
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
