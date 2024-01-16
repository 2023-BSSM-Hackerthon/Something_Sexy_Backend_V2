package kr.hs.bssm.weet.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.bssm.weet.domain.user.Authority;
import kr.hs.bssm.weet.global.annotation.AdminOnly;
import kr.hs.bssm.weet.global.annotation.LoginRequired;
import kr.hs.bssm.weet.global.context.Authentication;
import kr.hs.bssm.weet.global.context.ContextHolder;
import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = ContextHolder.getAuthentication();

        if (checkLoginRequired(handler)) {
            if (authentication == null) {
                throw new WeetException(ErrorCode.NO_PERMISSION);
            }

        if (checkAdminOnly(handler) && (authentication.getAuthority() != Authority.ADMIN)) {
                    throw new WeetException(ErrorCode.ONLY_ADMIN);
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

    private boolean checkAdminOnly(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (handlerMethod.getMethodAnnotation(AdminOnly.class) != null
                || handlerMethod.getBeanType().getAnnotation(AdminOnly.class) != null) {
            return true;
        }

        return false;
    }
}
