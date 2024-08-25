package com.shinhan.dailyconsume.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/Login","/Register").permitAll();
			auth.anyRequest().permitAll();
		});
		
        
		http.formLogin(login-> {
			login.loginProcessingUrl("/Login")
			.defaultSuccessUrl("/home",true)
			.permitAll();
		});
		
		http.logout(out -> {
			out.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
			.logoutSuccessUrl("/Login")
			.invalidateHttpSession(true);
		});
		
		http.csrf().disable();
		
		http.exceptionHandling(handling -> handling.accessDeniedPage("/auth/accessDenined"));
		
		return http.build();
		
	}
}