package kr.hs.bssm.weet.global.jwt.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import kr.hs.bssm.weet.global.context.Authentication;
import kr.hs.bssm.weet.global.context.ContextHolder;
import kr.hs.bssm.weet.global.jwt.util.JwtUtil;
import kr.hs.bssm.weet.global.jwt.util.dto.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = jwtUtil.resolveToken(httpServletRequest);

        if (jwt != null) {
            TokenInfo tokenInfo = jwtUtil.extractTokenInfo(jwt);
            ContextHolder.setAuthentication(new Authentication(tokenInfo));
        }

        chain.doFilter(request, response);
    }
}
