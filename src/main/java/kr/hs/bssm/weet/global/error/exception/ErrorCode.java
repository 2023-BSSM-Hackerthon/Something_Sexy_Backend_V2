package kr.hs.bssm.weet.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // auth
    NO_PERMISSION(403, "AUTH-403-1", "No permission"),
    ONLY_TEACHER(403, "AUTH-403-2", "Only teacher"),

    // user
    NOT_FOUND_USER(404, "USER-404-1", "Not found user"),

    // BSM OAuth
    NOT_FOUND_CODE(401, "BSM-401-1", "BSM OAuth Authorization Code not found"),

    // jwt
    INVALID_TOKEN(401, "JWT-401-1", "Invalid jwt"),
    EXPIRED_TOKEN(401, "JWT-401-2", "Expired jwt"),
    NOT_FOUND_TOKEN(401, "JWT-401-3", "Not found jwt"),

    // server
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal server error");

    private final int status;
    private final String code;
    private final String message;
}
