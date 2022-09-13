package com.step.springmvcapp.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@EnableAspectJAutoProxy(proxyTargetClass = true) // ошибка 500!!!
@Configuration 
@EnableWebMvc 
@ComponentScan (basePackages = "com.step.springmvcapp.controller" ) 
public class WebConfiguration extends WebMvcConfigurerAdapter  {
   
    @Bean  
    public ViewResolver viewResolver (){
        InternalResourceViewResolver viewResolver =new InternalResourceViewResolver ();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
    
        return viewResolver;
    }
    
    @Bean 
    public MessageSource messageSource(){ 
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
    
    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");

    }  
}
