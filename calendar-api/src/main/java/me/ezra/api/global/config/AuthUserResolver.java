package me.ezra.api.global.config;

import me.ezra.api.domain.schedule.AuthUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static me.ezra.api.domain.login.LoginService.LOGIN_SESSION_KEY;

public class AuthUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AuthUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception
    {
        Long userId = (Long) webRequest.getAttribute(LOGIN_SESSION_KEY, RequestAttributes.SCOPE_SESSION);
        if (userId != null) {
            return AuthUser.of(userId);
        } else {
            throw new RuntimeException("Bad request. No Session.");
        }
    }
}
