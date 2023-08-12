package com.ukeess.security.user;

import com.google.common.collect.Lists;
import com.ukeess.entity.AuthUser;
import com.ukeess.exception.EntityNotFoundException;
import com.ukeess.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AuthUser authUser = userRepository.findByName(userName)
                .orElseThrow(() -> new EntityNotFoundException(userName));

        log.debug("Retrieve user for authentication: {} by user_name: {}", authUser, userName);
        return buildUserForAuthentication(authUser);
    }

    private User buildUserForAuthentication(AuthUser authUser) {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authUser.getRole());
        List<GrantedAuthority> authorities = Lists.newArrayList(grantedAuthority);
        return new User(
                authUser.getName(),
                authUser.getPassword(),
                authUser.getActive(),
                true,
                true,
                true,
                authorities
        );
    }
}
