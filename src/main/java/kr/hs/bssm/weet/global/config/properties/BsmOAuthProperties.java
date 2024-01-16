package kr.hs.bssm.weet.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.bsm")
public record BsmOAuthProperties(
        String clientId,
        String clientSecret
) {
}
