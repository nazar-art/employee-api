package com.ukeess.controller;

import com.ukeess.exception.InvalidUserCredentialsException;
import com.ukeess.model.constant.SecurityConstants;
import com.ukeess.model.dto.AuthRequestDTO;
import com.ukeess.model.dto.AuthResponseDTO;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Nazar Lelyak.
 */
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsServiceImpl;
    private TokenProvider tokenProvider;

    @PostMapping
    @RequestMapping("/authenticate")
    @ApiOperation(value = "Authenticate for working with API",
            notes = "Provide a valid Credentials in a body",
            response = AuthResponseDTO.class)
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
