package kr.hs.bssm.weet.presentation.auth;

import kr.hs.bssm.weet.application.auth.AuthService;
import kr.hs.bssm.weet.presentation.auth.dto.request.TokenRefreshRequestDto;
import kr.hs.bssm.weet.presentation.auth.dto.response.LoginResponseDto;
import kr.hs.bssm.weet.presentation.auth.dto.response.TokenRefreshResponseDto;
import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException;
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/refresh")
    public TokenRefreshResponseDto reissueAccessToken(@RequestBody TokenRefreshRequestDto dto) {
        return authService.reissueAccessToken(dto);
    }
}
