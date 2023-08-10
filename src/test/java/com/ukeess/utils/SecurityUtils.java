package com.ukeess.utils;

import com.ukeess.security.constant.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Nazar Lelyak.
 */
@UtilityClass
public class SecurityUtils {

    private final String TEST_USER_NAME = "harry";

    public String generateToken() {
        return generateToken(TEST_USER_NAME, false);
    }

    public String generateExpiredToken() {
        return generateToken(TEST_USER_NAME, true);
    }

    public String generateToken(String username, boolean expired) {
        Instant now = Instant.now();
        Instant expiration = expired ?
                now.minus(5, ChronoUnit.HOURS) :
                now.plus(30, ChronoUnit.MINUTES);

        String jwt = Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS256, "my-token-secret").compact();
        return SecurityConstants.TOKEN_BEARER_PREFIX + jwt;
    }

    public static void main(String[] args) {
        System.out.println(generateExpiredToken());
    }

}
