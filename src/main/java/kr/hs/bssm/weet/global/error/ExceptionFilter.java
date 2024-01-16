package kr.hs.bssm.weet.global.error;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.IOException;

@Slf4j
public class ExceptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (WeetException e) {
            writeErrorCode((HttpServletResponse) response, e.getErrorCode());
        } catch (ExpiredJwtException e) {
            writeErrorCode((HttpServletResponse) response, ErrorCode.EXPIRED_TOKEN);
        } catch (JwtException e) {
            writeErrorCode((HttpServletResponse) response, ErrorCode.INVALID_TOKEN);
        } catch (Exception e) {
            log.error(e.getMessage());
            writeErrorCode((HttpServletResponse) response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void writeErrorCode(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.getStatus(),
                errorCode.getCode(),
                errorCode.getMessage()
        );

        response.setStatus(errorResponse.status());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(errorResponse.toString());
    }
}
