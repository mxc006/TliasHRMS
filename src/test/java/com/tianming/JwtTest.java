package com.tianming;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /*生成JWT令牌*/
    @Test
    public void testGenerateJwt() {
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("username","tianming");
        dataMap.put("password","123456");
        String jwt=Jwts.builder().signWith(SignatureAlgorithm.HS256,"aXRoZWltYQ==")
                .addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsInVzZXJuYW1lIjoidGlhbm1pbmciLCJleHAiOjE3ODIwMzAxMDR9.mySqg2PbwA9bIznDoorcbWXpQxzjAJbkkv-mv9fPhJM";
        Claims claims=Jwts.parser()
                .setSigningKey("aXRoZWltYQ==")
                .parseClaimsJws(token)
                .getBody();
         System.out.println(claims);
    }
}
