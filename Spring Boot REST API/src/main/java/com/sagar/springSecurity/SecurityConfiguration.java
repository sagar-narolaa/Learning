/*
 * package com.sagar.springSecurity;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * @EnableWebSecurity public class SecurityConfiguration extends
 * WebSecurityConfigurerAdapter {
 * 
 * 
 * @Autowired UserService userService;
 * 
 * @Autowired UserDetailsService userDetailsService;
 * 
 * @Bean public PasswordEncoder getPasswordEncoder() { return new
 * BCryptPasswordEncoder(); //return NoOpPasswordEncoder.getInstance(); }
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { // super.configure(auth);
 * 
 * auth.userDetailsService(userDetailsService);
 * 
 * 
 * User user = userService.findByAnytg("kidding").get(0); String uname =
 * user.getFname(); String pwd = user.getPassword();
 * 
 * auth.inMemoryAuthentication().withUser(uname).password(pwd).roles("USER")
 * .and().withUser("admin").password("admin").roles("ADMIN");
 * 
 * 
 * }
 * 
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
 * .antMatchers("/user").hasAnyRole("ADMIN", "USER")
 * .antMatchers("/").permitAll().and().formLogin(); }
 * 
 * }
 */