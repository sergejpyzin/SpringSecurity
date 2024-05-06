package ru.sergeypyzin.springsecurity.aspect;

// UserActionAspect.java

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserActionAspect {

    /**
     * Pointcut для методов, помеченных аннотацией @TrackUserAction.
     */
    @Pointcut("@annotation(ru.sergeypyzin.springsecurity.annotation.TrackUserAction)")
    public void trackUserAction() {
    }

    /**
     * Advice для логирования действий пользователя после успешного выполнения метода.
     *
     * @param joinPoint точка присоединения, представляющая вызов метода
     * @param result    результат выполнения метода
     */
    @AfterReturning(pointcut = "trackUserAction()", returning = "result")
    public void logUserAction(JoinPoint joinPoint, Object result) {
        // Получаем имя метода
        String methodName = joinPoint.getSignature().getName();
        // Получаем аргументы метода
        Object[] args = joinPoint.getArgs();
        // Выводим информацию о действии пользователя
        System.out.println("User action: Method '" + methodName + "' was called with arguments: " + argsToString(args));
    }

    /**
     * Преобразует массив аргументов в строковое представление.
     *
     * @param args массив аргументов метода
     * @return строковое представление аргументов
     */
    private String argsToString(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg).append(", ");
        }
        return sb.toString();
    }
}

