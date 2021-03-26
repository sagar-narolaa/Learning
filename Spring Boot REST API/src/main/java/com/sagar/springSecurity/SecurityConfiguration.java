
package com.sagar.springSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sagar.serviceimpl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		//return new BCryptPasswordEncoder();
		 return NoOpPasswordEncoder.getInstance();
	}
	
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(getPasswordEncoder());
         
        return authProvider;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { // super.configure(auth);
	 * 
	 * auth.userDetailsService(userDetailsService);
	 * 
	 * User user = userService.findByAnytg("kidding").get(0); String uname =
	 * user.getFname(); String pwd = user.getPassword();
	 * 
	 * auth.inMemoryAuthentication().withUser(uname).password(pwd).roles("USER").and
	 * ().withUser("admin") .password("admin").roles("ADMIN");
	 * 
	 * }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasRole("USER")
		.antMatchers("/").permitAll().and().formLogin();
	}

	
}
