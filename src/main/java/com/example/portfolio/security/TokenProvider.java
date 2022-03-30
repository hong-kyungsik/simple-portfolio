package com.example.portfolio.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.portfolio.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class TokenProvider {

    private static String SECRET_KEY;
    private static Algorithm ALGORITHM;
    private static final long AUTH_TIME = 60*60*24*7;

    @Value("${jwt.secret}")
    public void setSecretKey(String secretKey){
        SECRET_KEY = secretKey;
        ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    }

    public String create(User user){
        return JWT.create()
            .withSubject(user.getId().toString())
            .withClaim("exp", Instant.now().getEpochSecond()+AUTH_TIME)
            .sign(ALGORITHM);
    }

    public Long validateAndGetUserId(String token){
        DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
        return Long.parseLong(verify.getSubject());
    }

}
