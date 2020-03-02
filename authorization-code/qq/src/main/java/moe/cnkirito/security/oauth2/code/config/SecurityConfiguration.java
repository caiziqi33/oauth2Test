package moe.cnkirito.security.oauth2.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import moe.cnkirito.security.oauth2.code.facade.UserFacade;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        return new UserFacade();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            requestMatchers()
                // /oauth/authorize link org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint
                // 必须登录过的用户才可以进行 oauth2 的授权码申请
                .antMatchers("/", "/home","/login","/oauth/authorize")
                .and()
            .authorizeRequests()
                .anyRequest().permitAll()
                .and()
            .formLogin()
            	.usernameParameter("username")
            	.passwordParameter("password")
                .loginPage("/login")
                .failureUrl("/error")
                .and()
            .httpBasic()
                .disable()
            .exceptionHandling()
                .accessDeniedPage("/error")
                .and()
            // TODO: put CSRF protection back into this endpoint
            .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable();
//                .loginPage("/login")
//                .failureUrl("/login?authentication_error=true")
//        .httpBasic();
        // @formatter:on
    }
}
