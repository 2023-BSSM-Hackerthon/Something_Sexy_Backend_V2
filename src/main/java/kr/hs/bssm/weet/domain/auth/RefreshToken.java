package kr.hs.bssm.weet.domain.auth;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash(value = "weet-rt")
public class RefreshToken {

    @Id
    private Long userId;

    @Indexed
    private String refreshToken;

    private String accessToken;

    @TimeToLive
    private Long ttl;

    public RefreshToken updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
