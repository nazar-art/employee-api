package com.ukeess.controller;

import com.ukeess.security.TokenProvider;
import com.ukeess.security.UserDetailsServiceMock;
import com.ukeess.security.constant.SecurityConstants;
import com.ukeess.security.model.AuthRequestDto;
import com.ukeess.security.model.AuthResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nazar Lelyak.
 */
@RestController
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserDetailsServiceMock userDetailsServiceMock;
    private TokenProvider tokenProvider;

    @PostMapping
    @RequestMapping("/authenticate")
    public ResponseEntity<AuthResponseDto> createAuthenticationToken(@RequestBody AuthRequestDto authRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        UserDetails userDetails = userDetailsServiceMock.loadUserByUsername(authRequest.getUsername());
        String jwt = tokenProvider.generateToken(userDetails);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstants.AUTHORIZATION_HEADER, String.format("%s%s", SecurityConstants.TOKEN_BEARER_PREFIX, jwt));

        return new ResponseEntity<>(new AuthResponseDto(jwt), httpHeaders, HttpStatus.OK);
    }

}
