package kr.hs.bssm.weet.application.user;

import kr.hs.bssm.weet.domain.user.User;
import kr.hs.bssm.weet.domain.user.repository.UserRepository;
import leehj050211.bsmOauth.dto.resource.BsmUserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User createUser(BsmUserResource resource) {
        return userRepository.save(User.createStudent(resource));
    }
}
