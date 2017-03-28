package org.template.com.common.config;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.template.com.common.interceptor.CommonInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login.html").setViewName("login");
		registry.addViewController("/index.html").setViewName("index");
		registry.addViewController("/").setViewName("index");
	}

	@Bean
	public CommonInterceptor timeInterceptor() {
		return new CommonInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(timeInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
	
	

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//开启对/blog/123.json的支持
		configurer.favorPathExtension(true).useJaf(false)
		//关闭 /blog/123?format=json 的支持
		.favorParameter(false).parameterName("mediaType")
		.ignoreAcceptHeader(true).defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("html", MediaType.TEXT_HTML);
		
	}

	//web 相关配置
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		registration.getUrlMappings().clear();
		registration.addUrlMappings("*.json");
		registration.addUrlMappings("*.xml");
		registration.addUrlMappings("*.html");
		registration.addUrlMappings("*.htm");
		registration.addUrlMappings("*.js");
		registration.addUrlMappings("*.css");
		registration.addUrlMappings("*.jpg");
		registration.addUrlMappings("*.png");
		registration.addUrlMappings("*.gif");
		return registration;
	}
	
}
