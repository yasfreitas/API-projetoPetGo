package com.petGo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http)throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests((requests)-> requests
				.requestMatchers(
						"/v3/api-docs/**",
						"/swagger-ui/**",
						"swagger-ui/index.html")
				.permitAll()
				
				.requestMatchers(
						HttpMethod.POST,"/consulta/","/especialidade", "/pet/", "/proprietario/", "/tipo/", "/veterinario/")
				.permitAll()

				.requestMatchers(
						HttpMethod.GET,"/consulta/","/especialidade/", "/pet/", "/proprietario/", "/tipo/", "/veterinario/",
						"/consulta/{id}","/especialidade/{id}", "/pet/{id}", "/proprietario/{id}", "/tipo/{id}", "/veterinario/{id}",
						"/consulta/hora/{hora}","/consulta/data/{data}", "consulta/veterinario/{veterinario}", "consulta/pet/{pet}")
				.permitAll()

				.requestMatchers(
						HttpMethod.DELETE,"/consulta/{id}","/especialidade/{id}", "/pet/{id}", "/proprietario/{id}", "/tipo/{id}", "/veterinario/{id}")
				.permitAll()

				.requestMatchers(
						HttpMethod.PUT,"/consulta/{id}","/especialidade/{id}", "/pet/{id}", "/proprietario/{id}", "/tipo/{id}", "/veterinario/{id}")
				.permitAll()

				.anyRequest()
				.permitAll()

				)
		.httpBasic();
		return http.build();
	}
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("ccreators")
				.password("12346")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}