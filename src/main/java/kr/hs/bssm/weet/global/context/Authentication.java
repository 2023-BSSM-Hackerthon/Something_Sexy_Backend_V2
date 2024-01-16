package kr.hs.bssm.weet.global.context;

import kr.hs.bssm.weet.domain.user.Authority;
import kr.hs.bssm.weet.global.jwt.util.dto.TokenInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Authentication {

    private String email;
    private Authority authority;

    public Authentication(TokenInfo info) {
        this.email = info.email();
        this.authority = info.authority();
    }
}
