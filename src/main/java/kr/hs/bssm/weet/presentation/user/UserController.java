package kr.hs.bssm.weet.presentation.user;

import kr.hs.bssm.weet.application.user.UserService;
import kr.hs.bssm.weet.domain.user.User;
import kr.hs.bssm.weet.global.annotation.LoginRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @LoginRequired
    @GetMapping
    public User getCurrentUser() {
        return userService.findCurrentUser();
    }
}
