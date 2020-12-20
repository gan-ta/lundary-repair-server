package com.hs.lunpair.core.security.service;

import com.hs.lunpair.domain.user.dto.common.UserDetailsDto;
import com.hs.lunpair.domain.user.entity.enums.UserRole;
import com.hs.lunpair.domain.user.exception.UserNotFoundException;
import com.hs.lunpair.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;

    /**
     * 이메일을 톻하여 유저를 찾음
     * @param email 유저의 이메일
     * @return 권한이 설정 된 유저
     * @throws UsernameNotFoundException 유저 자원이 존재하지 않을 때의 예외
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailsDto userDto = UserDetailsDto.builder()
                .email("not@not.com")
                .password("not")
                .role(UserRole.ADMIN)
                .build();
        try {
            userDto = UserDetailsDto.toDto(
                    userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new)
            );
            return new User(userDto.getEmail(),
                    userDto.getPassword(),
                    getAuthorities(userDto.getRole()));

        }catch (UserNotFoundException e){
            log.error(e.getMessage());
            return new User(userDto.getEmail(),
                    userDto.getPassword(),
                    getAuthorities(userDto.getRole()));
        }
    }

    public Set<? extends GrantedAuthority> getAuthorities(UserRole role) {
        //권한들의 설정
        Set<GrantedAuthority> set = new HashSet<>();

        //권한 ADMIN > SELLER > USER
        switch(role){
            case ADMIN: set.add(new SimpleGrantedAuthority("ROLE_" + UserRole.ADMIN.toString()));
            case SELLER: set.add(new SimpleGrantedAuthority("ROLE_" + UserRole.SELLER.toString()));
            default: set.add(new SimpleGrantedAuthority("ROLE_" + UserRole.CUSTOMER.toString()));
        }

        return set;
    }
}

