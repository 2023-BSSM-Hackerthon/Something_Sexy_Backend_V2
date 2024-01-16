package kr.hs.bssm.weet.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WeetException extends RuntimeException {

    private final ErrorCode errorCode;
}
