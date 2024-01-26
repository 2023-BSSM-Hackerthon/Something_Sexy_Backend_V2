package kr.hs.bssm.weet.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.bssm.weet.domain.user.Authority;
import kr.hs.bssm.weet.global.annotation.TeacherOnly;
import kr.hs.bssm.weet.global.annotation.LoginRequired;
import kr.hs.bssm.weet.global.context.Authentication;
import kr.hs.bssm.weet.global.context.ContextHolder;
import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = ContextHolder.getAuthentication();

        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        if (checkLoginRequired(handler)) {
            if (authentication == null) {
                throw new WeetException(ErrorCode.NO_PERMISSION);
            }

        if (checkTeacherOnly(handler) && (authentication.getAuthority() != Authority.TEACHER)) {
                    throw new WeetException(ErrorCode.ONLY_TEACHER);
            }
        }

        return true;
    }

    private boolean checkLoginRequired(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (handlerMethod.getMethodAnnotation(LoginRequired.class) != null
                || handlerMethod.getBeanType().getAnnotation(LoginRequired.class) != null) {
            return true;
        }

        return false;
    }

    private boolean checkTeacherOnly(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (handlerMethod.getMethodAnnotation(TeacherOnly.class) != null
                || handlerMethod.getBeanType().getAnnotation(TeacherOnly.class) != null) {
            return true;
        }

        return false;
    }
}
