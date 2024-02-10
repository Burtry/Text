package com.example.big_event.config;

import com.example.big_event.interceptors.LoginInterceptor;
import com.example.big_event.json.JacksonObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/user/**")        //以 /user 开头的请求，防止Knife4j文档访问不了
                .addPathPatterns("/category/**")
                .addPathPatterns("/article/**")
                .excludePathPatterns("/user/login/**")
                .excludePathPatterns("/user/register/**");
    }


    /**
     * 扩展SpringBoot MVC框架的消息转换器
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //为消息转换器设置一个对象转换器,对象转换器可以将java对象序列化成json对象
        converter.setObjectMapper(new JacksonObjectMapper());
        //将自己的消息转换器加入到converters容器中去
        converters.add(1,converter);
    }

}
