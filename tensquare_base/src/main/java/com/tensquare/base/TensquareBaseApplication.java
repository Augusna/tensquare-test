package com.tensquare.base;

import com.tensquare.base.bean.GeneralCustomizationRepositoryFactoryBean;
import com.tensquare.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryFactoryBeanClass = GeneralCustomizationRepositoryFactoryBean.class)  //自定义的Repository实现
@SpringBootApplication
@EnableEurekaClient
public class TensquareBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TensquareBaseApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}

