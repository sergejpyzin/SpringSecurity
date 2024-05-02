package ru.sergeypyzin.springsecurity.security;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SecurityConfig {

    private final AuthHandler handler;

    /**
     * Настройка цепочки фильтров безопасности.
     *
     * @param httpSecurity объект конфигурации безопасности
     * @return цепочка фильтров безопасности
     * @throws Exception если происходит ошибка при настройке безопасности
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Настройка доступа к различным URL-адресам
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/css/**", "/guestPage", "/login")
                        .permitAll()
                        .requestMatchers("/public-data")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/private-data")
                        .hasAnyRole("ADMIN")
                        .anyRequest()
                        .authenticated())

                // Настройка формы входа в систему
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler(handler)
                        .permitAll())

                // Настройка выхода из системы
                .logout(logout -> logout.logoutSuccessUrl("/"))

                // Отключение CSRF-защиты
                .csrf().disable();

        // Возвращаем цепочку фильтров безопасности
        return httpSecurity.build();
    }



    /**
     * Создает и возвращает PasswordEncoder, используемый для кодирования паролей пользователей.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Создание и возврат экземпляра PasswordEncoder, который будет использоваться для хэширования паролей
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Создает и возвращает UserDetailsManager с предустановленными пользователями 'user' и 'admin'.
     * Пароли пользователей задаются в открытом тексте с префиксом {noop}.
     * Пользователь 'user' имеет роль 'USER', а пользователь 'admin' имеет роли 'USER' и 'ADMIN'.
     *
     * @return UserDetailsManager
     */
    @Bean
    public UserDetailsManager userDetailsManager(){
        // Создание экземпляров пользователей (user и admin) с их учетными данными и ролями
        var user = User.withUsername("user").password("{noop}password").roles("USER").build();
        var admin = User.withUsername("admin").password("{noop}password").roles("USER", "ADMIN").build();

        // Создание и возврат экземпляра UserDetailsManager, который будет управлять пользователями и их данными
        return new InMemoryUserDetailsManager(user, admin);
    }

}
