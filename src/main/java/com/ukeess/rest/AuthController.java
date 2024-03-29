package com.ukeess.rest;

import com.ukeess.exception.InvalidUserCredentialsException;
import com.ukeess.security.constant.SecurityConstants;
import com.ukeess.rest.dto.AuthRequestDTO;
import com.ukeess.rest.dto.AuthResponseDTO;
import com.ukeess.security.TokenProvider;
import com.ukeess.security.UserDetailsServiceImpl;
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
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final TokenProvider tokenProvider;

    @PostMapping("/authenticate")
    @ApiOperation(
            value = "Authenticate for working with API",
            notes = "Provide a valid Credentials in a body",
            response = AuthResponseDTO.class
    )
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(@RequestBody @Valid AuthRequestDTO authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException();
        }

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authRequest.getUsername());
        String jwt = tokenProvider.generateToken(userDetails);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstants.AUTHORIZATION_HEADER, String.format("%s%s", SecurityConstants.TOKEN_BEARER_PREFIX, jwt));

        return new ResponseEntity<>(new AuthResponseDTO(jwt), httpHeaders, HttpStatus.OK);
    }

}
