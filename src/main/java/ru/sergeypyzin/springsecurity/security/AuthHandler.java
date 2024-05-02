package ru.sergeypyzin.springsecurity.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Set;

@Configuration
public class AuthHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        // Получаем роли пользователя из объекта Authentication
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        // Проверяем, принадлежит ли пользователь к роли ADMIN
        if (roles.contains("ROLE_ADMIN")) {
            // Если пользователь администратор, перенаправляем его на страницу с приватными данными
            response.sendRedirect("/private-data");
        } else {
            // Если пользователь не администратор, перенаправляем его на страницу с публичными данными
            response.sendRedirect("/public-data");
        }
    }
}
