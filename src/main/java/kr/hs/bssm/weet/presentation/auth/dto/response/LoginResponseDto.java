package kr.hs.bssm.weet.presentation.auth.dto.response;

public record LoginResponseDto(
        String accessToken,
        String refreshToken
) {
}
