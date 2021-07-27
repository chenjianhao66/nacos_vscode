package com.jianhao.nacos_server.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;

@RestController
public class NamingController {
    

    @ApiOperation("增加服务")
    @PostMapping("addService")
    public String addService(String serviceName,String groupName){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8848/nacos/v1/ns/service?serviceName={serviceName}&groupName={groupName}";
        Map<String,String> map = new HashMap<>();
        map.put("serviceName", serviceName);
        map.put("groupName", groupName);
        String result = restTemplate.postForObject(url, "", String.class, map);
        return result;
    }

    @ApiOperation("注册实例")
    @PostMapping("registerInster")
    public String registerInster(String ip,int port,String serviceName,String groupName){
        try {
            NamingService server = NacosFactory.createNamingService("localhost");
            server.registerInstance(serviceName, groupName, ip, port);
        } catch (NacosException e) {
            return "注册失败！";
        }
        return "注册成功～";


    }
}