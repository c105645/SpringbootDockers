package com.stackroute.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
	

    @Value("${jwt.signing.key}")
    private String signingKey;
        @Bean
	    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
              	    	 
	        http.cors().configurationSource(corsConfiguration())
	                .and()
	                .csrf().disable().httpBasic()
	                .and()
	                .authorizeRequests()
	                .antMatchers("/api/v1/auth/register").permitAll()
	                .antMatchers("/api/v1/auth/login").permitAll()
	                .anyRequest().authenticated()
	                .and()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	        return http.build();

	    }
       
    	
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
        
      
        @Bean
        public AuthenticationManager authenticationManager(
                AuthenticationConfiguration authConfig) throws Exception {
            return authConfig.getAuthenticationManager();
        }
    	
        
	    @Bean
	    public CorsConfigurationSource corsConfiguration() {
	        UrlBasedCorsConfigurationSource configuration = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.addAllowedOrigin("http://ourfrontend.com");
	        corsConfiguration.addAllowedMethod("*");
	        corsConfiguration.addAllowedHeader("*");
	        configuration.registerCorsConfiguration("/**", corsConfiguration);
	        return configuration;
	    }
	    

	    


}
