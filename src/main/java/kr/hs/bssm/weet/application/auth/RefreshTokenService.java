package kr.hs.bssm.weet.application.auth;

import jakarta.servlet.http.HttpServletRequest;
import kr.hs.bssm.weet.domain.auth.RefreshToken;
import kr.hs.bssm.weet.domain.auth.repository.RefreshTokenRepository;
import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;
import kr.hs.bssm.weet.global.jwt.properties.JwtProperties;
import kr.hs.bssm.weet.global.jwt.util.JwtProvider;
import kr.hs.bssm.weet.global.jwt.util.JwtUtil;
import kr.hs.bssm.weet.global.jwt.util.dto.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static kr.hs.bssm.weet.global.jwt.util.JwtConstant.REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final JwtProperties jwtProperties;
    private final JwtUtil jwtUtil;

    public void saveToken(Long userId, String accessToken, String refreshToken) {
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .userId(userId)
                        .refreshToken(refreshToken)
                        .accessToken(accessToken)
                        .ttl(jwtProperties.refreshTokenExp())
                        .build()
        );
    }

    public RefreshToken updateAccessToken(String refreshToken) {
        RefreshToken tokenObject = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new WeetException(ErrorCode.NOT_FOUND_TOKEN));

        TokenInfo userInfo = jwtUtil.extractTokenInfo(tokenObject.getRefreshToken());

        String updatedAccessToken = jwtProvider.accessToken(userInfo.email(), userInfo.authority());

        return refreshTokenRepository.save(tokenObject.updateAccessToken(updatedAccessToken));
    }

    public boolean isValidRefreshToken(String token) {
        if (!jwtUtil.getTokenType(token).equals(REFRESH_TOKEN.getValue())) {
            throw new WeetException(ErrorCode.INVALID_TOKEN);
        }

        if (jwtUtil.isExpired(token)) {
            throw new WeetException(ErrorCode.EXPIRED_TOKEN);
        }

        return true;
    }

    public void deleteToken(Long userId) {
        refreshTokenRepository.deleteById(userId);
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return jwtUtil.resolveRefreshToken(request);
    }
}
