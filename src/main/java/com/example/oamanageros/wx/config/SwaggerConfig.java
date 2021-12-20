package com.example.oamanageros.wx.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 笑的心酸 - Red4Lion - mnmnmssd
 * @date 2021.12.20
 * 配置Swager
 */

@Configuration
@EnableOpenApi
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.OAS_30);

        // 添加配置信息 设置标题信息等等
        ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("OAOS在线办公系统");
        ApiInfo info = builder.build();
        docket.apiInfo(info);

        // 配置swagger执行测试的类与方法
        ApiSelectorBuilder selectorBuilder = docket.select();
        selectorBuilder.paths(PathSelectors.any());
        selectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        docket = selectorBuilder.build();

        //设置JWT 令牌
        ApiKey apiKey = new ApiKey("token", "token", "header");
        List<SecurityScheme> apiKeys = Collections.singletonList(apiKey);
        docket.securitySchemes(apiKeys);

        //设置作用域
        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopes = {scope};
        SecurityReference reference = new SecurityReference("token", scopes);
        List<SecurityReference> refList = new ArrayList<>();
        refList.add(reference);
        SecurityContext context = SecurityContext.builder().securityReferences(refList).build();
        List<SecurityContext> cxtList = new ArrayList<>();
        cxtList.add(context);
        docket.securityContexts(cxtList);

        return docket;
    }
}
