package moe.cnkirito.security.oauth2.code.config.resourceConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer()
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("qq").stateless(true);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().authenticated();
	       http
	       		.requestMatchers()
	       		// 保险起见，防止被主过滤器链路拦截
	       		.antMatchers("/qq/**").and()
	       		.authorizeRequests().anyRequest().authenticated()
	       		.and()
	       		.authorizeRequests()
	       		.antMatchers("/qq/info/**").access("#oauth2.hasScope('getUserInfo')");
	}
	
}
