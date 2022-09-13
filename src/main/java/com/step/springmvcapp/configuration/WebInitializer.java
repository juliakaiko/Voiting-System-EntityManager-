package com.step.springmvcapp.configuration;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
    @Override
    protected Class[] getServletConfigClasses() {
        return new Class[] { WebConfiguration.class }; 
    }
    
    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[]{HibernateConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; 
    }    

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }

}
