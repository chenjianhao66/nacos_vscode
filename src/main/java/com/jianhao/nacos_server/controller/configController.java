package com.jianhao.nacos_server.controller;


import com.jianhao.nacos_server.enity.Result;
import com.jianhao.nacos_server.exception.MyException;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.jianhao.nacos_server.enity.Config;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("config")
@Api(tags = "nacos配置中心接口类")
@Validated
public class configController {

    @NacosInjected
    private ConfigService configService;

    @ApiOperation(value = "获取配置", notes = "根据dataId、group获取一份配置")
    @PostMapping("getConfig")
    public Result<String> getConfig(@RequestBody @Validated Config config) {
        
        Result<String> result = new Result<String>();
        System.out.println("config对象---->"+config);
        try {
            String configInfo = configService.getConfig(config.getDataId(), config.getGroup(), 3000);
            System.out.println(configInfo);
            if (configInfo == null) {
                result.setCode(400).setMsg("查找不到该配置，请检查你的参数！");
                return result;
            }
            result.setData(configInfo).setCode(200).setMsg("查找成功！");
            return result;
        } catch (NacosException e) {
            e.printStackTrace();
            return result.setCode(500).setMsg("发生NacosExecption异常，请联系管理员！");
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



    @GetMapping("test")
    public void test(){
        throw new MyException();
    }
    @GetMapping("test1")
    public void test1() throws Exception {
        throw new Exception("throw Exception!!!");
    }
}