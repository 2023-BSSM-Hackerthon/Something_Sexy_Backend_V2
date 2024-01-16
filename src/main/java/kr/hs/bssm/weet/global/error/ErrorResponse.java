package kr.hs.bssm.weet.global.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;

public record ErrorResponse(
        Integer status,
        String code,
        String message
) {
    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new WeetException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
