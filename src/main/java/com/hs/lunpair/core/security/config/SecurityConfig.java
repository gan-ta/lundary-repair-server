package com.hs.lunpair.core.security.config;

import com.hs.lunpair.core.filter.JwtAuthenticationFilter;
import com.hs.lunpair.core.security.jwt.JwtCore;
import com.hs.lunpair.core.security.service.UserDetailsServiceImpl;
import com.hs.lunpair.domain.user.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity//Spring Security Filter Chain 이용한다는 것을 명시
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtCore jwtCore;
    private final HandlerExceptionResolver handlerExceptionResolver;

    private static final String[] AUTH_ARR = {
            "/swagger-ui.html"
    };

    //이쪽 경로로 오는 것들은 시큐리티 무시 하겠다
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(AUTH_ARR);
    }

    //api 경로 필터링 세팅
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic().disable()
//                .csrf().disable() CSRF(Cross-Site Request Forgery)로부터 보호
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //"start with "ROLE_" as this is automatically inserted" -> 따라서 권한 세팅 시 USER_***식으로 설정
                .antMatchers("/v1/api/admin/**").hasRole(UserRole.ADMIN.toString())
                .antMatchers("/v1/api/seller/**").hasRole(UserRole.SELLER.toString())
                .anyRequest().permitAll()
                .and()
                //필터를 대치 해주겠다. 원래는 userName과 패스워드를 쳐서 들어가야 했지만
                //여기에서는 토큰을 낚아채서 그 정보를 가지고 직접 auth 객체를 만들어서 context 홀더에 넣어주겠다
                .addFilterBefore(new JwtAuthenticationFilter(jwtCore,handlerExceptionResolver), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * userDetailsService를 내가 설정할 것으로 세팅
     * (loadByUserName에서 사실은 email을 통하여 UserDetails를 반환)
     * @param auth AuthenticationManagerBuilder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 암호화 객체 빈 설정
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
