package com.tensquare.tensquare_common.com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 生成令牌
 */
public class CreateJwt {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder=Jwts.builder()   //生成令牌
                .setId("1962409172")   //当前用户id
                .setSubject("first")   //用户名
                .setIssuedAt(new Date())   //时间                   //上面这是生成载荷
                .signWith(SignatureAlgorithm.HS256,"itcast")  ////生成头部信息，第一个指定编码，第二个指定盐(secret),盐随便加
                .setExpiration(new Date(new Date().getTime()+60000))     //设置过期时间,一分钟
                //添加自定义键值对
                .claim("role","admin");

        System.out.println(jwtBuilder.compact());
    }
}
