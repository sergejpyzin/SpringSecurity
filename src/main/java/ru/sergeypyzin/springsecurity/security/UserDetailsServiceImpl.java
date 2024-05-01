package ru.sergeypyzin.springsecurity.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Возвращает UserDetails, соответствующий переданному имени пользователя
        // В данном примере мы просто создаем фиктивные пользователей
        if (username.equals("user")) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username("user")
                    .password("{noop}password") // {noop} указывает, что пароль не хеширован
                    .roles("USER")
                    .build();
        } else if (username.equals("admin")) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username("admin")
                    .password("{noop}password")
                    .roles("ADMIN", "USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
    }
}
