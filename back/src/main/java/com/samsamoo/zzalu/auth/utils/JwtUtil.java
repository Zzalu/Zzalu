package com.samsamoo.zzalu.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    public static String createToken(String username, String secretKey, long expireTimeMs) {
        Claims claims = Jwts.claims(); // 일종의 map 정보를 넣을 수 있음, username 넣었음
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) // expireTimeMs: 만료 날짜
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
                ;
    }
}
