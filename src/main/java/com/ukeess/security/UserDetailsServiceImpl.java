package com.ukeess.security;

import com.google.common.collect.Lists;
import com.ukeess.dao.impl.UserDAO;
import com.ukeess.entity.AuthUser;
import com.ukeess.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AuthUser authUser = userDAO.findUserByUserName(userName)
                .orElseThrow(() -> new EntityNotFoundException(userName));

        log.debug("Retrieve user for authentication: {} by user_name: {}", authUser, userName);
        return buildUserForAuthentication(authUser);
    }

    private User buildUserForAuthentication(AuthUser authUser) {

        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authUser.getRole());
        List<GrantedAuthority> authorities = Lists.newArrayList(grantedAuthority);

        return new User(
                authUser.getUserName(),
                authUser.getPassword(),
                authUser.getActive(),
                true,
                true,
                true,
                authorities);
    }
}
