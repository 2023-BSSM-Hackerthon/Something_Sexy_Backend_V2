package kr.hs.bssm.weet.application.user;

import kr.hs.bssm.weet.domain.user.Authority;
import kr.hs.bssm.weet.domain.user.User;
import kr.hs.bssm.weet.domain.user.repository.UserRepository;
import kr.hs.bssm.weet.global.context.ContextHolder;
import kr.hs.bssm.weet.global.error.exception.ErrorCode;
import kr.hs.bssm.weet.global.error.exception.WeetException;
import leehj050211.bsmOauth.dto.resource.BsmUserResource;
import leehj050211.bsmOauth.type.BsmUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findCurrentUser() {
        return userRepository.findByEmail(ContextHolder.getAuthentication().getEmail())
                .orElseThrow(() -> new WeetException(ErrorCode.NOT_FOUND_USER));
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new WeetException(ErrorCode.NOT_FOUND_USER));
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<User> findAllTeacher() {
        return userRepository.findByAuthority(Authority.TEACHER);
    }

    @Transactional
    public User createUser(BsmUserResource resource) {
        if (resource.getRole() == BsmUserRole.STUDENT) {
            return userRepository.save(User.createStudent(resource));
        }

        if (resource.getRole() == BsmUserRole.TEACHER) {
            return userRepository.save(User.createTeacher(resource));
        }

        throw new WeetException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
