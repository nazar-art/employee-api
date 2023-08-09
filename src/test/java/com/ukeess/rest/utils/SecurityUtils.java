package com.ukeess.rest.utils;

import com.ukeess.security.constant.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Nazar Lelyak.
 */
@UtilityClass
public class SecurityUtils {

    private final int TOKEN_EXPIRATION_HOURS = 10;

    private final String TEST_USER_NAME = "harry";

    public String generateToken() {
        return generateToken(TEST_USER_NAME);
    }

    public String generateToken(String username) {
        String jwt = Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * TOKEN_EXPIRATION_HOURS))
                .signWith(SignatureAlgorithm.HS256, "my-secret-token").compact();
        return SecurityConstants.TOKEN_BEARER_PREFIX + jwt;
    }

}
