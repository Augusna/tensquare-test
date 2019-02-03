package com.tensquare.manager;

import com.tensquare.common.util.JwtUtil;
import com.tensquare.manager.filter.ZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class TensquareManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TensquareManagerApplication.class, args);
    }

    @Bean
    public ZuulFilter zuulFilter(){
        return new ZuulFilter();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

}

