package com.example.backend_forkit.config.jwt;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.logging.Logger;

@Component
public class JwtTokenProvider {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtTokenProvider.class);

    private SecretKey getSigninKey() {
    }
}
