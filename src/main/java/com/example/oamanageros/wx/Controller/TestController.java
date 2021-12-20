package com.example.oamanageros.wx.Controller;

import com.example.oamanageros.wx.Controller.form.TestForm;
import com.example.oamanageros.wx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 笑的心酸 - Red4Lion - mnmnmssd
 * @date 2021.12.20
 *  测试swagger
 */
@RestController
@RequestMapping("/test")
@Api("测试Web接口")
public class TestController {
    @PostMapping("/sayHello")
    @ApiOperation("简单测试")
    @ApiImplicitParam(name = "form", value = "对象参数", required = true, dataType = "form", dataTypeClass = TestForm.class)
    public R sayHello(@Valid @RequestBody TestForm form) {
        return R.ok().put("message","Hello" + form.getName());
    }
}
