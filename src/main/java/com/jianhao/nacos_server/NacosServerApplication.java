package com.jianhao.nacos_server;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.EnableNacos;
// import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
// import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;

// import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 开启nacos的配置中心功能，指定nacso服务器地址
// @EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
// @EnableNacosConfig

// 开启nacos的服务发现功能，制定nacos服务器地址
// @EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
// @EnableNacosDiscovery
@EnableNacos(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
public class NacosServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosServerApplication.class, args);
	}

}
