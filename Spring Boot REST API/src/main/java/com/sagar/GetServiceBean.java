package com.sagar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sagar.service.AbstractProductService;

@Configuration
public class GetServiceBean {

	@Value("${caching.enabled:false}")
	private boolean isCachingEnabled;

	@Value("${caching.provider.impl:noCache}")
	private String serviceToImplement;

	@Bean
	public AbstractProductService MyServiceAlias(ApplicationContext context) {			
		
		if (isCachingEnabled && serviceToImplement.equals("noCache")) {
			return null;			
		} 
		return (AbstractProductService) context.getBean(serviceToImplement);
	}
}
