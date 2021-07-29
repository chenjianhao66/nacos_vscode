package com.jianhao.nacos_server.enity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * @author 陈建浩
 * 配置实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Validated
public class Config {


    @NotNull(message = "dataId不能为空！")
    @Pattern(regexp = "^[a-z0-9-/.:]+")
    private String dataId;

    @NotNull(message = "分组不能为空，默认为DEFAULT_GROUP")
    private String group;


    @NotNull(message = "type不能为空，默认为properties")
    private String type;
}