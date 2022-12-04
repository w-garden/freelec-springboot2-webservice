package com.wgarden.book.config.auth;

import com.wgarden.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 설정들을 활성화시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                //URL별 권환 관리를 설정하는 옵션의 시작
                .authorizeRequests()
                //권한 관리 대상을 지정하는 옵션
                //URL,HTTP 메소드별로 관리가 가능
                //"/" 등 지정된 URL은 permitAll() 옵션을 통해 전체 열람 권한 부여
                //"/api/v1/**" 주소를 가진 api는 USER권한을 가진 사람만 가능
                .antMatchers("/", "/css/**","/images/**","/js/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //설정된 값들 이외 나머지 URL
                //authenticated() : 인증된 사용자만 허용
                .anyRequest().authenticated()
                .and()
                //로그아웃 기능에 대한 여러 설정의 진입점
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                //Oauth 2 로그인 기능에 대한 여러 설정의 진입점
                    .oauth2Login()
                //Oauth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                        .userInfoEndpoint()
                //userService : 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                //리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능
                            .userService(customOAuth2UserService);
    }
}
