package com.hs.lunpair.core.security.jwt;

import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.entity.enums.UserRole;
import com.hs.lunpair.domain.user.exception.UserNotFoundException;
import com.hs.lunpair.domain.user.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class JwtCore {

    /*
     * jwt : Json Web Token : Json 객체를 통해 안전하게 정보를 전송할 수 있는 웹 표준
     * '.'를 구분자로 세 부분으로 구분되어 있는 문자열로 구성
     * aaaaaa.bbbbbb.ccccc (헤더(header), 내용(payload), 서명(signature))
     * 1, 헤더
     * - typ: 토큰의 타입을 지정
     * - alg: Signature 해싱 알고리즘 지정
     *
     * 2, 내용(payload)
     * - 정보의 한 조각을 Claim이라고 한다
     * - 등록된 클레임, 공개 클레임, 비공개 믈레임
     * - 등록된 클레임 : 서비스에 필요한 정보가 아닌 토큰에 대한 정보들을 담기 위하여 이름이 이미 정해진 클레임
     * iss : 토큰 발급자
     * sub : 토큰 제목
     * aud : 토큰 대상자
     * exp : 토큰의 만료시간
     * nbf : 토큰의 활성 날짜
     * iat : 토큰이 발급된 시간
     * jti " jwt의 고유 식별자
     * - 공개 클레임 : 충돌이 방지된 이름을 가지고 있어야 한다.
     * 충돌을 방지하기 위해서는, 클레임 이름을 URI 형식으로 짓는다.
     * - 비공개 클레임 : 등록된 클레임도 아니고, 공개된 클레임들고 아니기에 협의하에 사용되는 클레임 이름들
     *
     * 3, 서명(signature)
     * - 토큰을 인코딩하거나 유효성 검증을 할 때 사용하는 고유한 암호화 코드
     * 서명 -> 헤더와 페이로드의 값을 각각 BASE64로 인코딩 -> 인코딩한 값을 비밀 키를 이용해 헤더에서 정의한 알고리즘으로 해싱 -> 다시 BASE64로 인코딩
     *
     * 장점 : 세션이 유지되지 않는 가중 서버 환경에서도 로그인 가능
     * 별도의 인증 저장소 필요 없음
     * 쿠키 전달하지 않아도 됨으로 취약점 사라짐
     * URL 파라미터와 헤더로 사용
     * 트레픽 부담 낮음
     * Rest 서비스로 제공 가능
     * 내장된 만료
     * 독립적인 jwt
     *
     * 단점: 토큰 자체에 정보 담고 있으므로 위험요소
     * 페이로드에 3가지 종류의 클레임을 저장하기 때문에 정보가 많아질수록 토큰의 길이 증가 -> 네트워크 부하 위험
     * 페이로드는 BASE64로 인코딩 된 것이므로 탈취 당하여 데이터 보일 수 있는 위험이 있기에 JWE로 암호화 하거나 Payload에 중요 데이터 넣지 말아야 함
     * Stateless : 상태를 저장하지 않기 때문에 한번 만들어지면 제어가 불가(꼭 만료 시간을 넣어 주워야 한다.)
     * Tore Token: 토큰은 클라이언트 측에서 관리해야 하기 때문에, 토큰을 저장해야 한다.
     */

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    final String USER = "user_id";
    private final long ACCESS_EXPIRE = 1000 * 60 * 30;
    private final long REFRESH_EXPIRE = 1000 * 60 * 24 * 14;
    private static final String TYPE = "jwt";
    private static final String ALGORITHM = "HS256";

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init(){
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    /**
     * JWT 페이로드에 달릴 claim 생성
     * @param email : 사용자 이메일
     * @param userRole : 사용자 역활
     * @return : 사용자 정보를 포함한 claim 갹채
     */
    private Claims generateClaim(String email, UserRole userRole){
        Claims claims = Jwts.claims();
        claims.put(USER,email);
        claims.put("userRole",userRole);

        return claims;
    }

    /**
     * jwt 토큰 생성
     * @param email 회원의 이름
     * @param userRole 회원의 역활
     * @return 사용자의 accessToken
     */
    public String createAccessToken(String email, UserRole userRole){
        Date issueDate = new Date();
        Date expireDate = new Date();

        expireDate.setTime(issueDate.getTime() + ACCESS_EXPIRE);
        return Jwts.builder()
                .setHeaderParam("typ",TYPE)
                .setHeaderParam("alg",ALGORITHM )
                .setClaims(generateClaim(email,userRole))
                .setIssuedAt(issueDate)
                .setSubject("AccessToken")
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, generateKey())
                .compact();
    }

    /**
     * 키 변환을 위한 key 를 만드는 메서드
     * @return secret key
     */
    private byte[] generateKey(){
        return SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 헤더에서 엑세스 토큰을 가져옴
     * @param request : 요청
     * @return : 엑세스 토큰
     */
    public Optional<String> resolveAccessToken(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader("accessToken"));
    }

    /**
     * 토큰의 유효성 판단
     * @param accessToken : 엑세스 토큰
     * @return :  토큰 만료 여부
     */
    public boolean isUsable(String accessToken){
        try{
            Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(accessToken);
            return true;
        }catch (SignatureException e) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
            throw new JwtTokenExpiredException();
        } catch (IllegalArgumentException e) {
            log.error("Empty JWT claims");
        }
        return false;
    }

    /**
     * 토큰을 통하여 Authentication 객체를 생성
     * credential이 없는 이유는 서버에서는 딱히 정보를 저장하지 않기 때문에 credential(비밀번호)를 딱히 사용할 필요가 없기 때문이다
     * @param accessToken 사용자 토큰
     * @return 사용자 정보를 담은 UsernamePasswordAuthenticationToken 객체
     */
    public Authentication getAuthentication(String accessToken){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.findEmailByToken(accessToken));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * 사용자 토큰을 이용하여 이메일 가져오기
     * @param accessToken 사용자 토큰
     * @return 사용자 이메일
     */
    public String findEmailByToken(String accessToken){
        return (String) Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJwt(accessToken)
                .getBody()
                .get(USER);//jwt secret키를 이용하여 토큰 body에 있는 정보를 가져옴
    }

    /**
     * 토큰을 이용하여 유저 엔티티 가져오기
     * @param accessToken 사용자 토큰
     * @return 토큰에 대한 유저 엔티티
     */
    public User findUserByToken(String accessToken){
        return userRepository.findByEmail(findEmailByToken(accessToken)).orElseThrow(UserNotFoundException::new);
    }

}
