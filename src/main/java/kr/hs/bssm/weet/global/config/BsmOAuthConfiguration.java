package kr.hs.bssm.weet.global.config;

import kr.hs.bssm.weet.global.config.properties.BsmOAuthProperties;
import leehj050211.bsmOauth.BsmOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BsmOAuthConfiguration {

    private final BsmOAuthProperties bsmOAuthProperties;

    @Bean
    public BsmOauth bsmOauth() {
        return new BsmOauth(
                bsmOAuthProperties.clientId(),
                bsmOAuthProperties.clientSecret()
        );
    }
}
