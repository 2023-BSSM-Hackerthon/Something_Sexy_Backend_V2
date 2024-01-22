package kr.hs.bssm.weet.domain.user.repository;

import kr.hs.bssm.weet.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
