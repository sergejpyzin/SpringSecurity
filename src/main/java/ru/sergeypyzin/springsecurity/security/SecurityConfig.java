package ru.sergeypyzin.springsecurity.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AuthHandler handler;

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/css/**", "/guestPage", "/authorization")
                        .permitAll()
                        .requestMatchers("/public-data")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/private-data")
                        .hasAnyRole("ADMIN")
                        .anyRequest()
                        .authenticated())

                .formLogin(login -> login
                        .loginPage("/authorization")
                        .successHandler(handler)
                        .permitAll())

                .logout(logout -> logout.logoutSuccessUrl("/"))
                .csrf().disable();

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(){
        var user = User.withUsername("user").password("{noop}password").roles("USER").build();
        var admin = User.withUsername("admin").password("{noop}password").roles("USER", "ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
