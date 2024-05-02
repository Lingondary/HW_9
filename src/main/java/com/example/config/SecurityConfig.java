package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeRequests(authorize -> authorize
						.requestMatchers("/css/**", "/favicon.ico", "/", "/index").permitAll()
						.requestMatchers("/user").hasRole("USER")
						.requestMatchers("/admin").hasRole("ADMIN")
						.requestMatchers("/private-data").hasRole("ADMIN")
						.anyRequest().authenticated()
				)
				.formLogin(login -> login
						.loginPage("/login")
						.defaultSuccessUrl("/")
						.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/")
						.permitAll());
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	UserDetailsManager inMemoryUserDetailsManager() {
		PasswordEncoder encoder = passwordEncoder();
		var user1 = User.withUsername("user").password(encoder.encode("password")).roles("USER").build();
		var user2 = User.withUsername("admin").password(encoder.encode("password")).roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2);
	}
}