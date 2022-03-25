package com.linrty.goods.common;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @PackageName: com.linrty.book.common
 * @ClassName: WebMvcConfigurer
 * @Description: 将系统默认的jackson替换程ali的FastJson
 * @author: Linrty
 * @date: 2022/3/24 22:19
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    // WebMvcConfigurerAdapter 这个类在SpringBoot2.0已过时，官方推荐直接实现WebMvcConfigurer 这个接口

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //自定义配置...
        // FastJsonConfig config = new FastJsonConfig();
        // config.set...
        // converter.setFastJsonConfig(config);

        // 高版本无需配置，低版本不配置报错：Content-Type cannot contain wildcard type '*'
        // List<MediaType> fastMediaTypes = new ArrayList<>();
        // fastMediaTypes .add(MediaType.APPLICATION_JSON);
        // fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        // converter.setSupportedMediaTypes(fastMediaTypes);

        converters.add(0, converter);
    }
}
