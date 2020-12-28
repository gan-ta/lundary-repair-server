package com.hs.yourfit.core.filter;

import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.core.security.jwt.JwtTokenExpiredException;
import com.hs.yourfit.core.security.jwt.JwtTokenInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtCore jwtCore;
    private final HandlerExceptionResolver handlerExceptionResolver;

    //원래 기본 필터가 존재하나 api 서버 구현을 위해 토큰을 채와서 컨택스트 홀더에 널는 방식으로 변경
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        //요청에서 채가지고 옴
        final Optional<String> token =  jwtCore.resolveAccessToken(request);

        try {
            //토큰이 존재하고 유효하면 컨텍스트 홀터에 넣는 작업 시행
            if (token.isPresent() && jwtCore.isUsable(token.get())) {
                //인증을 받아와서 컨텍스트 홀더에다가 넣어줌
                //api는 stateless 하기 때문에 credential을 굳이 설정하지 않고
                //토큰을 받아오는 대로 컨텍스트 홀더에 넣음
                Authentication auth = jwtCore.getAuthentication(token.get());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (JwtTokenExpiredException | JwtTokenInvalidException e){
            //시큐리티 예외처리
            handlerExceptionResolver.resolveException(request, response, null, e);
            return;
        }

        chain.doFilter(request, response);
    }
}
