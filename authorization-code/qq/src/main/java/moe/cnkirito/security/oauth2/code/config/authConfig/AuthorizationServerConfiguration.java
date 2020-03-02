package moe.cnkirito.security.oauth2.code.config.authConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource createDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public ClientDetailsService dbClientDetail() {
		return new JdbcClientDetailsService(createDataSource());
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.realm("qq").allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(dbClientDetail());
	}
	
	//引用缓存对象
	@Autowired
	public RedisConnectionFactory redisConnectionFactory;
	
	@Autowired
    @Qualifier("authenticationManagerBean")
	public AuthenticationManager authenticationManager;
	 
	@Bean
    public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
    }
	 
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		  endpoints.tokenStore(tokenStore())
          .authenticationManager(authenticationManager)
          .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	}
}
