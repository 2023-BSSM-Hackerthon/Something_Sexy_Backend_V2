package kr.hs.bssm.weet.presentation.auth;

import kr.hs.bssm.weet.application.auth.AuthService;
import kr.hs.bssm.weet.presentation.auth.dto.response.LoginResponseDto;
import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException;
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/bsm")
    public LoginResponseDto login(@RequestParam("code") String code) throws BsmOAuthTokenNotFoundException, BsmOAuthInvalidClientException, IOException, BsmOAuthCodeNotFoundException {
        return authService.login(code);
    }
}
