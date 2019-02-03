package com.tensquare.tensquare_common.com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * 解析令牌
 * token验证
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        Claims claims=Jwts.parser()
                .setSigningKey("itcast")  //盐(secret)
                //下面这个参数是整个令牌
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxOTYyNDA5MTcyIiwic3ViIjoiZmlyc3QiLCJpYXQiOjE1NDg1MTEwODEsImV4cCI6MTU0ODUxMTE0MX0.CqpSokDBj4SsR88Wu8eHml3LP1GuHzO3RVJrxi3mNlM")
                .getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());  //用户名
        System.out.println("登录时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));//时间
        System.out.println("过期时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
    }
}
