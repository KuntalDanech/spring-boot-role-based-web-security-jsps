package com.danech.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.danech.enums.UserRole;
import com.danech.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl userService;
	
	/**
	 * Spring Security User Password Encoder
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Spring Security Authentication
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	/**
	 * Most important method
	 * Spring Security Authorization
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		
		.authorizeRequests()
		
		// Secure all requests
		.antMatchers("/login?logout").permitAll()
		.antMatchers("/login?error").permitAll()
		.antMatchers("/admin/*").hasRole("ADMIN")
		.antMatchers("/agent/*").hasRole("AGENT")
		.antMatchers("/manager/*").hasRole("MANAGER")
		.anyRequest().authenticated()
		
		.and()
		
		// Login page and redirect URLs
		.formLogin()
		.loginPage("/login")
		.permitAll()
		
		// After success login where we need to redirect the web page
		// Lambda function has bee used see @AuthenticationSuccessHandler
		.successHandler((request,response,authentication)->{
			
			Optional<? extends GrantedAuthority> grantedAuthorityOptional = (Optional<? extends GrantedAuthority>) authentication
					.getAuthorities().stream().findFirst();
			if (grantedAuthorityOptional.isPresent()) {
				GrantedAuthority grantedAuthority = grantedAuthorityOptional.get();
				String role = grantedAuthority.getAuthority();
				if (role.equals(UserRole.ROLE_ADMIN.getType())) {
					response.sendRedirect(request.getContextPath()+"/admin/index");
				} else if (role.equals(UserRole.ROLE_AGENT.getType())) {
					response.sendRedirect(request.getContextPath()+"/agent/index");
				} else if (role.equals(UserRole.ROLE_MANAGER.getType())) {
					response.sendRedirect(request.getContextPath()+"/manager/index");
				}
			}
		})
		
		.and()
		
		//Logout
		.logout()
		
		.and()
		
		// Access denied page
		.exceptionHandling()
		.accessDeniedPage("/403");
				
	}
}
