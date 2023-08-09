package com.ukeess.controller;

import com.ukeess.exception.IncorrectUserCredentialsException;
import com.ukeess.security.TokenProvider;
import com.ukeess.security.UserDetailsServiceMock;
import com.ukeess.security.constant.SecurityConstants;
import com.ukeess.security.dto.AuthRequestDTO;
import com.ukeess.security.dto.AuthResponseDTO;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Nazar Lelyak.
 */
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceMock userDetailsServiceMock;
    private final TokenProvider tokenProvider;

    @ApiOperation(
            value = "Authenticate for working with an API",
            notes = "Provide a valid Credentials in a body",
            response = AuthResponseDTO.class
    )
    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(@RequestBody @Valid AuthRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new IncorrectUserCredentialsException();
        }

        UserDetails userDetails = userDetailsServiceMock.loadUserByUsername(authRequest.getUsername());
        String jwt = tokenProvider.generateToken(userDetails);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstants.AUTHORIZATION_HEADER, String.format("%s%s", SecurityConstants.TOKEN_BEARER_PREFIX, jwt));

        return new ResponseEntity<>(AuthResponseDTO.of(jwt), httpHeaders, HttpStatus.OK);
    }

}
