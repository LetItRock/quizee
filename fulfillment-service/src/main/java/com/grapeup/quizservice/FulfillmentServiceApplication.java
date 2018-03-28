package com.grapeup.quizservice;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;

@SpringBootApplication
@EnableDiscoveryClient // it will register this service with eureka
@RefreshScope // refreshes properties when /refresh endpoint is called
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FulfillmentServiceApplication extends ResourceServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(FulfillmentServiceApplication.class, args);
	}

	@Value("${spring.application.name}")
	private String resourceId;

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		final Resource resource = new ClassPathResource("grapeup.pub");
		String publicKey = null;
		try {
			publicKey = IOUtils.toString(resource.getInputStream());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources
				.resourceId(resourceId)
				.tokenServices(tokenServices());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.csrf().disable()
			.anonymous().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.and()
			.authorizeRequests().anyRequest().authenticated();
		// @formatter:on
	}
}
