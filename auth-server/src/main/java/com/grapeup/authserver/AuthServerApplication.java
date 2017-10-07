package com.grapeup.authserver;

import com.grapeup.authserver.service.security.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Configuration
	@EnableWebSecurity
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	public class WebSecurityServerConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private MongoUserDetailsService userDetailsService;

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			// @formatter:off
			http
				.formLogin().disable() // disable form authentication
				.anonymous().disable() // disable anonymous user
				.httpBasic().disable()
				// restricting access to authenticated users
				.authorizeRequests().anyRequest().authenticated();
			// @formatter:on
		}

		@Override
		protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
			auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(new BCryptPasswordEncoder());
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManager();
		}
	}

	@Configuration
	@EnableResourceServer
	public class OAuthResourceServerConfig extends ResourceServerConfigurerAdapter {
		@Value("${security.oauth2.resource.id}")
		private String resourceId;

		// The DefaultTokenServices bean provided at the AuthorizationConfig
		@Autowired
		private DefaultTokenServices tokenServices;

		// The TokenStore bean provided at the AuthorizationConfig
		@Autowired
		private TokenStore tokenStore;

		// To allow the ResourceServerConfigurerAdapter to understand the token,
		// it must share the same characteristics with AuthorizationServerConfigurerAdapter.
		// So, we must wire it up the beans in the ResourceServerSecurityConfigurer.
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources
					.resourceId(resourceId)
					.tokenServices(tokenServices)
					.tokenStore(tokenStore);
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
					.authorizeRequests().antMatchers("/login").permitAll()
					.and()
					.authorizeRequests().anyRequest().authenticated();
			// @formatter:on
		}
	}

	@Configuration
	@EnableAuthorizationServer // enables authorization and token endpoints in current application context
	public class OAuthAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private AuthenticationManager authenticationManager;

		@Autowired
		private MongoUserDetailsService userDetailsService;

		@Autowired
		private Environment env;

		@Bean
		TokenStore tokenStore() {
			return new JwtTokenStore(accessTokenConverter());
		}

		@Bean
		JwtAccessTokenConverter accessTokenConverter() {
			// TODO use environment variables for passwords
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			char[] password = "w0rldcl4sss0ftw4resk1lls".toCharArray();
			KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("grapeup.jks"), password)
				.getKeyPair("grapeup");
			converter.setKeyPair(keyPair);
			return converter;
		}

		@Bean
		@Primary
		DefaultTokenServices tokenServices() {
			DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
			defaultTokenServices.setTokenStore(tokenStore());
			defaultTokenServices.setSupportRefreshToken(true);
			defaultTokenServices.setTokenEnhancer(accessTokenConverter());
			return defaultTokenServices;
		}

		// configure clients of application
		@Override
		public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
			// @formatter:off
			// TODO use environment variables for passwords
			clients
				.inMemory()
					.withClient("browser")
					.authorizedGrantTypes("client_credentials", "refresh_token", "password")
					.scopes("ui")
				.and()
					.withClient("account-service")
					.secret("account-service")
					.authorizedGrantTypes("client_credentials", "refresh_token")
					.scopes("server")
				.and()
					.withClient("quiz-service")
					.secret("quiz-service")
					.authorizedGrantTypes("client_credentials", "refresh_token")
					.scopes("server");
			// @formatter:on
		}

		@Override
		public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService)
				.tokenStore(tokenStore())
				.tokenServices(tokenServices())
				.accessTokenConverter(accessTokenConverter());
		}

		// configure security on /oauth/token endpoint
		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
		}

	}
}
