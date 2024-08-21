package com.shinhan.dailyconsume.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors(cors -> cors.configurationSource(request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.addAllowedOrigin("*");
			config.addAllowedMethod("*");
			config.addAllowedHeader("*");
			return config;
		}));

		http
		.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/**", "/").permitAll();
			auth.anyRequest().authenticated();
		});
		
        
		http.formLogin(login-> {
			login.loginPage("/auth/login")
			.usernameParameter("mid")
			.defaultSuccessUrl("/auth/loginSuccess")
			.permitAll();
		});
		
		http.logout(out -> {
			out.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
			.logoutSuccessUrl("/auth/loginSuccess")
			.invalidateHttpSession(true);
		});
		
		http.exceptionHandling(handling -> handling.accessDeniedPage("/auth/accessDenined"));
		return http.build();
	}
}
