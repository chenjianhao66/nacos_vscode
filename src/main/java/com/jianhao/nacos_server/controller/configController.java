package com.jianhao.nacos_server.controller;

import javax.validation.constraints.Pattern;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("config")
@Api(tags = "nacos配置中心接口类")
public class configController {

    @NacosInjected
    private ConfigService configService;

    @ApiOperation(value = "获取配置", notes = "根据dataId、group获取一份配置")
    @GetMapping("getConfig")
    public String getConfig(String dataId, String group) {
        try {
            String configInfo = configService.getConfig(dataId, group, 3000);
            System.out.println(configInfo);
            if (configInfo == null) {
                return "找不到该配置！";
            }
            return configInfo;
        } catch (NacosException e) {
            e.printStackTrace();
            return "fail";
        }
    }


    @ApiOperation(value = "发布配置",notes = "配置dataId、group、content")
    @PostMapping("publishConfig")
    public String publishConfig(String dataId,String group,String[] content){
        String value = "";
        for (String v : content) {
            value+=v+=System.lineSeparator();
        }
        try {
            boolean result = configService.publishConfig(dataId, group, value.toString());
            if (result) {
                return "发布成功！";
            } else {
                return "发布失败，请检查数据";
            }
        } catch (NacosException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}