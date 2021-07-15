package com.zziri.contact.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(testHandlerMethodArgumentResolver());
    }

    @Bean
    public TestHandlerMethodArgumentResolver testHandlerMethodArgumentResolver() {
        return new TestHandlerMethodArgumentResolver();
    }
}
