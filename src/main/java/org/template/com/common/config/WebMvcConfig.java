package org.template.com.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
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
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/ws/ws.html").setViewName("ws");
		registry.addViewController("/chatroom/chat.html").setViewName("chat");
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
	
	

	@Bean
	public ContentNegotiationManagerFactoryBean contentNegotiationManager() {
		ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
		contentNegotiationManager.setFavorPathExtension(true);
		contentNegotiationManager.setUseJaf(false);
		contentNegotiationManager.setFavorParameter(false);
		contentNegotiationManager.setParameterName("mediaType");
		contentNegotiationManager.setIgnoreAcceptHeader(true);
		contentNegotiationManager.setDefaultContentType(MediaType.TEXT_HTML);
		contentNegotiationManager.addMediaType("json", MediaType.APPLICATION_JSON);
		contentNegotiationManager.addMediaType("xml", MediaType.APPLICATION_XML);
		return contentNegotiationManager;
	}
	
	/*@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//开启对/blog/123.json的支持
		configurer.favorPathExtension(true).useJaf(false)
		//关闭 /blog/123?format=json 的支持
		.favorParameter(false).parameterName("mediaType")
		.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML);
		
	}*/

	//web 相关配置
/*	@Bean
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
	*/
}
