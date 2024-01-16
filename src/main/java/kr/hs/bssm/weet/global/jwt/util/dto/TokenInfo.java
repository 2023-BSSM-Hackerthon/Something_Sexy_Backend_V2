package kr.hs.bssm.weet.global.jwt.util.dto;

import kr.hs.bssm.weet.domain.user.Authority;

public record TokenInfo(
        String email,
        Authority authority
) {
}
