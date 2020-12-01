package com.atguigu.gmall1213.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//标示启动spring服务
@ComponentScan("com.atguigu.gmall1213")//组件扫面
@EnableDiscoveryClient//让注册中心能够发现，扫描到改服务
public class ServiceProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProductApplication.class, args);
    }
}
