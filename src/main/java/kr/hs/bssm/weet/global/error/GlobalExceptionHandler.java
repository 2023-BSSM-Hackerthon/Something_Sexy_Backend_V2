package kr.hs.bssm.weet.global.error;

import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
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
}
