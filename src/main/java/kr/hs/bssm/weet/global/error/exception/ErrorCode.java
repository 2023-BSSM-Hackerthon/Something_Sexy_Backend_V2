package kr.hs.bssm.weet.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // auth
    NO_PERMISSION(403, "AUTH-403-1", "No permission"),
    ONLY_ADMIN(403, "AUTH-403-2", "Only admin"),

    // jwt
    INVALID_TOKEN(401, "JWT-401-1", "Invalid jwt"),
    EXPIRED_TOKEN(401, "JWT-401-2", "Expired jwt"),

    // server
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal server error");

    private final int status;
    private final String code;
    private final String message;
}
