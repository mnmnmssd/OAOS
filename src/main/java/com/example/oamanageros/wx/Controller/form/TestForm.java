package com.example.oamanageros.wx.Controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author 笑的心酸 - Red4Lion - mnmnmssd
 * @date 2021.12.20
 * 配置测试类
 */
@ApiModel
@Data
public class TestForm {
    @NotBlank
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,15}$")
    @ApiModelProperty(value = "姓名")
    private String name;
}
