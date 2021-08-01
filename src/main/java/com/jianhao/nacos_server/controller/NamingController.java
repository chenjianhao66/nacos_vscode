package com.jianhao.nacos_server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.Service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "NACOS服务发现接口测试")
public class NamingController {


    @NacosInjected
    private NamingService namingService;


    @NacosInjected
    private ConfigService configService;
    

    /**
     * 
     * 根据serviceName添加一个空服务
     * @param serviceName
     * @param groupName
     * @return
     */
    @ApiOperation(value = "增加服务",notes = "根据输入的serviceName添加一个空服务")
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
    

    /**
     * 获取输入服务的所有实例
     * @param serviceName
     * @return List<Instance> 该服务下的实例集合
     * @throws NacosException
     */
    @ApiOperation("获取一个服务下的所有实例")
    @GetMapping("getAllInstancse")
    @ResponseBody
    public List<Instance> getAllInstancse(String serviceName)throws NacosException{
         return namingService.getAllInstances(serviceName);
    }



    /**
     * 根据IP地址、端口号、服务名、分组来注册一个实例
     * @param ip
     * @param port
     * @param serviceName
     * @param groupName
     * @return
     */
    @ApiOperation("注册实例")
    @PostMapping("registerInster")
    public String registerInster(String ip,int port,String serviceName,String groupName){
        try {
            Service service  = new Service();
            Instance instance = new Instance();
            NamingService server = NacosFactory.createNamingService("localhost");
            
            server.registerInstance(serviceName, groupName, ip, port);
        } catch (NacosException e) {
            return "注册失败！";
        }
        return "注册成功～";
    }

    @ApiOperation("注销实例")
    @PostMapping("unregisterInster")
    public String unregisterInster(String serviceName,String ip,int port){
        try {
            namingService.deregisterInstance(serviceName, ip, port);
            
        } catch (NacosException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success~";
    }

}