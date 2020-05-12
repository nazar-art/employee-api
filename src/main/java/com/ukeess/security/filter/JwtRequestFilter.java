package com.ukeess.security.filter;

import com.ukeess.security.TokenProvider;
import com.ukeess.security.UserDetailsServiceMock;
import com.ukeess.security.constant.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nazar Lelyak.
 */
@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsServiceMock userDetailsServiceMock;
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);

        String userName = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith(SecurityConstants.TOKEN_BEARER_PREFIX)) {
            jwt = authHeader.substring(7);
            userName = tokenProvider.extractUsername(jwt);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsServiceMock.loadUserByUsername(userName);

            if (tokenProvider.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
    }
}
