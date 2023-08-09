package com.ukeess.security.user;

import com.ukeess.config.TokenConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Nazar Lelyak.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceMock implements UserDetailsService {
    private final TokenConfiguration configuration;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new User(configuration.getUsernameMock(), configuration.getPasswordMock(), Collections.emptyList());
    }
}
