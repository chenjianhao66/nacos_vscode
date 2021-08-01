package com.jianhao.nacos_server.controller;


import com.jianhao.nacos_server.enity.Result;
import com.jianhao.nacos_server.enity.resultEnum;
import com.jianhao.nacos_server.exception.MyException;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
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

    /**
     * 根据dataId、group来获取一份配置
     * @param config 配置实体类
     * @return 响应对象
     */
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


    /**
     * 发布、修改配置
     * 创建和修改配置时使用的同一个发布接口，当配置不存在时会创建配置，当配置已存在时会更新配置。
     * @param dataId
     * @param group
     * @param content
     * @return
     */
    @ApiOperation(value = "发布配置",
                notes = "创建和修改配置时使用的同一个发布接口，当配置不存在时会创建配置，当配置已存在时会更新配置。")
    @PostMapping("publishConfig")
    public Result<String> publishConfig(@Validated @RequestBody Config config){
        String value = "";
        for (String v : config.getContent()) {
            //循环配置数组，并且将每一个数组元素的后面加上换行符
            value+=v+=System.lineSeparator();
        }
        try {
            //发布、修改配置
            boolean result =configService.publishConfig(config.getDataId(), 
                                                        config.getGroup(), 
                                                        value,
                                                        config.getType());
            if (result) {
                return new Result<String>().setCode(200).setMsg("发布、修改成功");
            } else {
                return new Result<String>().setCode(500).setMsg("发布、修改失败！");
            }
        } catch (NacosException e) {
            e.printStackTrace();
            return new Result<String>().setCode(500).setMsg("抛出NACOS异常");
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