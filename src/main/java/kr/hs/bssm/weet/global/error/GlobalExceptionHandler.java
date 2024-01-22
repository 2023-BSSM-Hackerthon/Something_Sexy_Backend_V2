package kr.hs.bssm.weet.global.error;

import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;
import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(WeetException.class)
    public ResponseEntity<ErrorResponse> weetExceptionHandler(WeetException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(HttpStatus.valueOf(errorCode.getStatus()))
                .body(
                        new ErrorResponse(
                                errorCode.getStatus(),
                                errorCode.getCode(),
                                errorCode.getMessage()
                        )
                );
    }

    @ExceptionHandler(BsmOAuthCodeNotFoundException.class)
    public ResponseEntity<ErrorResponse> bsmOAuthCodeNotFoundExceptionHandler(BsmOAuthCodeNotFoundException e) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_CODE;
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(
                        new ErrorResponse(
                                errorCode.getStatus(),
                                errorCode.getCode(),
                                errorCode.getMessage()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        log.error(e.getMessage());
        ErrorCode errorCode = ErrorCode.NOT_FOUND_CODE;
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(
                        new ErrorResponse(
                                errorCode.getStatus(),
                                errorCode.getCode(),
                                errorCode.getMessage()
                        )
                );
    }
}
