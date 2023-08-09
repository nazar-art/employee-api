package com.ukeess.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nazar Lelyak.
 */
@Data
@Configuration
@ConfigurationProperties("token")
public class TokenConfiguration {
    private String secretKeyMock;
    private String usernameMock;
    private String passwordMock;
}
