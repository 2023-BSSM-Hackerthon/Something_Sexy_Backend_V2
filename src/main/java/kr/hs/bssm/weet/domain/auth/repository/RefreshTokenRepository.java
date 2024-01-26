package kr.hs.bssm.weet.domain.auth.repository;

import kr.hs.bssm.weet.domain.auth.RefreshToken;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableRedisRepositories
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
